package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_imbuement.loot.*
import me.fzzyhmstrs.fzzy_core.registry.LootRegistry.registerModLoot
import me.fzzyhmstrs.imbued_gear.loot.IgLoot
import net.minecraft.loot.LootPool
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.math.MathHelper

object RegisterLoot {


    //private val COAL_ORE_LOOT_TABLE_ID = Blocks.CHEST.lootTableId
    fun registerAll(){
        registerModLoot(IgLoot)
    }
}
