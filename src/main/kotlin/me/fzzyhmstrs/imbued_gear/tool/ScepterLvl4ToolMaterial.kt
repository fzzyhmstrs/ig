package me.fzzyhmstrs.imbued_gear.tool

import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.recipe.Ingredient


object ScepterLvl4ToolMaterial: ScepterToolMaterial(){
    override fun getDurability(): Int {
        return IgConfig.items.scepters.lvl4Durability.get()
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
        return Ingredient.ofItems(RegisterItem.CELESTINE)
    }
    override fun healCooldown(): Long {
        return IgConfig.items.scepters.lvl4Cooldown.get()
    }

    override fun baseCooldown(): Long {
        return 50L
    }
    override fun scepterTier(): Int{
        return 4
    }
}
