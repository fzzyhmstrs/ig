package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.event.ModifyModifiersEvent
import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.gear_core.interfaces.AugmentTracking
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import kotlin.math.atan

class CrownOfSorrowsItem(settings: Settings): AbstractAugmentJewelryItem(settings), DamageTracking, KillTracking, AugmentTracking {

    companion object{
        private const val twoOverPi = 2 / MathHelper.PI
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        val nbt = stack.nbt ?: return
        if (nbt.contains("active")){
            if (!nbt.getBoolean("active")){
                return
            }
        }
        if (nbt.contains("active_timer")){
            if (world.time - (stack.nbt?.getLong("active_timer") ?: 0L) > IgConfig.items.crownOfSorrows.activeDuration.get()){
                nbt.putBoolean("active",false)
                nbt.putInt("active_timer",0)
                EquipmentModifierHelper.removeModifier(RegisterModifier.WHISPER_OF_REGRET.modifierId,stack)
                ModifierHelper.removeModifier(stack, RegisterModifier.WHISPER_OF_REGRET_SCEPTER.modifierId)
                return
            }
        }
    }

    override fun onWearerDamaged(
        stack: ItemStack,
        wearer: LivingEntity,
        attacker: LivingEntity?,
        source: DamageSource,
        amount: Float
    ): Float {
        val nbt = stack.nbt
        if(nbt != null) {
            if (nbt.contains("active") && nbt.getBoolean("active")) {
                return super.onWearerDamaged(stack, wearer, attacker, source, amount) * 0.5f
            }
        }
        val rnd = wearer.world.random.nextFloat()
        if (rnd < getSorrowChance(stack,IgConfig.items.crownOfSorrows.defense50Percent.get())){
            val nbt2 = stack.orCreateNbt
            nbt2.putBoolean("active", true)
            nbt2.putLong("active_timer",wearer.world.time)
            EquipmentModifierHelper.addModifier(RegisterModifier.WHISPER_OF_REGRET.modifierId,stack)
            ModifierHelper.addModifier(RegisterModifier.WHISPER_OF_REGRET_SCEPTER.modifierId, stack)
        }
        return super.onWearerDamaged(stack, wearer, attacker, source, amount)
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        incrementKillCount(stack)
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

    private fun getSorrowChance(stack: ItemStack, fiftyPercentCount: Int): Float{
        val kills = getKillCount(stack)
        return atan(1f / fiftyPercentCount * kills) * twoOverPi
    }


}