package me.fzzyhmstrs.imbued_gear.entity

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.TrackTargetGoal
import net.minecraft.entity.mob.MobEntity

class BaitingArrowGoal(mob: MobEntity, private val baitTarget: LivingEntity) : TrackTargetGoal(mob, false) {
    override fun canStart(): Boolean {
        return baitTarget.isAlive
    }

    override fun shouldRunEveryTick(): Boolean {
        return true
    }

    override fun start() {
        mob.target = baitTarget
        super.start()
    }
}