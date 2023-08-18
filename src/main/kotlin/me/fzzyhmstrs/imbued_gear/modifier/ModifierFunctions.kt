package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.DamageTypeTags

object ModifierFunctions {

    val CELESTIAL_DAMAGE_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, _, _, amount ->
            amount * 0.8f
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
