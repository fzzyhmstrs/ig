package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_imbuement.item.weapon.SniperBowItem
import me.fzzyhmstrs.imbued_gear.item.TwoLayerDyeable
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.minecraft.item.ItemStack

class HuntersArbalestItem(settings: Settings) : SniperBowItem(settings), TwoLayerDyeable {

    override fun getSpeed(stack: ItemStack): Float{
        return super.getSpeed(stack) * 1.35f
    }

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return ingredient.isOf(RegisterItem.PURESTEEL_INGOT)
    }

    override fun getMaxUseTime(stack: ItemStack): Int {
        return super.getMaxUseTime(stack) + 5
    }

    override fun hasGlint(stack: ItemStack): Boolean {
        return false
    }
}