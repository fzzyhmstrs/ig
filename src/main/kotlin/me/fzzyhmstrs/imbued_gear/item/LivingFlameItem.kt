package me.fzzyhmstrs.imbued_gear.item

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

open class LivingFlameItem(settings: Settings)
    :
    SpecialityOffhandItem(settings,listOf(),listOf(/*me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier.ELEMENTAL.modifierId*/)), Modifiable
{
    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(EquipmentSlot.OFFHAND))
        SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,"51531500-7d92-11ee-b962-0242ac120002",2.0, EntityAttributeModifier.Operation.ADDITION, map)
        map
    }


    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        if (world.isClient) return TypedActionResult.consume(stack)
        val nbt = stack.orCreateNbt
        if (nbt.contains("active")) return TypedActionResult.fail(stack)
        nbt.putBoolean("active",true)
        nbt.putLong("active_time",world.time)
        EquipmentModifierHelper.addModifier(RegisterModifier.KINDLED.modifierId,stack)
        ModifierHelper.addModifier(RegisterModifier.KINDLED_SCEPTER.modifierId,stack)
        world.playSound(null,user.blockPos,SoundEvents.BLOCK_FIRE_AMBIENT,SoundCategory.PLAYERS,0.5f,1f)
        user.itemCooldownManager.set(stack.item, IgConfig.items.livingFlame.cooldown.get())
        return TypedActionResult.success(stack)
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier>{
        if (slot == EquipmentSlot.OFFHAND)
            return attributeModifiers
        return super.getAttributeModifiers(slot)
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