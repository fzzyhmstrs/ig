package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.modifier.ModifierPredicates
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.modifier.ConfigEquipmentModifier
import me.fzzyhmstrs.imbued_gear.modifier.ModifierConsumers
import me.fzzyhmstrs.imbued_gear.modifier.ModifierFunctions
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AbstractModifier<*>> = mutableListOf()
    internal val defaultEnabledMap: MutableMap<String,Boolean> = mutableMapOf()

    private val CHEAP_TOLL = ConstantLootNumberProvider.create(3f) // 96% probability
    // private val NORMAL_TOLL = ConstantLootNumberProvider.create(5f) //75% probability
    private val EXPENSIVE_TOLL = UniformLootNumberProvider.create(6f,7f) //50% chance of enough toll
    private val VERY_EXPENSIVE_TOLL = UniformLootNumberProvider.create(7f,9f) //25% chance of toll

    private fun buildModifier(
        modifierId: Identifier,
        target: EquipmentModifier.EquipmentModifierTarget = EquipmentModifier.EquipmentModifierTarget.NONE,
        weight: Int = 10,
        rarity: EquipmentModifier.Rarity = EquipmentModifier.Rarity.COMMON,
        persistent: Boolean = false,
        availableForSelection: Boolean = true
    ): EquipmentModifier{
        val key = modifierId.toString()
        if (!IgConfig.modifiers.enabledModifiers.containsKey(key)){
            defaultEnabledMap[key] = true
            IgConfig.modifiers.enabledModifiers.validateAndSet(defaultEnabledMap)
        } else{
            defaultEnabledMap[key] = IgConfig.modifiers.enabledModifiers[key]?:true
        }
        return ConfigEquipmentModifier(modifierId,target,weight,rarity,persistent, availableForSelection)
    }

    //scepter modifiers
    //val DAMNABLE_SUMMONS = AugmentModifier(Identifier(IG.MOD_ID,"damnable_summons"), cooldownModifier = -6.25, availableForRoll = false).withDamage(1f).withSpellToAffect(ModifierPredicates.SUMMONERS_PREDICATE).also { regMod.add(it) }
    //val CHAMPIONS_FAITH = AugmentModifier(Identifier(IG.MOD_ID,"champions_faith"), levelModifier = 1, availableForRoll = false).withDuration(0,0,25).withSpellToAffect(ModifierPredicates1.CHAMPIONS_PREDICATE).also { regMod.add(it) }
    val HEALERS_REWARD = AugmentModifier(Identifier(IG.MOD_ID,"healers_reward")).withConsumer(ModifierConsumers.HEALERS_REWARD_CONSUMER).withSpellToAffect(ModifierPredicates.HEALERS_PREDICATE).also { regMod.add(it) }
    val COSMIC = AugmentModifier(Identifier(IG.MOD_ID,"cosmic"), levelModifier = 1, cooldownModifier = -25.0, manaCostModifier = -25.0,availableForRoll = false).withDamage(2f).withDuration(0,0,20).also { regMod.add(it) }

    val WHISPER_OF_REGRET_SCEPTER = AugmentModifier(Identifier(IG.MOD_ID,"whisper_of_regret_scepter"),1)
        .withDamage(0.0f,0.0f,25f)
        .withAmplifier(2)
        .also { regMod.add(it) }

    //Random equipment modifiers
    //player experience
    val WISENED = buildModifier(
        Identifier(IG.MOD_ID,"wisened"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 3, EquipmentModifier.Rarity.EPIC)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,1.5, EntityAttributeModifier.Operation.ADDITION)
        .withToll(VERY_EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val VERY_EXPERIENCED = buildModifier(
        Identifier(IG.MOD_ID,"very_experienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 6, EquipmentModifier.Rarity.UNCOMMON)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,0.75, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(WISENED)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val EXPERIENCED = buildModifier(Identifier(IG.MOD_ID,"experienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 10)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,0.25, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(VERY_EXPERIENCED)
        .also { regMod.add(it) }
    val INEXPERIENCED = buildModifier(
        Identifier(IG.MOD_ID,"inexperienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 7, EquipmentModifier.Rarity.BAD)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,-0.25, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(EXPERIENCED)
        .also { regMod.add(it) }
    val INEPT = buildModifier(
        Identifier(IG.MOD_ID,"inept"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 2, EquipmentModifier.Rarity.REALLY_BAD)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,-1.0, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(INEXPERIENCED)
        .withToll(VERY_EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    
    //mana gaining modifier (repairing scepters and totems etc)
    val MANA_VAMPIRIC = buildModifier(Identifier(IG.MOD_ID,"mana_vampiric"), EquipmentModifier.EquipmentModifierTarget.WEAPON, 5, EquipmentModifier.Rarity.RARE)
        .withPostHit(ModifierConsumers.MANA_VAMPIRIC_HIT_CONSUMER)
        .withKilledOther(ModifierConsumers.MANA_VAMPIRIC_KILL_CONSUMER)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val MANA_DRAINING = buildModifier(Identifier(IG.MOD_ID,"mana_draining"), EquipmentModifier.EquipmentModifierTarget.WEAPON, 3, EquipmentModifier.Rarity.BAD)
        .withPostHit(ModifierConsumers.MANA_DRAINING_HIT_CONSUMER)
        .withKilledOther(ModifierConsumers.MANA_DRAINING_KILL_CONSUMER)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val MANA_REACTIVE = buildModifier(Identifier(IG.MOD_ID,"mana_reactive"), EquipmentModifier.EquipmentModifierTarget.ARMOR, 5, EquipmentModifier.Rarity.RARE)
        .withOnDamaged(ModifierFunctions.MANA_REACTIVE_DAMAGE_FUNCTION)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }

    /////////////////////////////////////////////////
        
    //Set and Special equipment modifiers
    val NOTHINGNESS = buildModifier(Identifier(IG.MOD_ID,"nothingness"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.NOTHINGNESS_HIT_CONSUMER)
        .also { regMod.add(it) }
    val WHISPER_OF_REGRET = EquipmentModifier(Identifier(IG.MOD_ID,"whisper_of_regret"), persistent = true, randomSelectable = false)
        .withModifiers(WHISPER_OF_REGRET_SCEPTER.modifierId)
        .also { regMod.add(it) }


    val VOID_SHROUDED = buildModifier(Identifier(IG.MOD_ID,"void_shrouded"), persistent = true, availableForSelection = false)
        .withOnDamaged(ModifierFunctions.VOID_SHROUDED_DAMAGE_FUNCTION)
        .also { regMod.add(it) }
    val MANA_KINDLED = buildModifier(Identifier(IG.MOD_ID,"mana_kindled"), persistent = true, availableForSelection = false)
        .withAttributeModifier(RegisterAttribute.SPELL_DAMAGE,0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .withAttributeModifier(RegisterAttribute.SPELL_COOLDOWN,-0.0625,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .also { regMod.add(it) }

    //val RULER_OF_THE_DAMNED = buildModifier(Identifier(IG.MOD_ID,"ruler_of_the_damned"), persistent = true, availableForSelection = false)
    //    .withModifiers(DAMNABLE_SUMMONS.modifierId)
    //    .withAttributeModifier(RegisterAttribute.SPELL_DURATION,0.0625,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    //    .also { regMod.add(it) }
    val HELIOPHOBIA = buildModifier(Identifier(IG.MOD_ID,"heliophobia"), persistent = true, availableForSelection = false)
        .withOnDamaged(ModifierFunctions.HELIOPHOBIA_DAMAGE_FUNCTION)
        .also { regMod.add(it) }
    val CHAMPIONS_RESOLVE = buildModifier(Identifier(IG.MOD_ID,"champions_resolve"), persistent = true, availableForSelection = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ARMOR,1.0,EntityAttributeModifier.Operation.ADDITION)
        .withAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,3.0,EntityAttributeModifier.Operation.ADDITION)
        .also { regMod.add(it) }
    //val CHAMPIONS_GRIT = buildModifier(Identifier(IG.MOD_ID,"champions_grit"), persistent = true, availableForSelection = false)
    //    .withModifiers(CHAMPIONS_FAITH.modifierId)
    //    .also { regMod.add(it) }
    val WARRIORS_LIGHT = buildModifier(Identifier(IG.MOD_ID,"warriors_light"), persistent = true, availableForSelection = false)
        .withModifiers(RegisterModifier.SMITING.modifierId)
        .withOnDamaged(ModifierFunctions.WARRIORS_LIGHT_DAMAGE_FUNCTION)
        .also { regMod.add(it) }
    val WARRIORS_PATH = buildModifier(Identifier(IG.MOD_ID,"warriors_path"), persistent = true, availableForSelection = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ARMOR,1.5,EntityAttributeModifier.Operation.ADDITION)
        .withAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.05,EntityAttributeModifier.Operation.ADDITION)
        .also { regMod.add(it) }
    val ETERNAL = buildModifier(Identifier(IG.MOD_ID,"eternal"), persistent = true, availableForSelection = false)
        .withAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,2.0,EntityAttributeModifier.Operation.ADDITION)
        .also { regMod.add(it) }
    val ETERNITY_SHROUDED = buildModifier(Identifier(IG.MOD_ID,"eternity_shrouded"), persistent = true, availableForSelection = false)
        .withAttributeModifier(RegisterAttribute.DAMAGE_MULTIPLICATION,-0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .also { regMod.add(it) }

    fun registerAll(){
        regMod.forEach {
            val id = it.modifierId
            defaultEnabledMap[id.toString()] = true
            ModifierRegistry.register(it)
        }
    }

}
