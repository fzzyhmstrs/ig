@file:Suppress("unused")

package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_imbuement.item.AiItemSettings
import me.fzzyhmstrs.amethyst_imbuement.item.promise.GemOfPromiseItem
import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.item.*
import me.fzzyhmstrs.imbued_gear.item.weapon.*
import me.fzzyhmstrs.imbued_gear.tool.ScepterLvl4ToolMaterial
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Rarity
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier as RegisterModifier1

// don't know if this is better as a class or object. as an object it allows me to call it without needing to initialize an instance of it.
object RegisterTool {

    internal val regTool: MutableList<Item> = mutableListOf()

    private fun <T: Item> register(item: T, name: String): T{
        if (item is IgnitedGemItem){
            GemOfPromiseItem.register(item)
        }
        regTool.add(item)
        return Registry.register(Registries.ITEM, IG.identity(name), item)
    }

    //tools and weapons
    val CELESTIAL_TRIDENT = register(CelestialTridentItem(IgConfig.materials.tools.celestial,FabricItemSettings().maxDamage(2650).rarity(Rarity.EPIC)),"celestial_trident")
    //tex recipe
    val CHAMPIONS_TRIDENT = register(ChampionsTridentItem(IgConfig.materials.tools.radiant,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(1250).rarity(Rarity.RARE)),"champions_trident")
    val CRYSTALLINE_ARROW = register(CrystallineArrowItem(FabricItemSettings()),"crystalline_arrow")
    val IMBUED_ARROW = register(ImbuedArrowItem(FabricItemSettings()),"imbued_arrow")
    //val GEM_AND_STEEL = register(GemAndSteelItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(32)),"gem_and_steel")
    val NIHIL_BLADE = register(NihilBladeItem(IgConfig.materials.tools.nihil,FabricItemSettings()),"nihil_blade")
    val CRACKLING_SPELLBLADE = register(CracklingSpellbladeItem(FabricItemSettings()),"crackling_spellblade")
    //item model tex lang modifier
    val COSMOS = register(CosmicScepterItem(ScepterLvl4ToolMaterial,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.SCEPTER).rarity(Rarity.EPIC))
        .withModifiers(listOf(RegisterModifier1.COSMIC))
        ,"cosmos")

    /*//trinkets
    //model tex mods
    //warrior - increases damage against undead, reduces damage from undead
    val BLAZE_OF_LIGHT = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1), equipmentModifiers = listOf(), scepterModifiers = listOf()) ,"blaze_of_light")
    //model tex mods
    //lich - buffs summons, chance to echo summoning spells at durability expense
    val BONE_RATTLE = register(BoneRattleItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(100)) ,"bone_rattle")
    //saint
    val CADUCEUS = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(450), scepterModifiers = listOf(RegisterModifier.HEALERS_GRACE.modifierId, RegisterModifier.HEALING.modifierId, RegisterModifier1.HEALERS_REWARD.modifierId)) ,"caduceus")
    //champion
    val CROWN_OF_SORROWS = register(CrownOfSorrowsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(240)),"crown_of_sorrows")
    //altar
    val DIVINE_CORONET = register(DivineCoronetItem(FabricItemSettings().maxDamage(480)).withGlint(),"divine_coronet")
    //model tex mods
    //flame - spell cooldown, attack speed, chance to set things on fire
    val LIVING_FLAME = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1), equipmentModifiers = listOf(), scepterModifiers = listOf()) ,"living_flame")
    // mods
    //void - damaging has chance to reduce max health, magic resist, mana cost
    val NULL_AND_VOID = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1), equipmentModifiers = listOf(), scepterModifiers = listOf()) ,"null_and_void")
    //scholar
    val PENDANT_OF_MEMORIES = register(PendantOfMemoriesItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(250)),"pendant_of_memories")
    //blade
    val RING_OF_SOULS = register(RingOfSoulsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(240)),"ring_of_souls")
    */

    fun registerAll() {
    }
}
