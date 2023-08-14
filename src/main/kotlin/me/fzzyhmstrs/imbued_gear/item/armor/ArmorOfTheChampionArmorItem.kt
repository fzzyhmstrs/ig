package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.ArmorOfTheChampionArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class ArmorOfTheChampionArmorItem(slot: EquipmentSlot, settings: Settings): ModifiableArmorItem(ArmorOfTheChampionArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.CHAMPIONS_GRIT.modifierId)

    private val celestialModifiers = mutableListOf(RegisterModifier.CHAMPIONS_GRIT.modifierId, RegisterModifier.CHAMPIONS_RESOLVE.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return celestialModifiers
    }

}