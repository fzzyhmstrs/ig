package me.fzzyhmstrs.imbued_gear.material


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
        .repairIngredient(Ingredient.ofItems(RegisterItem.VOIDSTEEL))
        .build()
    val RADIANT = ValidatedToolMaterial.Builder()
        .durability(1851)
        .miningSpeedMultiplier(10.5f)
        .attackDamage(4.5f)
        .miningLevel(MiningLevels.NETHERITE)
        .enchantability(15)
        .repairIngredient(Ingredient.ofItems(RegisterItem.PURESTEEL))
        .build()
    val CELESTIAL = ValidatedToolMaterial.Builder()
        .durability(2650)
        .miningSpeedMultiplier(14.5f)
        .attackDamage(9f)
        .miningLevel(MiningLevels.NETHERITE)
        .enchantability(45)
        .repairIngredient(Ingredient.ofItems(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem.CELESTINE))
        .build()
}
