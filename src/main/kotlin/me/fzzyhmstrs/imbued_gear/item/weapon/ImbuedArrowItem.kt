package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.imbued_gear.entity.ImbuedArrowEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ImbuedArrowItem(settings: Settings): ArrowItem(settings) {

    override fun createArrow(world: World, stack: ItemStack, shooter: LivingEntity): PersistentProjectileEntity {
        val arrowEntity = ImbuedArrowEntity(world, shooter)
        arrowEntity.initFromStack(stack)
        arrowEntity.setNoGravity(true)
        return arrowEntity
    }

}