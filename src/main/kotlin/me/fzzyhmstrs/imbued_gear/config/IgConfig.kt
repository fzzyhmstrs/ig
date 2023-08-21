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
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
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
            .build()), SimpleSynchronousResourceReloadListener
{

    private val itemsHeader = buildSectionHeader("items")

    class Items: ConfigClass(itemsHeader), OldClass<Items>{

        var ensouledTarget = ValidatedInt(32,Int.MAX_VALUE,0)

        val livingFlame = LivingFlame()
        class LivingFlame(): ConfigSection(Header.Builder().space().add("ia.readme.items.living_flame").add("ia.readme.items.living_flame_2").build()){
            var effectDuration = ValidatedLong(600L, Long.MAX_VALUE,0L)
            var cooldown = ValidatedInt(3000,Int.MAX_VALUE,0)
        }
        
        var boneRattle = BoneRattle()
        class BoneRattle: ConfigSection(Header.Builder().space().add("ia.readme.items.bone_rattle").add("ia.readme.items.bone_rattle_2").build()){
            var duplicationChance = ValidatedFloat(0.25f,1f,0f)
            var duplicationDamage = ValidatedInt(20,100,0)
            var repairTime = ValidatedInt(200,Int.MAX_VALUE,0)
        }
        
        var ringOfSouls = RingOfSouls()
        class RingOfSouls: ConfigSection(Header.Builder().space().add("ia.readme.items.ring_of_souls").add("ia.readme.items.ring_of_souls_2").build()){
            var maxTier = ValidatedInt(10, 25,1)
            var baseKillsPerTier = ValidatedInt(250, Int.MAX_VALUE,1)
            var killTierMultiplier = ValidatedFloat(2f, Float.MAX_VALUE,1f)
        }

        var crownOfSorrows = CrownOfSorrows()
        class CrownOfSorrows: ConfigSection(Header.Builder().space().add("ia.readme.items.crown_of_sorrows").add("ia.readme.items.crown_of_sorrows_2").build()){
            var activeDuration = ValidatedLong(400, Long.MAX_VALUE,1)
            var defense50Percent = ValidatedInt(10000, Int.MAX_VALUE,10)
            var regret50Percent = ValidatedInt(25000, Int.MAX_VALUE,25)
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

        var tools = Tools()

        class Tools: ConfigSection(Header.Builder().space().add("ia.readme.materials.tools_1").build()) {
            var nihil = IgToolMaterialsConfig.NIHIL
            var radiant = IgToolMaterialsConfig.RADIANT
            var celestial = IgToolMaterialsConfig.CELESTIAL
            var scepterTier4 = IgToolMaterialsConfig.SCEPTER_TIER_4
            var crackling = IgToolMaterialsConfig.CRACKLING        }
        
        
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
        var nihilBladeNothingnessChance = ValidatedFloat(0.15f,1.0f)
        var nullAndVoidHitChance = ValidatedFloat(0.15f,1.0f)
        var voidStrikeHitChance = ValidatedFloat(0.15f,1.0f)
        var voidStrikeDamageMultiplier = ValidatedFloat(3f,8f)
        var trueSmiteDamageMultiplier = ValidatedFloat(4f,12f)
        var fleshRendingCriticalChance = ValidatedFloat(0.2f,1f)
        var radiantBastionShieldChance = ValidatedFloat(0.333333f,1f)

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

    override fun initConfig() {
        super.initConfig()
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(this)
    }

    override fun reload(manager: ResourceManager?) {
        items = readOrCreateAndValidate("items_v0.json", base = IG.MOD_ID) {Items()}
        materials = readOrCreateAndValidate("materials_v0.json", base = IG.MOD_ID) {Materials()}
        modifiers = readOrCreateAndValidate("modifiers_v0.json", base = IG.MOD_ID) {Modifiers()}
    }

    override fun getFabricId(): Identifier {
        return IG.identity("ig_configuration")
    }
}
