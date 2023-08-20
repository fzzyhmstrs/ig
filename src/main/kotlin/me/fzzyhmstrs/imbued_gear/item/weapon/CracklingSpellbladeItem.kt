package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_core.item_util.DefaultAugmentSwordItem
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.util.Identifier

class CracklingSpellbladeItem(settings: Settings): DefaultAugmentSwordItem(IgConfig.materials.tools.crackling.compatMaterial(),4,-2.9f,settings) {
    override val fallbackId: Identifier = IG.identity("bankai")
}