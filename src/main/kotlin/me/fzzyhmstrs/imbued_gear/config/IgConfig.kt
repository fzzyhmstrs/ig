package me.fzzyhmstrs.imbued_gear.config

import me.fzzyhmstrs.fzzy_config.config_util.ConfigClass
import me.fzzyhmstrs.fzzy_config.config_util.ConfigSection
import me.fzzyhmstrs.fzzy_config.config_util.ReadMeText
import me.fzzyhmstrs.fzzy_config.config_util.SyncedConfigHelperV1.readOrCreateAndValidate
import me.fzzyhmstrs.fzzy_config.config_util.SyncedConfigWithReadMe
import me.fzzyhmstrs.fzzy_config.interfaces.OldClass
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedFloat
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedInt
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedLong
import me.fzzyhmstrs.fzzy_config.validated_field.map.ValidatedStringBoolMap
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.material.IgArmorMaterialsConfig
import me.fzzyhmstrs.imbued_gear.material.IgToolMaterialsConfig
import me.fzzyhmstrs.imbued_gear.tool.CracklingToolMaterial
import me.fzzyhmstrs.imbued_gear.tool.ScepterLvl4ToolMaterial
import net.minecraft.util.Identifier

object IgConfig:
    SyncedConfigWithReadMe(
        IG.MOD_ID,
        "README.txt",
        IG.MOD_ID,
        Header.Builder()
            .box("ia.readme.main_header.title")
            .space()
            .add("ia.readme.main_header.changelog")
            .literal()
            .add("1.19.3-01: Initial release of Imbued Ascendancy.")
            .space()
            .translate()
            .add("ia.readme.main_header.note")
            .space()
            .space()
            .build())
{

    private val itemsHeader = buildSectionHeader("items")

    class Items: ConfigClass(itemsHeader), OldClass<Items>{

        val livingFlame: LivingFlame()
        class LivingFlame(): ConfigSection(Header.Builder().space().add("ia.readme.items.living_flame").add("ia.readme.items.living_flame_2").build()){
            var effectDuration = ValidatedInt(600,Int.MAX_VALUE,0)
            var cooldown = ValidatedInt(3000,Int.MAX_VALUE,0)
        }
        
        var boneRattle = BoneRattle()
        class BoneRattle: ConfigSection(Header.Builder().space().add("ia.readme.items.bone_rattle").add("ia.readme.items.bone_rattle_2").build()){
            var duplicationChance = ValidatedFloat(0.25f,1f,0f)
            var duplicationDamage = ValidatedInt(20,100,0)
            var repairTime = ValidatedInt(300,Int.MAX_VALUE,0)
        }
        
        var ringOfSouls = RingOfSouls()
        class RingOfSouls: ConfigSection(Header.Builder().space().add("ia.readme.items.ring_of_souls").add("ia.readme.items.ring_of_souls_2").build()){
            var maxTier = ValidatedInt(10, Int.MAX_VALUE,1)
            var baseKillsPerTier = ValidatedInt(250, Int.MAX_VALUE,1)
            var killTierMultiplier = ValidatedFloat(2f, Float.MAX_VALUE,1f)
        }

        var crownOfSorrows = CrownOfSorrows()
        class CrownOfSorrows: ConfigSection(Header.Builder().space().add("ia.readme.items.crown_of_sorrows").add("ia.readme.items.crown_of_sorrows_2").build()){
            var activeDuration = ValidatedInt(80, Int.MAX_VALUE,1)
            var defense50Percent = ValidatedInt(10000, Int.MAX_VALUE,10)
            var regret50Percent = ValidatedInt(25000, Int.MAX_VALUE,25)
        }

        var scepters = Scepters()
        class Scepters: ConfigSection(Header.Builder().space().add("ia.readme.items.scepters").add("ia.readme.items.scepters_2").build()){
            @ReadMeText("ia.readme.items.scepters.cracklingDamage")
            var cracklingDamage = ValidatedFloat(CracklingToolMaterial.defaultAttackDamage(),20f,0f)
            var cracklingDurability = ValidatedInt(CracklingToolMaterial.defaultDurability(),5000,32)
            var cracklingCooldown = ValidatedLong(CracklingToolMaterial.baseCooldown(), Long.MAX_VALUE, CracklingToolMaterial.minCooldown())
            var lvl4Durability = ValidatedInt(ScepterLvl4ToolMaterial.defaultDurability(),15000,32)
            var lvl4Cooldown = ValidatedLong(ScepterLvl4ToolMaterial.baseCooldown(), Long.MAX_VALUE, CracklingToolMaterial.minCooldown())
        }

        override fun generateNewClass(): Items {
            return this
        }

    }

    private val materialsHeader = buildSectionHeader("materials")

    class Materials: ConfigClass(materialsHeader), OldClass<Materials>{

        var armor = Armor()

        class Armor: ConfigSection(Header.Builder().space().add("ia.readme.materials.armor_1").build()) {
            var celestial = IgArmorMaterialsConfig.CELESTIAL
            var champion = IgArmorMaterialsConfig.CHAMPION
            var elementalist = IgArmorMaterialsConfig.ELEMENTALIST
            var eternity = IgArmorMaterialsConfig.ETERNITY
            var lich_king = IgArmorMaterialsConfig.LICH_KING
            var scholar = IgArmorMaterialsConfig.SCHOLAR
            var spellblade = IgArmorMaterialsConfig.SPELLBLADE
            var void_mail = IgArmorMaterialsConfig.VOID_MAIL
            var warrior = IgArmorMaterialsConfig.WARRIOR
        }

        val tools = Tools()

        class Tools: ConfigSection(Header.Builder().space().add("ia.readme.materials.tools_1").build()) {
            var nihil = IgToolMaterialsConfig.NIHIL
            var radiant = IgToolMaterialsConfig.RADIANT
            var celestial = IgToolMaterialsConfig.CELESTIAL
        }
        
        
        override fun generateNewClass(): Materials {
            return this
        }

    }

    private val modifiersHeader = buildSectionHeader("modifiers")

    class Modifiers: ConfigClass(modifiersHeader), OldClass<Modifiers>{

        fun isModifierEnabled(id: Identifier): Boolean{
            return enabledModifiers[id.toString()]?:false
        }

        var enabledModifiers = ValidatedStringBoolMap(
            mapOf(),
            {str,_ -> Identifier.tryParse(str) != null},
            invalidEntryMessage = "Needs to be a valid modifier identifier string"
        )

        @ReadMeText("ia.readme.modifiers.voidShroudedMultiplier")
        var voidShroudedMultiplier = ValidatedFloat(0.84f,1.0f)
        var nihilBladeNothingnessChance = ValidatedFloat(0.15f,1.0f)
        var heliophobiaMultiplier = ValidatedFloat(2.0f,10.0f)
        var warriorsLightMultiplier = ValidatedFloat(0.9f,1.0f)
        var warriorsLightDamage = ValidatedFloat(2.0f,20f)

        var gear = GearSection()
        class GearSection: ConfigSection(Header.Builder().space().add("ia.readme.modifiers.gear").add("ia.readme.items.modifiers.gear_2").build()){
            var manaVampiricHitChance = ValidatedFloat(0.15f,1f,0f)
            var manaVampiricHitAmount = ValidatedInt(10,100,1)
            var manaVampiricKillAmount = ValidatedInt(15,150,1)
            var manaDrainingHitChance = ValidatedFloat(0.15f,1f,0f)
            var manaDrainingHitAmount = ValidatedInt(7,100,1)
            var manaDrainingKillAmount = ValidatedInt(10,150,1)
            var manaReactiveAmount = ValidatedInt(1,25,1)
        }

        override fun generateNewClass(): Modifiers {
            return this
        }

    }





    var items = readOrCreateAndValidate("items_v0.json", base = IG.MOD_ID) {Items()}
    var materials = readOrCreateAndValidate("materials_v0.json", base = IG.MOD_ID) {Materials()}
    var modifiers = readOrCreateAndValidate("modifiers_v0.json", base = IG.MOD_ID) {Modifiers()}

    private fun buildSectionHeader(name:String): Header{
        return Header.Builder().space().underoverscore("ia.readme.header.$name").add("ia.readme.header.$name.desc").space().build()
    }
}
