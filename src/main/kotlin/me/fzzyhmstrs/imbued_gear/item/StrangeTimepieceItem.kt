package me.fzzyhmstrs.imbued_gear.item

import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import java.util.*

open class StrangeTimepieceItem(settings: Settings) : AbstractAugmentJewelryItem(settings) {

    override fun getModifiers(
        stack: ItemStack,
        slot: SlotReference,
        entity: LivingEntity,
        uuid: UUID
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val modifiers = super.getModifiers(stack, slot, entity, uuid)
        modifiers.put(
            RegisterAttribute.SPELL_COOLDOWN,
            EntityAttributeModifier(uuid, "timepiece_attack_speed", 0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        )
        return modifiers
    }
}