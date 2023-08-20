package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

open class LivingFlameItem(settings: Settings)
    :
    SpecialityOffhandItem(settings,listOf(),listOf(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier.ELEMENTAL.modifierId)), Modifiable
{

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        if (world.isClient) return TypedActionResult.consume(stack)
        val nbt = stack.orCreateNbt
        if (nbt.contains("active")) return TypedActionResult.fail(stack)
        nbt.putBoolean("active",true)
        nbt.putLong("active_time",world.time)
        EquipmentModifierHelper.addModifier(RegisterModifier.KINDLED.modifierId,stack)
        ModifierHelper.addModifier(RegisterModifier.KINDLED_SCEPTER.modifierId,stack)
        user.itemCooldownManager.set(stack.item, IgConfig.items.livingFlame.cooldown.get())
        return TypedActionResult.success(stack)
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (world.isClient) return
        val nbt = stack.nbt?:return
        if (world.time - (stack.nbt?.getLong("active_time") ?: 0L) > IgConfig.items.livingFlame.effectDuration.get()){
            nbt.remove("active_time")
            nbt.remove("active")
            EquipmentModifierHelper.removeModifier(RegisterModifier.KINDLED.modifierId,stack)
            ModifierHelper.removeModifier(stack,RegisterModifier.KINDLED_SCEPTER.modifierId)
        }
    }
    
}
