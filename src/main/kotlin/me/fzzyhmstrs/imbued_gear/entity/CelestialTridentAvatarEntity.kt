package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class CelestialTridentAvatarEntity : CelestialTridentEntity {

    constructor(entityType: EntityType<out CelestialTridentEntity>, world: World) : super(entityType, world)
    constructor(world: World, owner: LivingEntity, parentStack: ItemStack) : super(
        RegisterEntity.CELESTIAL_TRIDENT_AVATAR_ENTITY,
        world,
        owner,
        parentStack
    )

    init{
        this.pickupType = PickupPermission.DISALLOWED
        this.damage = 13.0
    }

    //override val damage =  if (tridentStack.item is CelestialTridentItem) (tridentStack.item as CelestialTridentItem).material.attackDamage - -3f else 6f
    override fun asItemStack(): ItemStack {
        return ItemStack.EMPTY.copy()
    }

    override fun tryPickup(player: PlayerEntity): Boolean {
        return false
    }

    override fun setOwner(entity: Entity?) {
        super.setOwner(entity)
        pickupType = PickupPermission.DISALLOWED
    }

    override fun getOwner(): Entity? {
        if (hasDealtDamage())
            return null
        return super.getOwner()
    }

    override fun isNoClip(): Boolean {
        return false
    }
}