package me.fzzyhmstrs.imbued_gear.item.armor

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ArmorMaterial
import java.util.*

class ArchonsArmorItem(fzzyMaterial: ArmorMaterial, type: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, type, settings) {

    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(type.equipmentSlot))
        if (!SpChecker.spellPowerLoaded){
            when (type){
                Type.HELMET -> map.put(RegisterAttribute.SPELL_DAMAGE, EntityAttributeModifier(UUID.fromString("f3098976-80a1-11ee-b962-0242ac120002"),"archon_damage",0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                Type.CHESTPLATE -> map.put(RegisterAttribute.SPELL_DAMAGE, EntityAttributeModifier(UUID.fromString("f3098cdc-80a1-11ee-b962-0242ac120002"),"archon_damage",0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                Type.LEGGINGS -> map.put(RegisterAttribute.SPELL_DAMAGE, EntityAttributeModifier(UUID.fromString("f3098e3a-80a1-11ee-b962-0242ac120002"),"archon_damage",0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
                Type.BOOTS -> map.put(RegisterAttribute.SPELL_DAMAGE, EntityAttributeModifier(UUID.fromString("f30990ce-80a1-11ee-b962-0242ac120002"),"archon_damage",0.075,EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
            }
        } else {
            when(type) {
                Type.BOOTS -> {
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,"842c5cde-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.LIGHTNING,"842c5fae-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"842c613e-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                }
                Type.LEGGINGS -> {
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,"842c624c-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.LIGHTNING,"842c653a-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"842c6634-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                }
                Type.CHESTPLATE -> {
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,"842c674c-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.LIGHTNING,"842c685a-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"842c6954-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                }
                Type.HELMET -> {
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,"842c6a58-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.LIGHTNING,"842c6b66-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                    SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"842c6c74-8058-11ee-b962-0242ac120002", 3.0, EntityAttributeModifier.Operation.ADDITION, map)
                }
            }
        }
        map
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier>{
        if (slot == type.equipmentSlot) {
                return this.attributeModifiers
        }
        return super.getAttributeModifiers(slot)
    }
}