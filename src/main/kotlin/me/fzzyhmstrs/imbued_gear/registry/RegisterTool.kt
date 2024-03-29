@file:Suppress("unused")

package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.registry.ModifierRegistry
import me.fzzyhmstrs.amethyst_imbuement.config.AiConfig
import me.fzzyhmstrs.amethyst_imbuement.item.AiItemSettings
import me.fzzyhmstrs.amethyst_imbuement.item.promise.GemOfPromiseItem
import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.item.*
import me.fzzyhmstrs.imbued_gear.item.weapon.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
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
        return FzzyPort.ITEM.register(IG.identity(name), item)
    }

    //tools and weapons
    val CRYSTALLINE_ARROW = register(CrystallineArrowItem(FabricItemSettings()),"crystalline_arrow")
    val IMBUED_ARROW = register(ImbuedArrowItem(FabricItemSettings()),"imbued_arrow")
    val BAITING_ARROW = register(BaitingArrowItem(FabricItemSettings()),"baiting_arrow")
    val CELESTIAL_TRIDENT = register(CelestialTridentItem(IgConfig.materials.tools.celestial,FabricItemSettings().maxDamage(2650).rarity(Rarity.EPIC)),"celestial_trident")
    //tex recipe
    val CHAMPIONS_TRIDENT = register(ChampionsTridentItem(IgConfig.materials.tools.radiant,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(1250).rarity(Rarity.RARE)),"champions_trident")
    //val GEM_AND_STEEL = register(GemAndSteelItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(32)),"gem_and_steel")
    val NIHIL_BLADE = register(NihilBladeItem(IgConfig.materials.tools.nihil,FabricItemSettings().rarity(Rarity.RARE)),"nihil_blade")
    val RADIANT_BROADSWORD = register(RadiantBroadswordItem(IgConfig.materials.tools.radiant,FabricItemSettings().rarity(Rarity.RARE)),"radiant_broadsword")
    val WARRIORS_AXE = register(WarriorsAxeItem(IgConfig.materials.tools.radiant,FabricItemSettings().rarity(Rarity.RARE)),"warriors_axe")
    val CRACKLING_SPELLBLADE = register(CracklingSpellbladeItem(FabricItemSettings()),"crackling_spellblade")
    val HUNTERS_ARBALEST = register(HuntersArbalestItem(FabricItemSettings().rarity(Rarity.RARE).maxDamage(1250)), "hunters_arbalest")
    //item model tex lang modifier
    val COSMOS = register(CosmicScepterItem(IgConfig.materials.tools.scepterTier4,AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.SCEPTER).rarity(Rarity.EPIC)),"cosmos")

    //trinkets
    //warrior - increases damage against undead, reduces damage from undead
    val BLAZE_OF_LIGHT = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1).rarity(Rarity.RARE)/*, equipmentModifiers = listOf(RegisterModifier1.PROTECTION_FROM_EVIL.modifierId), scepterModifiers = listOf(RegisterModifier.SMITING.modifierId)*/) ,"blaze_of_light")
    //scholars
    val BOOK_OF_SECRETS = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1).rarity(Rarity.RARE)/*, equipmentModifiers = listOf(RegisterModifier1.SCHOLARLY.modifierId), scepterModifiers = listOf(RegisterModifier.SAVANT_ASPECT.modifierId)*/) ,"book_of_secrets")
    //lich - buffs summons, chance to echo summoning spells at durability expense
    val BONE_RATTLE = register(BoneRattleItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(AiConfig.items.manaItems.imbuedJewelryDurability.get()*2).rarity(Rarity.RARE)) ,"bone_rattle")
    //saint
    val CADUCEUS = register(CaduceusItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(AiConfig.items.manaItems.totemOfAmethystDurability.get()).rarity(Rarity.RARE)) ,"caduceus")
    //flame - spell cooldown, attack speed, chance to set things on fire
    val LIVING_FLAME = register(LivingFlameItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1).rarity(Rarity.RARE)) ,"living_flame")
    //void - damaging has chance to reduce max health, magic resist, mana cost
    val NULL_AND_VOID = register(SpecialityOffhandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxCount(1).rarity(Rarity.RARE)/*, equipmentModifiers = listOf(RegisterModifier1.NULL_SPACE.modifierId), scepterModifiers = listOf(ModifierRegistry.GREATER_THRIFTY.modifierId)*/) ,"null_and_void")
    //champion
    val CROWN_OF_SORROWS = register(CrownOfSorrowsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(AiConfig.items.manaItems.imbuedJewelryDurability.get()*2).rarity(Rarity.RARE)),"crown_of_sorrows")
    //altar
    val DIVINE_CORONET = register(DivineCoronetItem(FabricItemSettings().maxDamage(AiConfig.items.manaItems.imbuedJewelryDurability.get()*4).rarity(Rarity.EPIC)).withGlint(),"divine_coronet")
    //scholar
    val PENDANT_OF_MEMORIES = register(PendantOfMemoriesItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(250).rarity(Rarity.RARE)),"pendant_of_memories")
    val STRANGE_TIMEPIECE = register(StrangeTimepieceItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(AiConfig.items.manaItems.imbuedJewelryDurability.get()).rarity(Rarity.RARE)),"strange_timepiece")
    //blade
    val RING_OF_SOULS = register(RingOfSoulsItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).maxDamage(AiConfig.items.manaItems.imbuedJewelryDurability.get()*2).rarity(Rarity.RARE)),"ring_of_souls")
    //blade 2
    val ENERGETIC_BAND = register(EnergeticBandItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).rarity(Rarity.RARE).maxCount(1)),"energetic_band")

    val DEVASTATING_FOCUS = register(DevastatingFocusItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.EQUIPMENT).rarity(Rarity.RARE).maxCount(1)),"devastating_focus")


    fun registerAll() {
    }
}