package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.WarriorsHarnessArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class WarriorsHarnessArmorItem(slot: EquipmentSlot, settings: Settings): ModifiableArmorItem(WarriorsHarnessArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.WARRIORS_LIGHT.modifierId)

    private val celestialModifiers = mutableListOf(RegisterModifier.WARRIORS_LIGHT.modifierId, RegisterModifier.WARRIORS_PATH.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return celestialModifiers
    }

}