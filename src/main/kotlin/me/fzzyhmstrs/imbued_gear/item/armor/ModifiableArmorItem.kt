package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial

open class ModifiableArmorItem(fzzyMaterial: ArmorMaterial, slot: Type, settings: Settings): ArmorItem(fzzyMaterial,slot,settings), Modifiable {

}