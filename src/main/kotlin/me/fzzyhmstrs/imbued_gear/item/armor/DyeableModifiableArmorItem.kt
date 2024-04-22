package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.imbued_gear.item.TwoLayerDyeable
import net.minecraft.item.ArmorMaterial

class DyeableModifiableArmorItem(fzzyMaterial: ArmorMaterial, slot: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, slot, settings), TwoLayerDyeable {

}