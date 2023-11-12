package me.fzzyhmstrs.imbued_gear.item.armor

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.amethyst_core.compat.spell_power.SpChecker
import me.fzzyhmstrs.imbued_gear.material.FzzyArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier

class VoidMailArmorItem(fzzyMaterial: FzzyArmorMaterial, type: Type, settings: Settings) : ModifiableArmorItem(fzzyMaterial, type, settings) {

    private val attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier> by lazy {
        val map: ArrayListMultimap<EntityAttribute, EntityAttributeModifier> = ArrayListMultimap.create()
        map.putAll(super.getAttributeModifiers(type.equipmentSlot))
        when(type) {
            Type.BOOTS -> SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"b264c518-80ef-11ee-b962-0242ac120002", 1.5, EntityAttributeModifier.Operation.ADDITION, map)
            Type.LEGGINGS -> SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"b264c7de-80ef-11ee-b962-0242ac120002", 1.5, EntityAttributeModifier.Operation.ADDITION, map)
            Type.CHESTPLATE -> SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"b264c932-80ef-11ee-b962-0242ac120002", 1.5, EntityAttributeModifier.Operation.ADDITION, map)
            Type.HELMET -> SpChecker.addSpellPowerAttribute(SpChecker.Power.FROST,"b264ca5e-80ef-11ee-b962-0242ac120002", 1.5, EntityAttributeModifier.Operation.ADDITION, map)
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
