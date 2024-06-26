@file:Suppress("PropertyName")

package me.fzzyhmstrs.imbued_gear

import com.llamalad7.mixinextras.MixinExtrasBootstrap
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.item.BoneRattleItem
import me.fzzyhmstrs.imbued_gear.item.TwoLayerDyeable
import me.fzzyhmstrs.imbued_gear.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random


object IG: ModInitializer {
    const val MOD_ID = "imbued_gear"
    val LOGGER: Logger = LoggerFactory.getLogger("imbued_gear")
    override fun onInitialize() {
        RegisterArmor.registerAll()
        RegisterTool.registerAll()
        RegisterItem.registerAll()
        RegisterEntity.registerAll()
        RegisterModifier.registerAll()
        RegisterTag.registerAll()
        RegisterStatus.registerAll()
        RegisterEnchantment.registerAll()
        RegisterLoot.registerAll()
        BoneRattleItem.registerServer()
        RegisterSound.registerAll()
        IgConfig.initConfig()
    }

    fun igRandom(): Random{
        return Random(System.currentTimeMillis())
    }

    fun identity(path: String): Identifier{
        return Identifier(MOD_ID,path)
    }
}

@Environment(value = EnvType.CLIENT)
object IGClient: ClientModInitializer{

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
        RegisterItemModel.registerAll()
        TwoLayerDyeable.registerClient()
    }

    fun iaRandom(): Random{
        return Random(System.currentTimeMillis())
    }
}

object IGPreLaunch: PreLaunchEntrypoint{

    override fun onPreLaunch() {
        MixinExtrasBootstrap.init()
    }

}