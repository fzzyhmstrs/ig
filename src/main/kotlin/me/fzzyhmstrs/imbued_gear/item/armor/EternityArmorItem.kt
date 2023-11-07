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

class EternityArmorItem(fzzyMaterial: FzzyArmorMaterial, slot: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, type, settings) {

    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(type.equipmentSlot))
        if (SpChecker.spellPowerLoaded) {
            val attribute = Registries.ATTRIBUTE.get(Identifier("spell_power","healing"))
            if (attribute != null) {
                val uUID = when(type) {
                    Type.BOOTS -> UUID.fromString("8533bb28-7d91-11ee-b962-0242ac120002")
                    Type.LEGGINGS -> UUID.fromString("8533bd9e-7d91-11ee-b962-0242ac120002")
                    Type.CHESTPLATE -> UUID.fromString("8533be98-7d91-11ee-b962-0242ac120002")
                    Type.HELMET -> UUID.fromString("8533c28a-7d91-11ee-b962-0242ac120002")
                }
                map.put(attribute, EntityAttributeModifier(uUID,"healing_power", 1.0, EntityAttributeModifier.Operation.ADDITION))
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
