package me.fzzyhmstrs.imbued_gear.item.weapon

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.fzzy_core.item_util.BasicCustomTridentItem
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.imbued_gear.entity.CelestialTridentAvatarEntity
import me.fzzyhmstrs.imbued_gear.entity.CelestialTridentEntity
import net.minecraft.client.item.TooltipContext
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.item.TridentItem
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.UseAction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class CelestialTridentItem(material: ToolMaterial, settings: Settings) : BasicCustomTridentItem<BasicCustomTridentEntity>(material,7.0,-2.9,settings.maxDamageIfAbsent(material.durability)), PersistentEffectHelper.PersistentEffect {

    override val delay = PerLvlI(10)

    override fun getEnchantability(): Int {
        return 15
    }

    override fun isFireproof(): Boolean {
        return true
    }

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        super.usageTick(world, user, stack, remainingUseTicks)
        if (world.isClient) return
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i > 10){
            //play hum noise
            when (i){
                16 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.4f,1.3f)
                }
                22 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.5f,1.5f)
                }
                28 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.6f,1.7f)
                }
                34 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,0.7f,1.9f)
                }
                40 ->{
                    world.playSound(null,user.blockPos,SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE,SoundCategory.PLAYERS,1.0f,2.1f)
                }
            }
        }

    }

    override fun onStoppedUsing(stack: ItemStack, world: World, user: LivingEntity, remainingUseTicks: Int) {
        if (user !is PlayerEntity) {
            return
        }
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i < 10) {
            return
        }
        val j = EnchantmentHelper.getRiptide(stack)
        if (j > 0 && !user.isTouchingWaterOrRain) {
            return
        }
        val avatars = if(i >=40){
            5
        } else if (i >= 34){
            4
        } else if (i >= 28){
            3
        } else if (i >= 22){
            2
        } else if (i >= 16){
            1
        } else {
            0
        }
        if (!world.isClient) {
            stack.damage(1 + avatars, user) { p: PlayerEntity ->
                p.sendToolBreakStatus(
                    user.getActiveHand()
                )
            }
            if (j == 0) {
                if (avatars > 0) {
                    val data = CelestialTridentData(world, user, stack.copy())
                    PersistentEffectHelper.setPersistentTickerNeed(this, 11, 11 * avatars,data)
                }

                val cte = makeTridentEntity(getMaterial(),world, user as LivingEntity, stack)
                cte.setVelocity(
                    user,
                    user.pitch,
                    user.yaw,
                    0.0f,
                    2.5f,
                    1.0f
                )
                if (user.abilities.creativeMode) {
                    cte.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY
                }
                if(ItemStack.areEqual(user.offHandStack, stack)) {
                    cte.setOffhand()
                }
                world.spawnEntity(cte)
                world.playSoundFromEntity(
                    null,
                    cte,
                    SoundEvents.ITEM_TRIDENT_THROW,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
                if (!user.abilities.creativeMode) {
                    user.inventory.removeOne(stack)
                }
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        if (j > 0) {
            val yaw = user.yaw
            val pitch = user.pitch
            var g =
                -MathHelper.sin(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
            var h = -MathHelper.sin(pitch * (Math.PI.toFloat() / 180))
            var k =
                MathHelper.cos(yaw * (Math.PI.toFloat() / 180)) * MathHelper.cos(pitch * (Math.PI.toFloat() / 180))
            //val l = MathHelper.sqrt(g * g + h * h + k * k)
            val m = 3.0f * ((1.0f + j.toFloat() + avatars.toFloat()) / 4.0f)

            g *= m
            h *= m
            k *= m
            user.addVelocity(g.toDouble(),h.toDouble(),k.toDouble())
            user.useRiptide(20)

            if (user.isOnGround) {
                //val n = 1.1999999f
                user.move(MovementType.SELF, Vec3d(0.0, 1.1999999284744263, 0.0))
            }
            val n =
                if (j >= 3) SoundEvents.ITEM_TRIDENT_RIPTIDE_3 else if (j == 2) SoundEvents.ITEM_TRIDENT_RIPTIDE_2 else SoundEvents.ITEM_TRIDENT_RIPTIDE_1
            world.playSoundFromEntity(null, user, n, SoundCategory.PLAYERS, 1.0f, 1.0f)
        }
    }

    override fun makeTridentEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): BasicCustomTridentEntity {
        return CelestialTridentEntity(world, livingEntity, stack).also{ it.damage = material.attackDamage + 5.0 }
    }

    override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
        if (data !is CelestialTridentData) return
        val cte = CelestialTridentAvatarEntity(data.world, data.user, data.stack)
        cte.damage = getMaterial().attackDamage + 3.0
        cte.setVelocity(
            data.user,
            data.user.pitch,
            data.user.yaw,
            0.0f,
            2.5f,
            1.0f
        )
        data.world.spawnEntity(cte)
        data.world.playSoundFromEntity(
            null,
            cte,
            SoundEvents.ITEM_TRIDENT_THROW,
            SoundCategory.PLAYERS,
            0.8f,
            (data.world.random.nextFloat() * 0.2f) + 1f
        )


    }

    private class CelestialTridentData(val world: World,val user: LivingEntity, val stack: ItemStack): PersistentEffectHelper.PersistentEffectData

}