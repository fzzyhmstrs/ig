package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class NihilBladeItem(material: ToolMaterial,settings: Settings): SwordItem(material,0,-1.5f,settings), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.NOTHINGNESS.modifierId)
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