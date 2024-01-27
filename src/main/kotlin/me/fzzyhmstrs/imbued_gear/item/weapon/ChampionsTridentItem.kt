package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.BasicCustomTridentItem
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.entity.ChampionsTridentEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Identifier
import net.minecraft.world.World

class ChampionsTridentItem(material: ToolMaterial,settings: Settings) : BasicCustomTridentItem<BasicCustomTridentEntity>(material,7.0,-2.9,settings), Modifiable {
    override fun defaultModifiers(type: ModifierHelperType<*>?): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.RADIANT_BASTION.modifierId)
        return super<BasicCustomTridentItem>.defaultModifiers(type)
    }

    override fun isFireproof(): Boolean {
        return true
    }

    override fun makeTridentEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): BasicCustomTridentEntity {
        return ChampionsTridentEntity(world,livingEntity,stack).also { it.damage = material.attackDamage + 7.0 }
    }

}