package me.fzzyhmstrs.imbued_gear.item.armor

import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.imbued_gear.armor.CelestialArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeType

class CelestialArmorItem(slot: EquipmentSlot, settings: Settings): ArmorItem(CelestialArmorMaterial(),slot,settings), Reactant {

    override fun canReact(
        stack: ItemStack,
        reagents: List<ItemStack>,
        player: PlayerEntity?,
        type: RecipeType<*>?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun react(stack: ItemStack, reagents: List<ItemStack>, player: PlayerEntity?, type: RecipeType<*>?) {
        TODO("Not yet implemented")
    }
}