package me.fzzyhmstrs.imbued_gear.item.promise

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagent
import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterCriteria
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.*

class VoidGemItem(settings: Settings): IgnitedGemItem(settings), SpellcastersReagent {

    override fun getModifier(): Identifier {
        return Identifier("imbued_gear:call_of_the_void")
    }

    override fun giveTooltipHint(nbt: NbtCompound, stack: ItemStack, tooltip: MutableList<Text>){
    }

    fun voidGemCheck(stack: ItemStack, inventory: PlayerInventory){
        stack.decrement(1)
        val newStack = ItemStack(RegisterItem.VOID_GEM)
        inventory.offerOrDrop(newStack)
        val player = inventory.player
        if (player is ServerPlayerEntity) {
            RegisterCriteria.IGNITE.trigger(player)
        }
    }

    override fun getAttributeModifier(): Pair<EntityAttribute, EntityAttributeModifier> {
        return Pair(RegisterAttribute.MAGIC_RESISTANCE, EntityAttributeModifier(UUID.fromString("30e7f31c-deb9-11ed-b5ea-0242ac120002"),"nothingness_icon_modifier",0.03,EntityAttributeModifier.Operation.ADDITION))
    }
}
