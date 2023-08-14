package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.LichKingsRobesArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class LichKingsRobesArmorItem(slot: EquipmentSlot, settings: Settings): ModifiableArmorItem(LichKingsRobesArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.RULER_OF_THE_DAMNED.modifierId, RegisterModifier.HELIOPHOBIA.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}
