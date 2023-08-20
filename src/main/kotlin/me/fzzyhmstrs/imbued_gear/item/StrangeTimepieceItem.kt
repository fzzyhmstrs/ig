package me.fzzyhmstrs.imbued_gear.item

import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.item_util.interfaces.Flavorful
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World
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