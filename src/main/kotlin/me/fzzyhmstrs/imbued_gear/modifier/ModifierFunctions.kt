package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import java.util.UUID

object ModifierFunctions {

    private val divinityMap: MutableMap<UUID,Long> = mutableMapOf()

    val PROTECTION_FROM_EVIL_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, attacker, _, amount ->
            if (attacker?.group == EntityGroup.UNDEAD)
                return@DamageFunction amount * 0.8f
            amount
        }

    val FIERY_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, attacker, _, amount ->
            if (attacker?.isOnFire == true)
                return@DamageFunction amount * 0.8f
            amount
        }

    val MASTER_OF_ELEMENTS_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, source, amount ->
            if (source.isIn(RegisterTag.ELEMENTAL))
                return@DamageFunction amount * 0.5f
            amount
        }

    val CELESTIAL_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, _, amount ->
            amount * 0.85f
        }

    val DIVINE_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, _, amount ->
            if (amount > user.health){
                val time = user.world.time
                if (divinityMap.computeIfAbsent(user.uuid){time - 1220L} + 1200L < time){
                    divinityMap[user.uuid] = time
                    user.addStatusEffect(StatusEffectInstance(StatusEffects.ABSORPTION,200,4))
                    user.addStatusEffect(StatusEffectInstance(StatusEffects.STRENGTH,200,4))
                    user.addStatusEffect(StatusEffectInstance(StatusEffects.HASTE,200,1))
                    user.world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(),SoundCategory.PLAYERS,1f,1f)
                    return@DamageFunction 0f
                }
            }
            amount
        }

    val MANA_REACTIVE_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, damage, amount ->
            if (damage.isIn(DamageTypeTags.BYPASSES_ARMOR)){
                ModifierConsumers.manaHealItems(user,IgConfig.modifiers.gear.manaReactiveAmount.get() * 2)
            } else {
                ModifierConsumers.manaHealItems(user,IgConfig.modifiers.gear.manaReactiveAmount.get())
            }
            amount
        }
}
