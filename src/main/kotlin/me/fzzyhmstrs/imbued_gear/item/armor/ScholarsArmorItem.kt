package me.fzzyhmstrs.amethyst_imbuement.item.armor

import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.world.World

class ScholarsArmorItem(fzzyMaterial: FzzyArmorMaterial, slot: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, type, settings) {

    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(type.equipmentSlot))
        if (SpChecker.spellPowerLoaded) {
            val attribute = Registries.ATTRIBUTE.get(Identifier("spell_power","arcane"))
            if (attribute != null) {
                val uUID = when(type) {
                    Type.BOOTS -> UUID.fromString("8f6bee96-7d8f-11ee-b962-0242ac120002")
                    Type.LEGGINGS -> UUID.fromString("8f6bfb52-7d8f-11ee-b962-0242ac120002")
                    Type.CHESTPLATE -> UUID.fromString("8f6bfcb0-7d8f-11ee-b962-0242ac120002")
                    Type.HELMET -> UUID.fromString("8f6bfdc8-7d8f-11ee-b962-0242ac120002")
                }
                map.put(attribute, EntityAttributeModifier(uUID,"arcane_power", 1.0, EntityAttributeModifier.Operation.ADDITION))
            }
        }
        map
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier>{
        if (slot == type.equipmentSlot) {
                return this.attributeModifiers
        }
        return super.getAttributeModifiers(slot)
    }
}
