package me.fzzyhmstrs.imbued_gear.tool

import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

object NihilBladeMaterial: ToolMaterial {
    override fun getDurability(): Int {
        return 333
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 10.5f
    }
    override fun getAttackDamage(): Float {
        return 4.5f
    }
    override fun getMiningLevel(): Int {
        return MiningLevels.NETHERITE
    }
    override fun getEnchantability(): Int {
        return 35
    }
    override fun getRepairIngredient(): Ingredient? {
        return Ingredient.ofItems(RegisterItem.NULL_ONYX)
    }
}