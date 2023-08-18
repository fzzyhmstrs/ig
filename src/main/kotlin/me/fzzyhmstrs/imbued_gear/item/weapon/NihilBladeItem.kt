package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Identifier

class NihilBladeItem(material: ToolMaterial,settings: Settings): SwordItem(material,0,-1.5f,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.NOTHINGNESS.modifierId)
        return super.defaultModifiers(type)
    }

}