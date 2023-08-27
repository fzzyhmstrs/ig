package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_imbuement.item.SniperBowItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterTool
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.minecraft.item.ItemStack

class HuntersArbalestItem(settings: Settings) : SniperBowItem(settings) {

    override fun getSpeed(stack: ItemStack): Float{
        return super.getSpeed(stack) * 1.35f
    }

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return ingredient.isOf(RegisterItem.PURESTEEL_INGOT)
    }
}