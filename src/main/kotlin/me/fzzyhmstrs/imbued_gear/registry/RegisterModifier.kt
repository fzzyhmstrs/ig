package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpCompat
import me.fzzyhmstrs.amethyst_core.event.AfterSpellEvent
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.modifier.ModifierPredicates
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.interfaces.ActiveGearSetsTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.gear_core.set.GearSets
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.item.RingOfSoulsItem
import me.fzzyhmstrs.imbued_gear.modifier.ConfigEquipmentModifier
import me.fzzyhmstrs.imbued_gear.modifier.ModifierConsumers
import me.fzzyhmstrs.imbued_gear.modifier.ModifierFunctions
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import kotlin.math.max

@Suppress("unused", "MemberVisibilityCanBePrivate")
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
    //val DAMNABLE_SUMMONS = AugmentModifier(IG.identity("damnable_summons"), cooldownModifier = -6.25, availableForRoll = false).withDamage(1f).withSpellToAffect(ModifierPredicates.SUMMONERS_PREDICATE).also { regMod.add(it) }
    //val CHAMPIONS_FAITH = AugmentModifier(IG.identity("champions_faith"), levelModifier = 1, availableForRoll = false).withDuration(0,0,25).withSpellToAffect(ModifierPredicates1.CHAMPIONS_PREDICATE).also { regMod.add(it) }
    val HEALERS_REWARD = AugmentModifier(IG.identity("healers_reward")).withConsumer(ModifierConsumers.HEALERS_REWARD_CONSUMER).withSpellToAffect(ModifierPredicates.HEALERS_PREDICATE).also { regMod.add(it) }
    val COSMIC = AugmentModifier(IG.identity("cosmic"), levelModifier = 1, cooldownModifier = -25.0, manaCostModifier = -25.0).withDamage(2f).withDuration(0,0,20).also { regMod.add(it) }

    val CALL_OF_THE_VOID = AugmentModifier(IG.identity("call_of_the_void"),cooldownModifier = -10.0, manaCostModifier = 10.0).withConsumer(ModifierConsumers.CALL_OF_THE_VOID_CONSUMER).also { regMod.add(it) }

    val WHISPER_OF_REGRET_SCEPTER = AugmentModifier(IG.identity("whisper_of_regret_scepter"),1)
        .withDamage(0.0f,0.0f,25f)
        .withAmplifier(2)
        .also { regMod.add(it) }
    val KINDLED_SCEPTER = AugmentModifier(IG.identity("kindled_scepter"), cooldownModifier = -60.0)
        .also { regMod.add(it) }

    //Random equipment modifiers
    //player experience
    val WISENED = buildModifier(
        IG.identity("wisened"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 3, EquipmentModifier.Rarity.EPIC)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,1.5, EntityAttributeModifier.Operation.ADDITION)
        .withToll(VERY_EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val VERY_EXPERIENCED = buildModifier(
        IG.identity("very_experienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 6, EquipmentModifier.Rarity.UNCOMMON)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,0.75, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(WISENED)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val EXPERIENCED = buildModifier(IG.identity("experienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 10)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,0.25, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(VERY_EXPERIENCED)
        .also { regMod.add(it) }
    val INEXPERIENCED = buildModifier(
        IG.identity("inexperienced"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 7, EquipmentModifier.Rarity.BAD)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,-0.25, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(EXPERIENCED)
        .also { regMod.add(it) }
    val INEPT = buildModifier(
        IG.identity("inept"), EquipmentModifier.EquipmentModifierTarget.WEAPON_AND_TRINKET, 2, EquipmentModifier.Rarity.REALLY_BAD)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,-1.0, EntityAttributeModifier.Operation.ADDITION)
        .withDescendant(INEXPERIENCED)
        .withToll(VERY_EXPENSIVE_TOLL)
        .also { regMod.add(it) }

    //mana gaining modifier (repairing scepters and totems etc)
    val MANA_VAMPIRIC = buildModifier(IG.identity("mana_vampiric"), EquipmentModifier.EquipmentModifierTarget.WEAPON, 5, EquipmentModifier.Rarity.RARE)
        .withPostHit(ModifierConsumers.MANA_VAMPIRIC_HIT_CONSUMER)
        .withKilledOther(ModifierConsumers.MANA_VAMPIRIC_KILL_CONSUMER)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val MANA_DRAINING = buildModifier(IG.identity("mana_draining"), EquipmentModifier.EquipmentModifierTarget.WEAPON, 3, EquipmentModifier.Rarity.BAD)
        .withPostHit(ModifierConsumers.MANA_DRAINING_HIT_CONSUMER)
        .withKilledOther(ModifierConsumers.MANA_DRAINING_KILL_CONSUMER)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }
    val MANA_REACTIVE = buildModifier(IG.identity("mana_reactive"), EquipmentModifier.EquipmentModifierTarget.ARMOR, 5, EquipmentModifier.Rarity.RARE)
        .withOnDamaged(ModifierFunctions.MANA_REACTIVE_DAMAGE_FUNCTION)
        .withToll(EXPENSIVE_TOLL)
        .also { regMod.add(it) }

    ////
    val COWARDLY = buildModifier(IG.identity("cowardly"), EquipmentModifier.EquipmentModifierTarget.WEAPON, 1, EquipmentModifier.Rarity.EPIC)
        .withOnAttack(ModifierFunctions.COWARDLY_ATTACK_FUNCTION)
        .withToll(VERY_EXPENSIVE_TOLL)
        .also { regMod.add(it) }

    /////////////////////////////////////////////////

    //Set and Special equipment modifiers
    val SCHOLARLY = buildModifier(IG.identity("scholarly"), persistent = true, availableForSelection = false)
        .withAttributeModifier(
            RegisterAttribute.PLAYER_EXPERIENCE,1.0, EntityAttributeModifier.Operation.ADDITION)
        .withCustomFormatting(Formatting.BLUE)
        .also { regMod.add(it) }
    val PROTECTION_FROM_EVIL = buildModifier(IG.identity("protection_from_evil"), persistent = true, availableForSelection = false)
        .withOnDamaged(ModifierFunctions.PROTECTION_FROM_EVIL_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.GOLD)
        .also { regMod.add(it) }
    val NOTHINGNESS = buildModifier(IG.identity("nothingness"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.NOTHINGNESS_HIT_CONSUMER)
        .withCustomFormatting(Formatting.DARK_BLUE)
        .also { regMod.add(it) }
    val NULL_SPACE = buildModifier(IG.identity("null_space"), persistent = true, availableForSelection = false)
        .withAttributeModifier(RegisterAttribute.MAGIC_RESISTANCE,0.1,EntityAttributeModifier.Operation.ADDITION)
        .withPostHit(ModifierConsumers.NULL_SPACE_HIT_CONSUMER)
        .withCustomFormatting(Formatting.DARK_BLUE)
        .also { regMod.add(it) }
    val RADIANT_BASTION = buildModifier(IG.identity("radiant_bastion"), persistent = true, availableForSelection = false)
        .withKilledOther(ModifierConsumers.RADIANT_BASTION_KILL_CONSUMER)
        .withCustomFormatting(Formatting.GOLD)
        .also { regMod.add(it) }
    val RADIANT_DEVOTION = buildModifier(IG.identity("radiant_devotion"), persistent = true, availableForSelection = false)
        .withKilledOther(ModifierConsumers.RADIANT_DEVOTION_KILL_CONSUMER)
        .withCustomFormatting(Formatting.GOLD)
        .also { regMod.add(it) }
    val RADIANT_ABSOLUTION = buildModifier(IG.identity("radiant_absolution"), persistent = true, availableForSelection = false)
        .withKilledOther(ModifierConsumers.RADIANT_ABSOLUTION_KILL_CONSUMER)
        .withCustomFormatting(Formatting.GOLD)
        .also { regMod.add(it) }
    val FLESH_RENDING = buildModifier(IG.identity("flesh_rending"), persistent = true, availableForSelection = false)
        .withOnAttack(ModifierFunctions.FLESH_RENDING_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.GOLD)
        .also { regMod.add(it) }
    val WHISPER_OF_REGRET = EquipmentModifier(IG.identity("whisper_of_regret"), persistent = true, randomSelectable = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,3.0,EntityAttributeModifier.Operation.ADDITION)
        .withAttributeModifier(EntityAttributes.GENERIC_ARMOR,3.0,EntityAttributeModifier.Operation.ADDITION)
        .withCustomFormatting(Formatting.DARK_PURPLE)
        .also { regMod.add(it) }
    val KINDLED = buildModifier(IG.identity("kindled"), persistent = true, availableForSelection = false)
        .withAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,0.2,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .withAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
        .withCustomFormatting(Formatting.RED)
        .also { regMod.add(it) }
    val CELESTIAL = EquipmentModifier(IG.identity("celestial"), persistent = true, randomSelectable = false)
        .withOnDamaged(ModifierFunctions.CELESTIAL_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.AQUA, Formatting.BOLD)
        .also { regMod.add(it) }
    val DIVINE = EquipmentModifier(IG.identity("divine"), persistent = true, randomSelectable = false)
        .withOnDamaged(ModifierFunctions.DIVINE_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.AQUA, Formatting.BOLD)
        .also { regMod.add(it) }
    val FIERY = EquipmentModifier(IG.identity("fiery"), persistent = true, randomSelectable = false)
        .withPostHit(ModifierConsumers.FIERY_HIT_CONSUMER)
        .withOnDamaged(ModifierFunctions.FIERY_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.RED, Formatting.BOLD)
        .also { regMod.add(it) }
    val MASTER_OF_ELEMENTS = EquipmentModifier(IG.identity("master_of_elements"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.MASTER_OF_ELEMENTS_ATTACK_FUNCTION)
        .withOnDamaged(ModifierFunctions.MASTER_OF_ELEMENTS_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.RED, Formatting.BOLD)
        .also { regMod.add(it) }
    val HORDE_MASTER = EquipmentModifier(IG.identity("horde_master"), persistent = true, randomSelectable = false)
        .withTick(ModifierConsumers.HORDE_MASTER_TICK_CONSUMER)
        .withCustomFormatting(Formatting.DARK_GREEN, Formatting.BOLD)
        .also { regMod.add(it) }
    val LUST_FOR_COMBAT = EquipmentModifier(IG.identity("lust_for_combat"), persistent = true, randomSelectable = false)
        .withKilledOther(ModifierConsumers.LUST_FOR_COMBAT_KILL_CONSUMER)
        .withCustomFormatting(Formatting.RED, Formatting.ITALIC)
        .also { regMod.add(it) }
    val VOIDS_ECHO = EquipmentModifier(IG.identity("voids_echo"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.VOIDS_ECHO_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.DARK_BLUE, Formatting.BOLD)
        .also { regMod.add(it) }
    val TRUE_SMITE = EquipmentModifier(IG.identity("true_smite"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.TRUE_SMITE_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.GOLD, Formatting.BOLD)
        .also { regMod.add(it) }
    val HUNTERS_PRECISION = EquipmentModifier(IG.identity("hunters_precision"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.HUNTERS_PRECISION_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.DARK_GREEN, Formatting.BOLD)
        .also { regMod.add(it) }
    val HUNTERS_SWIFTNESS = EquipmentModifier(IG.identity("hunters_swiftness"), persistent = true, randomSelectable = false)
        .withOnDamaged(ModifierFunctions.HUNTERS_SWIFTNESS_DAMAGE_FUNCTION)
        .withCustomFormatting(Formatting.DARK_GREEN, Formatting.BOLD)
        .also { regMod.add(it) }
    val MANA_CASCADE = EquipmentModifier(IG.identity("mana_cascade"), persistent = true, randomSelectable = false)
        .withCustomFormatting(Formatting.LIGHT_PURPLE)
        .also { regMod.add(it) }
    val SPELL_SHIELD = EquipmentModifier(IG.identity("spell_shield"), persistent = true, randomSelectable = false)
        .withTick(ModifierConsumers.SPELL_SHIELD_TICK_CONSUMER)
        .withCustomFormatting(Formatting.LIGHT_PURPLE)
        .also { regMod.add(it) }

    private val archonsGearSet = Identifier("gear_core:gear_core_sets/archons_set.json")

    fun registerAll(){

        RingOfSoulsItem.registerAll()

        regMod.forEach {
            val id = it.modifierId
            defaultEnabledMap[id.toString()] = true
            ModifierRegistry.register(it)
        }
        AfterSpellEvent.EVENT.register{ _, user, _, _ ->
            val sets = (user as ActiveGearSetsTracking).gear_core_getActiveSets()
            val level = sets[GearSets.getGearSet(archonsGearSet)] ?: 0
            if (!SpChecker.spellPowerLoaded && level >= 4) {
                val rage = user.getStatusEffect(RegisterStatus.SPELL_RAGE)
                val amp = rage?.amplifier?.plus(1) ?: 0
                val dur = 100
                user.addStatusEffect(StatusEffectInstance(RegisterStatus.BLADE_RAGE, dur, max(amp, 9)))
            }
        }
        SpCompat.AFTER_CAST.register{_, user, _, _, _ ->
            val sets = (user as ActiveGearSetsTracking).gear_core_getActiveSets()
            val level = sets[GearSets.getGearSet(archonsGearSet)] ?: 0
            if (level < 4) return@register
            val fireEffect = FzzyPort.STATUS_EFFECT.get(Identifier("spell_power:fire"))
            if (fireEffect != null) {
                val fire = user.getStatusEffect(fireEffect)
                val amp = fire?.amplifier?.plus(1) ?: 0
                val dur = 100
                user.addStatusEffect(StatusEffectInstance(fireEffect, dur, max(amp,9)))
            }
            val lightningEffect = FzzyPort.STATUS_EFFECT.get(Identifier("spell_power:lightning"))
            if (lightningEffect != null) {
                val lightning = user.getStatusEffect(lightningEffect)
                val amp = lightning?.amplifier?.plus(1) ?: 0
                val dur = 100
                user.addStatusEffect(StatusEffectInstance(lightningEffect, dur, max(amp,9)))
            }
            val frostEffect = FzzyPort.STATUS_EFFECT.get(Identifier("spell_power:frost"))
            if (frostEffect != null) {
                val frost = user.getStatusEffect(frostEffect)
                val amp = frost?.amplifier?.plus(1) ?: 0
                val dur = 100
                user.addStatusEffect(StatusEffectInstance(frostEffect, dur, max(amp,9)))
            }
        }
    }

}