package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.SpellbladesArmorArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class SpellbladesArmorArmorItem(slot: EquipmentSlot, settings: Settings): ModifiableArmorItem(SpellbladesArmorArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.MANA_KINDLED.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}