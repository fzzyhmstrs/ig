package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.imbued_gear.item.weapon.CelestialTridentItem
import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class CelestialTridentAvatarEntity : CelestialTridentEntity {

    constructor(entityType: EntityType<out CelestialTridentEntity?>?, world: World?) : super(entityType, world)
    constructor(world: World, owner: LivingEntity, parentStack: ItemStack) : super(
        RegisterEntity.CELESTIAL_TRIDENT_AVATAR_ENTITY,
        world,
        owner,
        parentStack
    )

    override val damage =  if (tridentStack.item is CelestialTridentItem) (tridentStack.item as CelestialTridentItem).material.getAttackDamage() - 8f else 6f

    override fun asItemStack(): ItemStack {
        return ItemStack.EMPTY.copy()
    }

    override fun tickTrident() {
    }

    override fun tryPickup(player: PlayerEntity): Boolean {
        return false
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        if (isOwner(player) || owner == null) {
            super.onPlayerCollision(player)
        }
    }
}