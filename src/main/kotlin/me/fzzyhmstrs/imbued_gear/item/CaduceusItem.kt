package me.fzzyhmstrs.imbued_gear.item

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.interfaces.*
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.ShieldItem
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class CaduceusItem(settings: Settings)
    : 
    ShieldItem(settings), Modifiable, DamageTracking, AttributeTracking, HitTracking, KillTracking, ModifierTracking
{

    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(EquipmentSlot.OFFHAND))
        SpChecker.addSpellPowerAttribute(SpChecker.Power.HEALING,"341918ec-80d8-11ee-b962-0242ac120002",2.0, EntityAttributeModifier.Operation.ADDITION, map)
        map
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
        if (slot == EquipmentSlot.OFFHAND)
            return attributeModifiers
        return super.getAttributeModifiers(slot)
    }

    override fun defaultModifiers(type: ModifierHelperType<*>?): MutableList<Identifier> {
        if (type == ModifierHelper.getType()) return mutableListOf(RegisterModifier.HEALERS_GRACE.modifierId, RegisterModifier.HEALING.modifierId, me.fzzyhmstrs.imbued_gear.registry.RegisterModifier.HEALERS_REWARD.modifierId)
        return super.defaultModifiers(type)
    }

    private val flavorText: MutableText by lazy{
        FlavorHelper.makeFlavorText(this)
    }

    private val flavorTextDesc: MutableText by lazy{
        FlavorHelper.makeFlavorTextDesc(this)
    }


    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        FlavorHelper.addFlavorText(tooltip, context, flavorText, flavorTextDesc)
    }

}
