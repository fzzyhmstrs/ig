package me.fzzyhmstrs.imbued_gear.item.promise

import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterCriteria
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier

class EnsouledGemItem(settings: Settings): IgnitedGemItem(settings) {

    private val ENSOULED_TARGET by lazy{
        IgConfig.items.ensouledTarget.get()
    }

    override fun getModifier(): Identifier {
        return me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier.SUMMONERS_ASPECT.modifierId
    }

    override fun giveTooltipHint(nbt: NbtCompound, stack: ItemStack, tooltip: MutableList<Text>) {
        if (nbt.contains("bred_animals")){
            val animals = nbt.getInt("bred_animals").toFloat()
            val progress = animals / ENSOULED_TARGET.toFloat() * 100.0F
            tooltip.add(AcText.translatable("item.amethyst_imbuement.gem_of_promise.ensouled", progress).formatted(Formatting.BLUE))
        }
    }

    fun ensouledGemCheck(stack: ItemStack, inventory: PlayerInventory, player: LivingEntity){
        val nbt = stack.orCreateNbt
        var animals = 0
        if (nbt.contains("bred_animals")){
            animals = nbt.getInt("bred_animals")
        }
        val newAnimals = animals + 1
        if (newAnimals >= ENSOULED_TARGET){
            stack.decrement(1)
            val newStack = ItemStack(RegisterItem.ENSOULED_GEM)
            inventory.offerOrDrop(newStack)
            if (player is ServerPlayerEntity) {
                RegisterCriteria.IGNITE.trigger(player)
            }
        } else {
            nbt.putInt("bred_animals",newAnimals)
        }
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
