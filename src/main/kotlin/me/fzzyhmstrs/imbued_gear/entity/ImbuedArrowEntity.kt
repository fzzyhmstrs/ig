package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ImbuedArrowEntity(entityType: EntityType<ImbuedArrowEntity>, world: World): ArrowEntity(entityType, world) {

    constructor(world: World, owner: LivingEntity): this(RegisterEntity.IMBUED_ARROW_ENTITY,world){
        this.setPosition(owner.x, owner.eyeY - 0.1, owner.z)
        this.owner = owner
        if (owner is PlayerEntity) {
            pickupType = PickupPermission.ALLOWED
        }
    }

    constructor(world: World, x: Double, y: Double, z: Double): this(RegisterEntity.IMBUED_ARROW_ENTITY,world){
        this.setPosition(x,y,z)
    }

    override fun asItemStack(): ItemStack {
        return ItemStack(RegisterTool.IMBUED_ARROW)
    }

}