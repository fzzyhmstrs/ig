package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.armor.VoidMailArmorMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

class VoidMailArmorItem(slot: EquipmentSlot,settings: Settings): ModifiableArmorItem(VoidMailArmorMaterial(),slot,settings) {

    private val modifiers = mutableListOf(RegisterModifier.VOID_SHROUDED.modifierId)

    override fun defaultEquipmentModifiers(): MutableList<Identifier> {
        return modifiers
    }

    override fun celestialModifiers(): List<Identifier> {
        return modifiers
    }

}