package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.item.TwoLayerDyeable
import me.fzzyhmstrs.imbued_gear.material.FzzyArmorMaterial

class DyeableModifiableArmorItem(fzzyMaterial: FzzyArmorMaterial, slot: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, slot, settings), TwoLayerDyeable {

}