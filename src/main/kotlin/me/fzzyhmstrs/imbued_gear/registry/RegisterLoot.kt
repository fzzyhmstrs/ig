package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.fzzy_core.registry.LootRegistry.registerModLoot
import me.fzzyhmstrs.imbued_gear.loot.IgLoot

object RegisterLoot {


    //private val COAL_ORE_LOOT_TABLE_ID = Blocks.CHEST.lootTableId
    fun registerAll(){
        registerModLoot(IgLoot)
    }
}
