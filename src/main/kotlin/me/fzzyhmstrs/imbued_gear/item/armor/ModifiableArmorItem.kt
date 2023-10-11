package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.material.FzzyArmorMaterial
import net.minecraft.item.ArmorItem
import net.minecraft.util.Identifier

open class ModifiableArmorItem(private val fzzyMaterial: FzzyArmorMaterial, slot: Type, settings: Settings): ArmorItem(fzzyMaterial,slot,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType<*>): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) return fzzyMaterial.getModifiers().toMutableList()
        return super.defaultModifiers(type)
    }

}