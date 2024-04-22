package me.fzzyhmstrs.imbued_gear.config

import me.fzzyhmstrs.fzzy_config.annotations.ConvertFrom
import me.fzzyhmstrs.fzzy_config.annotations.RequiresRestart
import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigSection
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedStringMap
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedString
import me.fzzyhmstrs.fzzy_config.validation.number.*
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.material.IgArmorMaterialsConfig
import me.fzzyhmstrs.imbued_gear.material.IgToolMaterialsConfig
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.Identifier

object IgConfig {

    @ConvertFrom("items_v0.json",IG.MOD_ID)
    class Items: Config(IG.identity("items_config")){

        var ensouledTarget = ValidatedInt(32,Int.MAX_VALUE,0)
        var lootChanceMultiplier = ValidatedFloat(1f,10f,0f)

        val livingFlame = LivingFlame()
        class LivingFlame(): ConfigSection(){
            var effectDuration = ValidatedLong(600L, Long.MAX_VALUE,0L)
            var cooldown = ValidatedInt(3000,Int.MAX_VALUE,0)
        }

        var boneRattle = BoneRattle()
        class BoneRattle: ConfigSection(){
            var duplicationChance = ValidatedFloat(0.25f,1f,0f)
            var duplicationDamage = ValidatedInt(20,100,0)
            var repairTime = ValidatedInt(200,Int.MAX_VALUE,0)
        }

        var ringOfSouls = RingOfSouls()
        class RingOfSouls: ConfigSection(){
            var maxTier = ValidatedInt(10, 25,1)
            var baseKillsPerTier = ValidatedInt(250, Int.MAX_VALUE,1)
            var killTierMultiplier = ValidatedFloat(2f, Float.MAX_VALUE,1f)
        }

        var crownOfSorrows = CrownOfSorrows()
        class CrownOfSorrows: ConfigSection(){
            var activeDuration = ValidatedLong(400, Long.MAX_VALUE,1)
            var defense50Percent = ValidatedInt(10000, Int.MAX_VALUE,10)
            var regret50Percent = ValidatedInt(25000, Int.MAX_VALUE,25)
        }
    }

    @ConvertFrom("materials_v2.json",IG.MOD_ID)
    class Materials: Config(IG.identity("materials_config")) {

        var armor = Armor()

        @RequiresRestart
        class Armor : ConfigSection() {
            var archon = IgArmorMaterialsConfig.ARCHON
            var celestial = IgArmorMaterialsConfig.CELESTIAL
            var champion = IgArmorMaterialsConfig.CHAMPION
            var elementalist = IgArmorMaterialsConfig.ELEMENTALIST
            var eternity = IgArmorMaterialsConfig.ETERNITY
            var hunter = IgArmorMaterialsConfig.HUNTER
            var lich_king = IgArmorMaterialsConfig.LICH_KING
            var scholar = IgArmorMaterialsConfig.SCHOLAR
            var spellblade = IgArmorMaterialsConfig.SPELLBLADE
            var void_mail = IgArmorMaterialsConfig.VOID_MAIL
            var warrior = IgArmorMaterialsConfig.WARRIOR
        }

        var tools = Tools()

        @RequiresRestart
        class Tools : ConfigSection() {
            var nihil = IgToolMaterialsConfig.NIHIL
            var radiant = IgToolMaterialsConfig.RADIANT
            var celestial = IgToolMaterialsConfig.CELESTIAL
            var scepterTier4 = IgToolMaterialsConfig.SCEPTER_TIER_4
            var crackling = IgToolMaterialsConfig.CRACKLING
        }
    }

    @ConvertFrom("modifiers_v0.json",IG.MOD_ID)
    class Modifiers: Config(IG.identity("modifiers_config")) {

        fun isModifierEnabled(id: Identifier): Boolean{
            return enabledModifiers[id.toString()]?:false
        }

        var enabledModifiers = ValidatedStringMap(mapOf(), ValidatedString(), ValidatedBoolean())

        var nihilBladeNothingnessChance = ValidatedFloat(0.15f,1.0f)
        var nullAndVoidHitChance = ValidatedFloat(0.15f,1.0f)
        var voidStrikeHitChance = ValidatedFloat(0.15f,1.0f)
        var voidStrikeDamageMultiplier = ValidatedFloat(3f,8f)
        var trueSmiteDamageMultiplier = ValidatedFloat(4f,12f)
        var fleshRendingCriticalChance = ValidatedFloat(0.2f,1f)
        var radiantBastionShieldChance = ValidatedFloat(0.333333f,1f)

        var gear = GearSection()
        class GearSection: ConfigSection(){
            var manaVampiricHitChance = ValidatedFloat(0.15f,1f,0f)
            var manaVampiricHitAmount = ValidatedInt(10,100,1)
            var manaVampiricKillAmount = ValidatedInt(15,150,1)
            var manaDrainingHitChance = ValidatedFloat(0.15f,1f,0f)
            var manaDrainingHitAmount = ValidatedInt(7,100,1)
            var manaDrainingKillAmount = ValidatedInt(10,150,1)
            var manaReactiveAmount = ValidatedInt(1,25,1)
        }
    }

    @ConvertFrom("enchants_v1.json",IG.MOD_ID)
    class Enchants: Config(IG.identity("enchants_config")) {

        fun isEnchantEnabled(enchantment: Enchantment): Boolean{
            val id = (FzzyPort.ENCHANTMENT.getId(enchantment) ?: return true).toString()
            return enabledEnchants[id] ?: true
        }

        fun getMaxLevel(enchantment: Enchantment, fallback: Int): Int{
            val id = (FzzyPort.ENCHANTMENT.getId(enchantment) ?: return fallback).toString()
            return enchantMaxLevels[id]?:fallback
        }

        var enabledEnchants = ValidatedStringMap(mapOf(
            "imbued_gear:spell_rage" to true,
            "imbued_gear:spell_thrift" to true,
            "imbued_gear:spell_magnitude" to true,
            "imbued_gear:spell_stability" to true,
            "imbued_gear:spell_extent" to true,
            "imbued_gear:spell_alacrity" to true),
            ValidatedString(),
            ValidatedBoolean())

        var enchantMaxLevels = ValidatedStringMap(mapOf(
            "imbued_gear:spell_rage" to 3,
            "imbued_gear:spell_thrift" to 3,
            "imbued_gear:spell_magnitude" to 1,
            "imbued_gear:spell_stability" to 3,
            "imbued_gear:spell_extent" to 3,
            "imbued_gear:spell_alacrity" to 3,
            "imbued_gear:spell_luck" to 3,
            "imbued_gear:spell_barbs" to 3),
            ValidatedString(),
            ValidatedInt(1,7,1))

    }

    var items: Items = ConfigApi.registerAndLoadConfig({Items()})
    var materials: Materials = ConfigApi.registerAndLoadConfig({Materials()})
    var modifiers: Modifiers = ConfigApi.registerAndLoadConfig({Modifiers()})
    var enchants: Enchants = ConfigApi.registerAndLoadConfig({Enchants()})


    fun initConfig() {
    }
}