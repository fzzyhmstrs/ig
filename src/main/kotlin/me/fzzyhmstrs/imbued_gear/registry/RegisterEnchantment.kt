package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.fzzy_core.coding_util.AbstractConfigDisableEnchantment
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.enchantment.SpellAttributeEnchantment
import me.fzzyhmstrs.imbued_gear.scepter.BankaiAugment
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.*

object RegisterEnchantment {

    private fun <T: Enchantment> T.register(name: String): T{
        val id = IG.identity(name)
        val e1 = Registry.register(Registries.ENCHANTMENT,id, this)
        if (e1 is AbstractConfigDisableEnchantment){
            if (!e1.isEnabled()){
                IG.LOGGER.info("Augment $id is set as disabled in the configs!")
            }
        }
        if (e1 is ScepterAugment){
            if (!AugmentHelper.getAugmentEnabled(id.toString())) {
                IG.LOGGER.info("Augment $id is set as disabled in the configs!")
            }
        }
        return e1
    }

    val BANKAI = BankaiAugment().register("bankai")

    val SPELL_RAGE = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_DAMAGE,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.03, EntityAttributeModifier.Operation.MULTIPLY_BASE) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("c9213e78-4439-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("c92144cc-4439-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("c9214698-4439-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("c92147e2-4439-11ee-be56-0242ac120002")
        ))
    ).register("spell_rage")
    val SPELL_THRIFT = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_MANA_COST,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.03, EntityAttributeModifier.Operation.MULTIPLY_BASE) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("9dd762ae-44e0-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("9dd76664-44e0-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("9dd7681c-44e0-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("9dd76a88-44e0-11ee-be56-0242ac120002")
        ))
    ).register("spell_thrift")
    val SPELL_MAGNITUDE = SpellAttributeEnchantment(
        Enchantment.Rarity.VERY_RARE,
        RegisterAttribute.SPELL_AMPLIFIER,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, 1.0 * level, EntityAttributeModifier.Operation.ADDITION) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("b9e3ac22-44e1-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("b9e3af10-44e1-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("b9e3b050-44e1-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("b9e3b1ae-44e1-11ee-be56-0242ac120002")
        ))
    ).register("spell_magnitude")
    val SPELL_STABILITY = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_DURATION,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.0375, EntityAttributeModifier.Operation.MULTIPLY_BASE) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("1d90b3a0-44e2-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("1d90b76a-44e2-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("1d90b95e-44e2-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("1d90bb66-44e2-11ee-be56-0242ac120002")
        ))
    ).register("spell_stability")
    val SPELL_EXTENT = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_RANGE,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.0375, EntityAttributeModifier.Operation.MULTIPLY_BASE) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("b47df006-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("b47df344-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("b47df574-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("b47df6aa-44e3-11ee-be56-0242ac120002")
        ))
    ).register("spell_extent")
    val SPELL_ALACRITY = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_COOLDOWN,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.025, EntityAttributeModifier.Operation.MULTIPLY_BASE) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("376f6dba-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("376f704e-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("376f72ce-44e3-11ee-be56-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("376f7418-44e3-11ee-be56-0242ac120002")
        ))
    ).register("spell_alacrity")
    val SPELL_LUCK = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_CRITICAL_CHANCE,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.01, EntityAttributeModifier.Operation.ADDITION) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("bc55ab9a-80b9-11ee-b962-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("bc55b2de-80b9-11ee-b962-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("bc55b4b4-80b9-11ee-b962-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("bc55b5fe-80b9-11ee-b962-0242ac120002")
        ))
    ).register("spell_luck")
    val SPELL_BARBS = SpellAttributeEnchantment(
        Enchantment.Rarity.RARE,
        RegisterAttribute.SPELL_CRITICAL_MULTIPLIER,
        {uuid, name, level -> EntityAttributeModifier(uuid, name, level * 0.05, EntityAttributeModifier.Operation.ADDITION) },
        EnumMap(mapOf(
            EquipmentSlot.HEAD to UUID.fromString("fc31896a-8167-11ee-b962-0242ac120002"),
            EquipmentSlot.CHEST to UUID.fromString("fc318c58-8167-11ee-b962-0242ac120002"),
            EquipmentSlot.LEGS to UUID.fromString("fc318d8e-8167-11ee-b962-0242ac120002"),
            EquipmentSlot.FEET to UUID.fromString("fc319324-8167-11ee-b962-0242ac120002")
        ))
    ).register("spell_barbs")

    fun registerAll(){}
}