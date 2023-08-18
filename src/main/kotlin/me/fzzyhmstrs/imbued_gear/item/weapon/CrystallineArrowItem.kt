package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.imbued_gear.entity.CrystallineArrowEntity
import me.fzzyhmstrs.imbued_gear.entity.ImbuedArrowEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class CrystallineArrowItem(settings: Settings): ArrowItem(settings) {

    override fun createArrow(world: World, stack: ItemStack, shooter: LivingEntity): PersistentProjectileEntity {
        val arrowEntity = CrystallineArrowEntity(world, shooter)
        arrowEntity.initFromStack(stack)
        arrowEntity.damage = 2.5
        return arrowEntity
    }

}