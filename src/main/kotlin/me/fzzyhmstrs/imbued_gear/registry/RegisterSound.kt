package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.imbued_gear.IG
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent

object RegisterSound {

    private val BONE_RATTLE_ID = IG.identity("bone_rattle")
    val BONE_RATTLE = Registry.register(Registries.SOUND_EVENT,BONE_RATTLE_ID,SoundEvent.of(BONE_RATTLE_ID))

    fun registerAll(){}
}