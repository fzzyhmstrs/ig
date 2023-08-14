package me.fzzyhmstrs.imbued_gear.tool

import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterToolMaterial
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.recipe.Ingredient

object CracklingToolMaterial: ScepterToolMaterial() {
    override fun getDurability(): Int {
        return IgConfig.items.scepters.cracklingDurability.get()
    }
    fun defaultDurability(): Int{
        return 1561
    }

    override fun getMiningSpeedMultiplier(): Float {
        return 7.5f
    }
    override fun getAttackDamage(): Float {
        return IgConfig.items.scepters.cracklingDamage.get()
    }
    fun defaultAttackDamage(): Float {
        return 4f
    }

    override fun getAttackSpeed(): Double {
        return -2.4
    }

    override fun getMiningLevel(): Int {
        return 4
    }
    override fun getEnchantability(): Int {
        return 35
    }

    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(RegisterItem.SPARKING_GEM)
    }

    override fun healCooldown(): Long {
        return IgConfig.items.scepters.cracklingCooldown.get()
    }

    override fun baseCooldown(): Long {
        return 600
    }

    override fun minCooldown(): Long {
        return 60
    }

    override fun scepterTier(): Int {
        return 2
    }
}