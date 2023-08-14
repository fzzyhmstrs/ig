package me.fzzyhmstrs.imbued_gear.material

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterBlock
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem as RegisterAi
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvents

object IgArmorMaterialsConfig{
    val CELESTIAL = FzzyArmorMaterial .Builder("ig_celestial",SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE)
        .repairIngredient(Ingredient.ofItems(RegisterAi.CELESTINE))
        .enchantability(45)
        .protectionAmounts(6,11,9,6)
        .durabilityMultiplier(99,999)
        .knockbackResistance(0.2f)
        .toughness(5f)
        .build()
    val CHAMPION = FzzyArmorMaterial.Builder("ig_champion",SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND)
        .repairIngredient(Ingredient.ofItems(RegisterItem.PURESTEEL))
        .enchantability(18)
        .protectionAmounts(5,10,8,5)
        .durabilityMultiplier(55,200)
        .knockbackResistance(0.05f)
        .toughness(4.5f)
        .build()
    val ELEMENTALIST = FzzyArmorMaterial.Builder("ig_elementalist",SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND)
        .repairIngredient(Ingredient.ofItems(RegisterAi.SPARKING_GEM,RegisterAi.BLAZING_GEM, RegisterBlock.GLISTENING_ICE_ITEM))
        .enchantability(22)
        .protectionAmounts(3,8,6,3)
        .durabilityMultiplier(13)
        .toughness(2.0f)
        .build()
    val ETERNITY = FzzyArmorMaterial.Builder("ig_eternity",SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND)
        .repairIngredient(Ingredient.ofItems(RegisterAi.SPARKING_GEM,RegisterAi.BLAZING_GEM, RegisterBlock.GLISTENING_ICE_ITEM))
        .enchantability(22)
        .protectionAmounts(4,9,7,4)
        .durabilityMultiplier(39,150)
        .knockbackResistance(0.5f)
        .build()
    val LICH_KING = FzzyArmorMaterial.Builder("ig_lich_king",SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)
        .repairIngredient(Ingredient.ofItems(RegisterItem.WITHER_BONE))
        .enchantability(13)
        .protectionAmounts(2,7,6,2)
        .durabilityMultiplier(15)
        .knockbackResistance(0f)
        .toughness(2.0f)
        .build()
    val SCHOLAR = FzzyArmorMaterial.Builder("ig_scholar",SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)
        .repairIngredient(Ingredient.ofItems(RegisterAi.BOOK_OF_LORE))
        .enchantability(40)
        .protectionAmounts(2,6,5,2)
        .durabilityMultiplier(19)
        .build()
    val SPELLBLADE = FzzyArmorMaterial.Builder("ig_spellblade",SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA)
        .repairIngredient(Ingredient.ofItems(RegisterAi.LETHAL_GEM))
        .enchantability(10)
        .protectionAmounts(3,7,5,3)
        .durabilityMultiplier(25)
        .toughness(1.0f)
        .build()
    val VOID_MAIL = FzzyArmorMaterial.Builder("ig_void_mail",SoundEvents.ITEM_ARMOR_EQUIP_CHAIN)
        .repairIngredient(Ingredient.ofItems(RegisterItem.VOID_GEM))
        .enchantability(20)
        .protectionAmounts(3,8,6,3)
        .durabilityMultiplier(29)
        .toughness(2.0f)
        .build()
    val WARRIOR = FzzyArmorMaterial.Builder("ig_warrior",SoundEvents.ITEM_ARMOR_EQUIP_IRON)
        .repairIngredient(Ingredient.ofItems(RegisterItem.PURESTEEL))
        .enchantability(20)
        .protectionAmounts(3,8,6,3)
        .durabilityMultiplier(29)
        .toughness(2.0f)
        .build()
}
