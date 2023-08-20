package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_core.item_util.DefaultAugmentSwordItem
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class CracklingSpellbladeItem(settings: Settings): DefaultAugmentSwordItem(IgConfig.materials.tools.crackling.compatMaterial(),4,-2.9f,settings) {
    override val fallbackId: Identifier = IG.identity("bankai")

    private val flavorText: MutableText by lazy{
        FlavorHelper.makeFlavorText(this)
    }

    private val flavorTextDesc: MutableText by lazy{
        FlavorHelper.makeFlavorTextDesc(this)
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        FlavorHelper.addFlavorText(tooltip, context, flavorText, flavorTextDesc)
    }
}