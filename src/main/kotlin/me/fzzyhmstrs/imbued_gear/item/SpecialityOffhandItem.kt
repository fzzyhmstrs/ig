package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.CustomFlavorItem
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.interfaces.*
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Identifier

open class SpecialityOffhandItem(
    settings: Settings,
    private val equipmentModifiers: List<Identifier> = listOf(),
    private val scepterModifiers: List<Identifier> = listOf())
    :
    CustomFlavorItem(settings), Modifiable, DamageTracking, AttributeTracking, HitTracking, TickTracking, KillTracking, ModifierTracking
{

    override fun defaultModifiers(type: ModifierHelperType<*>?): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) {
            return equipmentMods().toMutableList()
        }
        if (type == ModifierHelper.getType()) return scepterMods().toMutableList()
        return super.defaultModifiers(type)
    }

    open fun equipmentMods(): List<Identifier>{
        return equipmentModifiers
    }

    open fun scepterMods(): List<Identifier>{
        return scepterModifiers
    }

    override fun fzzy_core_getCorrectSlot(): EquipmentSlot? {
        return EquipmentSlot.OFFHAND
    }

}
