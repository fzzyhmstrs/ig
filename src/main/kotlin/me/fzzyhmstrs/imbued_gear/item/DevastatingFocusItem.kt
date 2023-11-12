package me.fzzyhmstrs.imbued_gear.item

import com.google.common.collect.Multimap
import dev.emi.trinkets.api.SlotReference
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.amethyst_core.event.ModifyModifiersEvent
import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.gear_core.interfaces.AugmentTracking
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import java.util.*
import kotlin.math.atan

class DevastatingFocusItem(settings: Settings): AbstractAugmentJewelryItem(settings), DamageTracking, KillTracking, AugmentTracking, Modifiable {

    override fun getModifiers(
        stack: ItemStack,
        slot: SlotReference,
        entity: LivingEntity,
        uuid: UUID
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val map = super.getModifiers(stack, slot, entity, uuid)
        if(!SpChecker.spellPowerLoaded)
        map.put(RegisterAttribute.SPELL_DAMAGE, EntityAttributeModifier(uuid,"archon_damage",0.1,EntityAttributeModifier.Operation.MULTIPLY_TOTAL))
        else
            SpChecker.addSpellPowerAttribute(SpChecker.Power.FIRE,uuid.toString(), 3.0, EntityAttributeModifier.Operation.ADDITION, map)
            SpChecker.addSpellPowerAttribute(SpChecker.Power.LIGHTNING,uuid.toString(), 3.0, EntityAttributeModifier.Operation.ADDITION, map)
            SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,uuid.toString(), 3.0, EntityAttributeModifier.Operation.ADDITION, map)
        return map
    }


}