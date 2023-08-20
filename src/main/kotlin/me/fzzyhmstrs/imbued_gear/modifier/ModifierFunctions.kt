package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterStatus
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.registry.tag.EntityTypeTags
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

    val FLESH_RENDING_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { stack, user, _, _, amount ->
            val charged = stack.nbt?.getBoolean("charged") == true
            if (user.world.random.nextFloat() < IgConfig.modifiers.fleshRendingCriticalChance.get() || charged) {
                user.world.playSound(null,user.blockPos,SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,SoundCategory.PLAYERS,0.7f,1.2f)
                return@DamageFunction amount * if (charged) {
                    stack.orCreateNbt.putBoolean("charged",false)
                    stack.orCreateNbt.remove("charged_time")
                    2.0f
                } else {
                    1.5f
                }
            }
            amount
        }

    //attacker is the victim in this case
    val VOID_STRIKE_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, attacker, _, amount ->
            if (attacker?.hasStatusEffect(RegisterStatus.NIHILISM) == true ||
                attacker?.hasStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.CURSED) == true) {
                val rnd = user.world.random.nextFloat()
                if (rnd < IgConfig.modifiers.voidStrikeHitChance.get()) {
                    return@DamageFunction (amount * IgConfig.modifiers.voidStrikeDamageMultiplier.get())
                }
            }
            amount
        }

    val TRUE_SMITE_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, target, _, amount ->
            if (target == null) return@DamageFunction amount
            if (target.group == EntityGroup.UNDEAD && (target.health <= (target.maxHealth * 0.5f)))
                return@DamageFunction amount * IgConfig.modifiers.trueSmiteDamageMultiplier.get()
            amount
        }

    val MASTER_OF_ELEMENTS_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, source, amount ->
            if (source.isIn(RegisterTag.ELEMENTAL))
                return@DamageFunction amount * 0.5f
            amount
        }

    val MASTER_OF_ELEMENTS_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, target, _, amount ->
            if (target?.isOnFire == true || target?.isFrozen == true || target?.isFireImmune == true || target != null && target.type.isIn(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES))
                return@DamageFunction 1.25f * amount
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
