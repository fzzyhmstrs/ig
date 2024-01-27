package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

open class ChampionsTridentEntity : BasicCustomTridentEntity {

    constructor(entityType: EntityType<out ChampionsTridentEntity>, world: World) : super(entityType, world)
    constructor(world: World, owner: LivingEntity, stack: ItemStack): this(RegisterEntity.CHAMPIONS_TRIDENT_ENTITY,world, owner, stack)
    constructor(entityType: EntityType<out ChampionsTridentEntity>, world: World, owner: LivingEntity, stack: ItemStack) : super(entityType, world, owner, stack)

    init {
        this.damage = 13.0
    }
}
