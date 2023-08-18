package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Identifier

class RadiantBroadswordItem(material: ToolMaterial, settings: Settings): SwordItem(material,3,-2.4f,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.RADIANT_DEVOTION.modifierId)
        return super.defaultModifiers(type)
    }

}