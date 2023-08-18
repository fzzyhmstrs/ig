package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_imbuement.item.Reactant
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagent
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import java.util.*

class DivineCoronetItem(settings: Settings): AbstractAugmentJewelryItem(settings), Reactant, ManaItem, Modifiable {

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.CELESTIAL.modifierId)
        return super.defaultModifiers(type)
    }

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
        return 20
    }

    private fun toNbt(attribute: Pair<EntityAttribute, EntityAttributeModifier>): NbtCompound {
        val nbtCompound = NbtCompound()
        nbtCompound.putString("Name", attribute.second.name)
        nbtCompound.putDouble("Amount", attribute.second.value * 2.5)
        nbtCompound.putInt("Operation", attribute.second.operation.id)
        nbtCompound.putUuid("UUID", UUID.randomUUID())
        nbtCompound.putString("AttributeName", Registries.ATTRIBUTE.getId(attribute.first).toString())
        return nbtCompound
    }


}