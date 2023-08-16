package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagent
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.Registries
import java.util.*

class DivineCoronetItem(settings: Settings): AbstractAugmentJewelryItem(settings), Reactant, ManaItem {
    override fun canReact(
        stack: ItemStack,
        reagents: List<ItemStack>,
        player: PlayerEntity?,
        type: RecipeType<*>?
    ): Boolean {
        return true
    }

    override fun react(stack: ItemStack, reagents: List<ItemStack>, player: PlayerEntity?, type: RecipeType<*>?) {
        for (reagent in reagents){
            val item = reagent.item
            if (item is SpellcastersReagent){
                if (stack.nbt?.contains("TrinketAttributeModifiers") == true) return
                val attribute = item.getAttributeModifier()
                val list = NbtList()
                val nbt = toNbt(attribute)
                list.add(nbt)
                stack.orCreateNbt.put("TrinketAttributeModifiers",list)
                break
            }
        }
    }

    override fun getRepairTime(): Int {
        return 0
    }

    fun toNbt(attribute: Pair<EntityAttribute, EntityAttributeModifier>): NbtCompound {
        val nbtCompound = NbtCompound()
        nbtCompound.putString("Name", attribute.second.name)
        nbtCompound.putDouble("Amount", attribute.second.value * 2.5)
        nbtCompound.putInt("Operation", attribute.second.operation.id)
        nbtCompound.putUuid("UUID", UUID.randomUUID())
        nbtCompound.putString("AttributeName", Registries.ATTRIBUTE.getId(attribute.first).toString())
        return nbtCompound
    }


}