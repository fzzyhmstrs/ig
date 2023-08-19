package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.effects.CustomStatusEffect
import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object RegisterStatus {

    val NIHILISM: StatusEffect = CustomStatusEffect(StatusEffectCategory.HARMFUL,0x000028)
        .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,"bebdd92a-e5cd-11ed-a05b-0242ac120003",-4.0,EntityAttributeModifier.Operation.ADDITION)
    val BLADE_RAGE: StatusEffect = CustomStatusEffect(StatusEffectCategory.BENEFICIAL,0xD64420)
        .addAttributeModifier(RegisterAttribute.SPELL_DAMAGE,"a34f1f40-3e98-11ee-be56-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    val SPELL_RAGE: StatusEffect = CustomStatusEffect(StatusEffectCategory.BENEFICIAL,0x0275D3)
        .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,"a34f1f40-3e98-11ee-be56-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)

    fun registerAll(){
        Registry.register(Registries.STATUS_EFFECT, IG.identity("nihilism"), NIHILISM)
    }
}
