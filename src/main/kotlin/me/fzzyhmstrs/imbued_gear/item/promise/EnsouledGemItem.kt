package me.fzzyhmstrs.imbued_gear.item.promise

import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class EnsouledGemItem(settings: Settings): IgnitedGemItem(settings) {

    override fun getModifier(): Identifier {
        return Identifier("amethyst_imbuement:traveler")
    }

    override fun giveTooltipHint(nbt: NbtCompound, stack: ItemStack, tooltip: MutableList<Text>) {
    }

    /*fun realityGemCheck(stack: ItemStack,startPos: BlockPos, endPos: BlockPos, inventory: PlayerInventory){
        if (startPos == endPos) return
        val nbt = stack.orCreateNbt
        val distance = nbt.getInt("teleported")
        val newDistance  = sqrt(endPos.getSquaredDistance(endPos)).toInt() + distance
        if (newDistance >= TELEPORT_TARGET){
            stack.decrement(1)
            val newStack = ItemStack(RegisterItem.REALITY_GEM)
            inventory.offerOrDrop(newStack)
            val player = inventory.player
            if (player is ServerPlayerEntity) {
                RegisterCriteria.IGNITE.trigger(player)
            }
        } else {
            nbt.putInt("teleported",newDistance)
        }
    }*/

}
