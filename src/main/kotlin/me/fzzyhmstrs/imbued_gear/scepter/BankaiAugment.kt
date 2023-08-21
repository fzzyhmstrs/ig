package me.fzzyhmstrs.imbued_gear.scepter

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentEffect
import me.fzzyhmstrs.amethyst_core.scepter_util.LoreTier
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterHelper
import me.fzzyhmstrs.amethyst_core.scepter_util.ScepterTier
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.AugmentDatapoint
import me.fzzyhmstrs.amethyst_core.scepter_util.augments.ScepterAugment
import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.fzzy_core.raycaster_util.RaycasterUtil
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class BankaiAugment: ScepterAugment(ScepterTier.TWO,9), PersistentEffectHelper.PersistentEffect{

    private val tries = doubleArrayOf(0.0,-1.0,1.0)

    override val baseEffect: AugmentEffect
        get() = super.baseEffect.withRange(12.75,1.25)
            .withDamage(9.75f,0.25f)
            .withAmplifier(15,1)

    override fun augmentStat(imbueLevel: Int): AugmentDatapoint {
        return AugmentDatapoint(SpellType.FURY,300,50,
            19,imbueLevel,13, LoreTier.NO_TIER, RegisterItem.SPARKING_GEM)
    }

    override fun applyTasks(world: World, user: LivingEntity, hand: Hand, level: Int, effects: AugmentEffect): Boolean {
        if (world !is ServerWorld) return false
        if (user !is PlayerEntity) return false
        val rotation = user.getRotationVec(1.0F)
        val perpendicularVector = RaycasterUtil.perpendicularVector(rotation, RaycasterUtil.InPlane.XZ)
        val raycasterPos = user.pos.add(rotation.multiply(effects.range(level)/2)).add(Vec3d(0.0,user.height/2.0,0.0))
        val entityList: MutableList<Entity> =
            RaycasterUtil.raycastEntityRotatedArea(
                world.iterateEntities(),
                user,
                raycasterPos,
                rotation,
                perpendicularVector,
                effects.range(level),
                0.8,
                0.8)
        if (entityList.isEmpty()) return false
        val map: MutableMap<Float,Entity> = mutableMapOf()
        entityList.forEach {
            map[user.distanceTo(it)] = it
        }
        val sortedMap = map.toSortedMap()

        val target = sortedMap.getValue(sortedMap.lastKey())
        val rot = target.rotationVector.negate()
        val pos = target.pos.add(rot.x,0.0,rot.z)
        for (i in tries) {
            if (world.isSpaceEmpty(user.type.createSimpleBoundingBox(pos.x,pos.y+i, pos.z))){
                doTeleport(pos, i, user, hand, world, entityList, level, effects)
                return true
            }
        }
        val rot2 = target.rotationVector
        val pos2 = target.pos.add(rot2.x,0.0,rot2.z)
        for (i in tries) {
            if (world.isSpaceEmpty(user.type.createSimpleBoundingBox(pos2.x,pos2.y+i, pos2.z))){
                doTeleport(pos2, i, user, hand, world, entityList, level, effects)
                return true
            }
        }

        return true
    }

    private fun doTeleport(pos: Vec3d, i: Double, user: PlayerEntity, hand: Hand, world: World, entityList: MutableCollection<Entity>, level: Int, effects: AugmentEffect){
        user.teleport(pos.x,pos.y+i, pos.z,true)
        world.playSound(null, user.blockPos, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 2.0F, 1.4F)
        val stack = user.getStackInHand(hand)
        val item = stack.item
        val damage = if (item is ToolItem){
            user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE).toFloat() * (effects.amplifier(level).toFloat() / 12f)
        } else {
            effects.damage(level)
        }
        val data = BankaiPersistentEffectData(world,user,entityList,stack, user.damageSources.playerAttack(user),damage,level,effects)
        PersistentEffectHelper.setPersistentTickerNeed(this,10,10,data)
    }

    override fun soundEvent(): SoundEvent {
        return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER
    }

    override val delay: PerLvlI
        get() = PerLvlI()

    override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
        if (data !is BankaiPersistentEffectData) return
        val entities = data.entityList
        var kills = 0
        val user = data.user
        for (entity in entities){
            val g = if(entity is LivingEntity) EnchantmentHelper.getAttackDamage(data.stack,entity.group) else EnchantmentHelper.getAttackDamage(data.stack, EntityGroup.DEFAULT)
            entity.damage(data.source,data.damage + g)
            entity.applyDamageEffects(user,entity)
            if (!entity.isAlive || entity is LivingEntity && entity.isDead) kills++
            if (user is PlayerEntity && entity is LivingEntity){
                data.stack.postHit(entity, user)
            }
        }
        if (kills > 0 && data.world.random.nextFloat() < (0.15f + 0.025f * data.level)){
            if (user is PlayerEntity){
                user.itemCooldownManager.remove(data.stack.item)
            }
            data.world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.35f,1.2f)
            ScepterHelper.resetCooldown(data.world,data.stack,user,this.id?.toString()?:"minecraft:curse_of_vanishing",data.level)
        }
        data.world.playSound(null, user.blockPos, soundEvent(), SoundCategory.PLAYERS, 2.0F, 1.4F)

    }

    private class BankaiPersistentEffectData(
        val world: World,
        val user: LivingEntity,
        val entityList: MutableCollection<Entity>,
        val stack: ItemStack,
        val source: DamageSource,
        val damage: Float,
        val level: Int,
        val effect: AugmentEffect)
        :
        PersistentEffectHelper.PersistentEffectData
}