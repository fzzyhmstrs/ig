package me.fzzyhmstrs.imbued_gear.material

import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedArmorMaterial
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedFloat
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedIngredient
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedInt
import me.fzzyhmstrs.fzzy_config.validated_field.list.ValidatedIdentifierList
import me.fzzyhmstrs.fzzy_config.validated_field.list.ValidatedSeries
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

open class FzzyArmorMaterial protected constructor(
    armorName: String,
    armorSoundEvent: SoundEvent,
    defaultModifiers: ValidatedIdentifierList,
    repairIngredientDefault: ValidatedIngredient,
    enchantabilityDefault: ValidatedInt,
    protectionAmountsDefault: ValidatedSeries<Int>,
    durabilityMultiplierDefault: ValidatedInt,
    knockbackResistanceDefault: ValidatedFloat,
    toughnessDefault: ValidatedFloat)
    :
    ValidatedArmorMaterial(
    armorName,
    armorSoundEvent,
    repairIngredientDefault,
    enchantabilityDefault,
    protectionAmountsDefault,
    durabilityMultiplierDefault,
    knockbackResistanceDefault,
    toughnessDefault)
{
    var modifiers = defaultModifiers

    fun getModifiers(): List<Identifier>{
        return modifiers.get()
    }

    open class Builder(name: String, soundEvent: SoundEvent): AbstractBuilder<FzzyArmorMaterial, Builder>(name, soundEvent){

        protected var m = ValidatedIdentifierList(listOf(),{true},"Needs to be a valid identifier.")

        override fun builderClass(): Builder {
            return this
        }

        override fun build(): FzzyArmorMaterial {
            return FzzyArmorMaterial(name,soundEvent,m,rI,e,pA,dM,kR,t)
        }
    }

}