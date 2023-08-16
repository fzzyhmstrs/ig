package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_core.item_util.DefaultAugmentSwordItem
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.registry.RegisterEnchantment
import me.fzzyhmstrs.imbued_gear.tool.CracklingToolMaterial
import net.minecraft.util.Identifier

class CracklingSpellbladeItem(settings: Settings): DefaultAugmentSwordItem(CracklingToolMaterial,3,-2.4f,settings) {
    override val fallbackId: Identifier = IG.identity("bankai")

    override fun canAcceptAugment(augment: ScepterAugment): Boolean {
        return augment == RegisterEnchantment.BANKAI
    }
}