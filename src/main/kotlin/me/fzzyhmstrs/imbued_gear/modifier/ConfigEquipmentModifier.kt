package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifierHelper
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.util.Identifier

class ConfigEquipmentModifier(
    modifierId: Identifier = AbstractModifierHelper.BLANK,
    target: EquipmentModifierTarget = EquipmentModifierTarget.NONE,
    weight: Int = 10,
    rarity: Rarity = Rarity.COMMON,
    persistent: Boolean = false,
    availableForSelection: Boolean = true)
: EquipmentModifier(
    modifierId,
    target,
    weight,
    rarity,
    persistent,
    availableForSelection)
{

    override fun randomlySelectable(): Boolean{
        return IgConfig.modifiers.isModifierEnabled(modifierId) && super.randomlySelectable()
    }
}
