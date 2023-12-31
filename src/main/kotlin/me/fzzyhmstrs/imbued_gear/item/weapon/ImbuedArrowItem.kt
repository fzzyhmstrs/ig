package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.imbued_gear.entity.ImbuedArrowEntity
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.world.World

class ImbuedArrowItem(settings: Settings): ArrowItem(settings) {

    override fun createArrow(world: World, stack: ItemStack, shooter: LivingEntity): PersistentProjectileEntity {
        val arrowEntity = ImbuedArrowEntity(world, shooter)
        arrowEntity.initFromStack(stack)
        arrowEntity.setNoGravity(true)
        return arrowEntity
    }

    private val flavorText: MutableText by lazy{
        FlavorHelper.makeFlavorText(this)
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        FlavorHelper.addFlavorText(tooltip, context, flavorText, AcText.empty())
    }

}