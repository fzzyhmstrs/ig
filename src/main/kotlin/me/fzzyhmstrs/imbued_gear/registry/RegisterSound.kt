package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.sound.SoundEvent

object RegisterSound {

    private val BONE_RATTLE_ID = IG.identity("bone_rattle")
    val BONE_RATTLE = FzzyPort.SOUND_EVENT.register(BONE_RATTLE_ID,SoundEvent.of(BONE_RATTLE_ID))

    fun registerAll(){}
}