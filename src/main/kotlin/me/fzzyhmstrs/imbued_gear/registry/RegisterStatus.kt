package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.effects.CustomStatusEffect
import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

object RegisterStatus {

    val NIHILISM: StatusEffect = CustomStatusEffect(StatusEffectCategory.HARMFUL,0x000028)
        .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,"bebdd92a-e5cd-11ed-a05b-0242ac120003",-4.0,EntityAttributeModifier.Operation.ADDITION)
    val SPELL_RAGE: StatusEffect = CustomStatusEffect(StatusEffectCategory.BENEFICIAL,0xD64420)
        .addAttributeModifier(RegisterAttribute.SPELL_DAMAGE,"a34f1f40-3e98-11ee-be56-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    val BLADE_RAGE: StatusEffect = CustomStatusEffect(StatusEffectCategory.BENEFICIAL,0x0275D3)
        .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,"a34f1f40-3e98-11ee-be56-0242ac120002",0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL)
    val SPELL_SHIELD: StatusEffect = CustomStatusEffect(StatusEffectCategory.BENEFICIAL,0x7400B7)

    fun registerAll(){
        FzzyPort.STATUS_EFFECT.register(IG.identity("nihilism"), NIHILISM)
        FzzyPort.STATUS_EFFECT.register(IG.identity("spell_rage"), SPELL_RAGE)
        FzzyPort.STATUS_EFFECT.register(IG.identity("blade_rage"), BLADE_RAGE)
        FzzyPort.STATUS_EFFECT.register(IG.identity("spell_shield"), SPELL_SHIELD)
    }
}
