package me.fzzyhmstrs.imbued_gear.enchantment

import com.google.common.collect.Multimap
import me.fzzyhmstrs.fzzy_core.coding_util.AbstractConfigDisableEnchantment
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.fzzy_core.enchantment_util.AttributeProviding
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import org.apache.commons.lang3.function.TriFunction
import java.util.*

class SpellAttributeEnchantment(
    weight: Rarity,
    private val attribute: EntityAttribute,
    private val attributeModifier: TriFunction<UUID,String,Int,EntityAttributeModifier>,
    private val uuids: EnumMap<EquipmentSlot, UUID>,
    vararg slot: EquipmentSlot)
    :
    AbstractConfigDisableEnchantment(weight, EnchantmentTarget.ARMOR,*slot),
    AttributeProviding
{

    /*private val uuids: EnumMap<EquipmentSlot, UUID> = EnumMap(mapOf(
        EquipmentSlot.HEAD to UUID.fromString("c9213e78-4439-11ee-be56-0242ac120002"),
        EquipmentSlot.CHEST to UUID.fromString("c92144cc-4439-11ee-be56-0242ac120002"),
        EquipmentSlot.LEGS to UUID.fromString("c9214698-4439-11ee-be56-0242ac120002"),
        EquipmentSlot.FEET to UUID.fromString("c92147e2-4439-11ee-be56-0242ac120002")
    ))*/

    override fun getMinPower(level: Int): Int {
        return 15 * level
    }

    override fun getMaxPower(level: Int): Int {
        return getMinPower(level) + 20
    }

    override fun getMaxLevel(): Int {
        return IgConfig.enchants.getMaxLevel(this,3)
    }

    override fun isAcceptableItem(stack: ItemStack): Boolean {
        return (stack.item is ArmorItem) && checkEnabled()
    }

    override fun canAccept(other: Enchantment): Boolean {
        return !FzzyPort.ENCHANTMENT.isInTag(other,RegisterTag.SPELL_ATTRIBUTE_ENCHANTS)
    }

    override fun checkEnabled(): Boolean {
        return IgConfig.enchants.isEnchantEnabled(this)
    }

    override fun modifyAttributeMap(
        map: Multimap<EntityAttribute, EntityAttributeModifier>,
        slot: EquipmentSlot,
        level: Int
    ) {
        val uuid: UUID = uuids[slot] ?: return
        val name = "spell_rage_modifier_" + slot.getName()
        map.put(
            attribute,
            attributeModifier.apply(uuid,name, level)
        )
    }

}