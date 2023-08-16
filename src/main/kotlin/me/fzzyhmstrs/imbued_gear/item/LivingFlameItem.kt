package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.event.ModifySpellEvent
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

open class LivingFlameItem(settings: Settings)
    :
    SpecialityOffhandItem(settings,listOf(),listOf())
{
    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        if (world.isClient) return TypedActionResult.consume(stack)
        val nbt = stack.orCreateNbt
        if (nbt.contains("active")) return TypedActionResult.fail(stack)
        nbt.putBoolean("active",true)
        nbt.putInt("active_time",IgConfig.items.livingFlame.effectDuration.get())
        EquimentModifierHelper.addModifier(RegisterModifier.KINDLED.modifierId,stack)
        user.itemCooldownManager.set(stack.item, cooldown)
        return TypedActionResult.success(stack)
    }
    
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (world.isClient) return
        val nbt = stack.nbt?:return
        if (nbt.contains("active_time")){
            val ticks = nbt.getInt("active_time")
            val newTicks = ticks--
            if (newTicks <= 0){
                EquimentModifierHelper.removeModifier(RegisterModifier.KINDLED.modifierId,stack)
                nbt.remove("active_time")
                nbt.remove("active")
            } else {
                nbt.putInt("active_time",newTicks)
            }
        }
    }
    
}
