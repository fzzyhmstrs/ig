package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity

object ModifierFunctions {

    val VOID_SHROUDED_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, damage, amount ->
            if (damage.isMagic){
                amount * IgConfig.modifiers.voidShroudedMultiplier.get()
            } else {
                amount
            }
        }

    val MANA_REACTIVE_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, damage, amount ->
            if (damage.isMagic){
                ModifierConsumers.manaHealItems(user,IgConfig.modifiers.gear.manaReactiveAmount.get() * 2)
            } else {
                ModifierConsumers.manaHealItems(user,IgConfig.modifiers.gear.manaReactiveAmount.get())
            }
            amount
        }

    val HELIOPHOBIA_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, _, _, amount ->
            if (user.world.isSkyVisible(user.blockPos) && user.world.isDay){
                amount * IgConfig.modifiers.heliophobiaMultiplier.get()
            } else {
                amount
            }
        }

    val WARRIORS_LIGHT_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, attacker, damage, amount ->
            if (attacker != null && attacker.isUndead){
                if (!attacker.isInvulnerable){
                    val source = if(user is PlayerEntity) DamageSource.player(user) else DamageSource.mob(user)
                    attacker.damage(source, IgConfig.modifiers.warriorsLightDamage.get())
                }
                amount * IgConfig.modifiers.warriorsLightMultiplier.get()
            } else {
                amount
            }
        }
}
