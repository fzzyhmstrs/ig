package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.ArmorOfTheChampionArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class EternityShroudArmorItem(slot: EquipmentSlot, settings: Settings): ModifiableArmorItem(ArmorOfTheChampionArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.ETERNAL.modifierId)

    private val celestialModifiers = mutableListOf(RegisterModifier.ETERNAL.modifierId, RegisterModifier.ETERNITY_SHROUDED.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return celestialModifiers
    }

}