package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.event.ModifySpellEvent
import me.fzzyhmstrs.amethyst_core.item_util.AugmentScepterItem
import me.fzzyhmstrs.amethyst_core.item_util.ScepterLike
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.SummonEntityAugment
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.mana_util.ManaHelper
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.world.World
import kotlin.math.max

open class BoneRattleItem(
    settings: Settings)
    :
    SpecialityOffhandItem(settings,listOf(),listOf()), ManaItem
{
    init{
        ModifySpellEvent.EVENT.register{spell: ScepterAugment, world: World, user: LivingEntity, hand: Hand, modifiers: AbstractModifier.CompiledModifiers<AugmentModifier> ->
            for (stack in user.handItems) {
                val item = stack.item
                if (item is BoneRattleItem && user.getStackInHand(hand).item is ScepterLike) {
                    if (spell is SummonEntityAugment){
                        if (world.random.nextFloat() <  IgConfig.items.boneRattle.duplicationChance.get()){
                            if (item.checkCanUse(stack,world,user,IgConfig.items.boneRattle.duplicationDamage.get(),
                                    AcText.empty())){
                                val testLevel = ScepterHelper.getTestLevel(user.getStackInHand(hand).orCreateNbt,
                                    Registries.ENCHANTMENT.getId(spell)?.toString()?:Identifier("amethyst_imbuement","magic_missile").toString(),spell)
                                val level = max(1, ((testLevel + modifiers.compiledData.levelModifier) * user.getAttributeValue(
                                    RegisterAttribute.SPELL_LEVEL)).toInt())
                                spell.applyModifiableTasks(world,user,hand,level,modifiers.modifiers,modifiers.compiledData)
                                item.manaDamage(stack, world, user, IgConfig.items.boneRattle.duplicationDamage.get())
                            }
                        }
                    }
                }
            }
            ActionResult.PASS
        }
    }

    
    override fun getRepairTime(): Int {
        return IgConfig.items.boneRattle.repairTime.get()
    }
    
    override fun onCraft(stack: ItemStack, world: World, player: PlayerEntity) {
        if (world.isClient || getRepairTime() == 0) return
        ManaHelper.initializeManaItem(stack)
    }
    
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (world.isClient || getRepairTime() == 0) return
        if (ManaHelper.needsInitialization(stack)){
            ManaHelper.initializeManaItem(stack)
        }
        //slowly heal damage over time
        if (ManaHelper.tickHeal(stack)){
            healDamage(1,stack)
        }
    }
    
}
