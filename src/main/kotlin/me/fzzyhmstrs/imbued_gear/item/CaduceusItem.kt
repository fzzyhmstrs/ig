package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.item_util.interfaces.Flavorful
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.interfaces.*
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.ShieldItem
import net.minecraft.registry.Registries
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.world.World

class CaduceusItem(settings: Settings)
    : 
    ShieldItem(settings), Modifiable, DamageTracking, AttributeTracking, HitTracking, KillTracking, ModifierTracking
{

    override fun defaultModifiers(type: ModifierHelperType?): MutableList<Identifier> {
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
