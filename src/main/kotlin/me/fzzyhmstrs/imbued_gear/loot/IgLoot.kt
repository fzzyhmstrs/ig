package me.fzzyhmstrs.imbued_gear.loot


import me.fzzyhmstrs.fzzy_core.item_util.AbstractModLoot
import me.fzzyhmstrs.imbued_gear.registry.RegisterArmor
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object IgLoot: AbstractModLoot() {
    override val targetNameSpace: String = "minecraft"

    override fun lootBuilder(id: Identifier, table: LootTable.Builder): Boolean {
        when (id) {
            Identifier("amethyst_imbuement","chests/crystal_workshop") -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.025f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            Identifier("minecraft","entities/wither_skeleton") -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.25f))
                    .rolls(UniformLootNumberProvider.create(1.0F,3.0F))
                    .with(ItemEntry.builder(RegisterItem.WITHER_BONE).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.NETHER_BRIDGE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.BASTION_TREASURE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.BASTION_BRIDGE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.RUINED_PORTAL_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.STRONGHOLD_CORRIDOR_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.075f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.STRONGHOLD_CROSSING_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.1f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.STRONGHOLD_LIBRARY_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.SIMPLE_DUNGEON_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.END_CITY_TREASURE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.15f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.ANCIENT_CITY_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.15f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.VILLAGE_TEMPLE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.005f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.VILLAGE_ARMORER_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.01f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.DESERT_PYRAMID_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.LIVING_FLAME).weight(1))
                    .with(ItemEntry.builder(RegisterTool.NULL_AND_VOID).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_BOOTS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_LEGGINGS).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_CHESTPLATE).weight(1))
                    .with(ItemEntry.builder(RegisterArmor.ETERNITY_HELMET).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.JUNGLE_TEMPLE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.ENERGETIC_BAND).weight(1))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.PILLAGER_OUTPOST_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.025f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.SHIPWRECK_TREASURE_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.025f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.PENDANT_OF_MEMORIES).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.SHIPWRECK_MAP_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.05f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.STRANGE_TIMEPIECE).weight(1))
                table.pool(poolBuilder)
                return true
            }
            LootTables.IGLOO_CHEST_CHEST -> {
                val poolBuilder = LootPool.builder()
                    .conditionally(RandomChanceLootCondition.builder(.15f))
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(RegisterTool.STRANGE_TIMEPIECE).weight(1))
                table.pool(poolBuilder)
                return true
            }
            else -> {
                return false
            }
        }
    }
}