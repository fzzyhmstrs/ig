package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeType

class DivineCoronetItem(settings: Settings): AbstractAugmentJewelryItem(settings), Reactant, ManaItem {
    override fun canReact(
        stack: ItemStack,
        reagents: List<ItemStack>,
        player: PlayerEntity?,
        type: RecipeType<*>?
    ): Boolean {
        return true
    }

    override fun react(stack: ItemStack, reagents: List<ItemStack>, player: PlayerEntity?, type: RecipeType<*>?) {
        TODO("Not yet implemented")
    }

    override fun getRepairTime(): Int {
        return 0
    }


}