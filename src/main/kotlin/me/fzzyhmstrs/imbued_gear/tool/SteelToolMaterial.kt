package me.fzzyhmstrs.imbued_gear.tool

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

object SteelToolMaterial: ToolMaterial {
    override fun getDurability(): Int {
        return 400
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 7.5f
    }
    override fun getAttackDamage(): Float {
        return 2.5f
    }
    override fun getMiningLevel(): Int {
        return 2
    }
    override fun getEnchantability(): Int {
        return 15
    }

    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.STEEL_INGOT)
    }
}