package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_imbuement.block.WardingCandleBlock
import me.fzzyhmstrs.imbued_gear.IF
import me.fzzyhmstrs.imbued_gear.block.EffectWardingCandleBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

@Suppress("MemberVisibilityCanBePrivate")
object RegisterBlock {

    internal val regBlock: MutableMap<String, Block> = mutableMapOf()

    val WARDING_CANDLE = EffectWardingCandleBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.OFF_WHITE).nonOpaque().strength(0.1f).sounds(BlockSoundGroup.CANDLE).luminance(
        WardingCandleBlock.STATE_TO_LUMINANCE)).also { regBlock["warding_candle"] = it }

    fun registerAll() {
        for (k in regBlock.keys) {
            if (k == "experience_bush") continue
            registerBlock(k,regBlock[k])
        }
    }

    private fun registerBlock(path: String, block:Block?){
        Registry.register(Registries.BLOCK, Identifier(IF.MOD_ID, path), block)
        Registry.register(Registries.ITEM, Identifier(IF.MOD_ID,path), BlockItem(block,FabricItemSettings()))
    }

    @Suppress("unused")
    private fun always(): Boolean {
        return true
    }
    private fun never(): Boolean {
        return false
    }

}