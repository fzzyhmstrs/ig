package me.fzzyhmstrs.imbued_gear.tool

import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient


object ScepterLvl4ToolMaterial: ScepterToolMaterial(){
    override fun getDurability(): Int {
        return IgConfig.items.scepters.cosmicDurability.get()
    }
    fun defaultDurability(): Int{
        return 3550
    }
    override fun getMiningSpeedMultiplier(): Float {
        return 1.0f
    }
    override fun getAttackDamage(): Float {
        return 0.0f
    }
    override fun getAttackSpeed(): Double {
        return -3.0
    }
    override fun getMiningLevel(): Int {
        return 1
    }
    override fun getEnchantability(): Int {
        return 25
    }
    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(Items.CELESTINE)
    }
    override fun healCooldown(): Long {
        return IgConfig.items.scepters.cosmicCooldown.get()
    }

    override fun baseCooldown(): Long {
        return 50L
    }
    override fun scepterTier(): Int{
        return 4
    }
}
