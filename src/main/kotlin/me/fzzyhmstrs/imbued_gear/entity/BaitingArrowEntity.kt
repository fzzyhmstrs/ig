package me.fzzyhmstrs.imbued_gear.entity

import me.fzzyhmstrs.amethyst_imbuement.mixins.MobEntityAccessor
import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.Monster
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class BaitingArrowEntity(entityType: EntityType<BaitingArrowEntity>, world: World): ArrowEntity(entityType, world) {

    constructor(world: World, owner: LivingEntity): this(RegisterEntity.BAITING_ARROW_ENTITY,world){
        this.setPosition(owner.x, owner.eyeY - 0.1, owner.z)
        this.owner = owner
        if (owner is PlayerEntity) {
            pickupType = PickupPermission.ALLOWED
        }
    }

    constructor(world: World, x: Double, y: Double, z: Double): this(RegisterEntity.BAITING_ARROW_ENTITY,world){
        this.setPosition(x,y,z)
    }

    override fun asItemStack(): ItemStack {
        return ItemStack(RegisterTool.BAITING_ARROW)
    }

    override fun onHit(target: LivingEntity?) {
        super.onHit(target)
        if (target != null){
            val box = this.boundingBox.expand(24.0)
            for (entity in this.world.getOtherEntities(target,box){it !is Monster}.mapNotNull { it as? MobEntity }){
                val goal = BaitingArrowGoal(entity,target)
                val targetSelector = (entity as MobEntityAccessor).targetSelector
                targetSelector.add(-1, goal)
                entity.target = target
                val data = Data(entity,target, goal)
                PersistentEffectHelper.setPersistentTickerNeed(PersistentTracker,400,400,data)
            }
        }

    }

    companion object PersistentTracker: PersistentEffectHelper.PersistentEffect{

        override val delay: PerLvlI = PerLvlI()
        override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
            if (data !is Data) return
            val targetSelector = (data.mob as MobEntityAccessor).targetSelector
            targetSelector.remove(data.goal)
        }

        class Data(val mob: MobEntity, val target: LivingEntity, val goal: BaitingArrowGoal): PersistentEffectHelper.PersistentEffectData

    }

}