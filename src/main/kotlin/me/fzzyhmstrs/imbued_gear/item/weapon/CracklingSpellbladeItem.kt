package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_core.item_util.DefaultAugmentSwordItem
import me.fzzyhmstrs.amethyst_core.registry.RegisterTag
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.util.Identifier

class CracklingSpellbladeItem(settings: Settings): DefaultAugmentSwordItem(IgConfig.materials.tools.crackling.compatMaterial(),4,-2.9f,settings) {
    override val fallbackId: Identifier = IG.identity("bankai")

    override fun canAcceptAugment(augment: ScepterAugment): Boolean{
        return FzzyPort.ENCHANTMENT.isInTag(augment, RegisterTag.LIGHTNING_AUGMENTS)
    }

    override fun canBeModifiedBy(type: ModifierHelperType<*>): Boolean {
        return true
    }
}
