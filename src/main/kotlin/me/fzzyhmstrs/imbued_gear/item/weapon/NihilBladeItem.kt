package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import me.fzzyhmstrs.imbued_gear.tool.NihilBladeMaterial
import net.minecraft.item.SwordItem
import net.minecraft.util.Identifier

class NihilBladeItem(settings: Settings): SwordItem(NihilBladeMaterial,3,-2.4f,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()){
            return mutableListOf(RegisterModifier.NOTHINGNESS.modifierId)
        }
        return super.defaultModifiers(type)
    }

}