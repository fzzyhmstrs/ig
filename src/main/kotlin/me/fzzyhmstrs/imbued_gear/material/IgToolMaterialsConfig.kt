package me.fzzyhmstrs.imbued_gear.material


import me.fzzyhmstrs.amethyst_imbuement.material.ScepterToolMaterial
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedToolMaterial
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.recipe.Ingredient

object IgToolMaterialsConfig{
    val NIHIL = ValidatedToolMaterial.Builder()
        .durability(333)
        .miningSpeedMultiplier(10.5f)
        .attackDamage(4.5f)
        .miningLevel(MiningLevels.NETHERITE)
        .enchantability(35)
        .repairIngredient(Ingredient.ofItems(RegisterItem.VOIDSTEEL_INGOT))
        .build()
    val RADIANT = ValidatedToolMaterial.Builder()
        .durability(1851)
        .miningSpeedMultiplier(10.5f)
        .attackDamage(6f)
        .miningLevel(MiningLevels.NETHERITE)
        .enchantability(15)
        .repairIngredient(Ingredient.ofItems(RegisterItem.PURESTEEL_INGOT))
        .build()
    val CELESTIAL = ValidatedToolMaterial.Builder()
        .durability(2650)
        .miningSpeedMultiplier(14.5f)
        .attackDamage(10f)
        .miningLevel(MiningLevels.NETHERITE)
        .enchantability(45)
        .repairIngredient(Ingredient.ofItems(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem.CELESTINE))
        .build()
    val SCEPTER_TIER_4 = ScepterToolMaterial.Builder(4)
        .attackSpeed(-3.0)
        .healCooldown(50L)
        .durability(3550,15000)
        .miningSpeedMultiplier(1f)
        .attackDamage(0f)
        .miningLevel(1)
        .enchantability(25)
        .repairIngredient(Ingredient.ofItems(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem.CELESTINE))
        .build()
    val CRACKLING = ScepterToolMaterial.Builder(2)
        .attackSpeed(-2.9)
        .healCooldown(200L)
        .durability(1561,10000)
        .miningSpeedMultiplier(7.5f)
        .attackDamage(4f)
        .miningLevel(4)
        .enchantability(25)
        .repairIngredient(Ingredient.ofItems(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem.LETHAL_GEM))
        .build()
}
