package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterEnchantment.CONTAMINATED
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterEnchantment.DECAYED
import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

open class ChampionsTridentEntity : BasicCustomTridentEntity {

    constructor(entityType: EntityType<out ChampionsTridentEntity>, world: World) : super(entityType, world)
    constructor(world: World, owner: LivingEntity, stack: ItemStack): this(RegisterEntity.CHAMPIONS_TRIDENT_ENTITY,world, owner, stack)
    constructor(entityType: EntityType<out ChampionsTridentEntity>, world: World, owner: LivingEntity, stack: ItemStack) : super(entityType, world, owner, stack)

    init {
        this.damage = 13.0
    }
}
