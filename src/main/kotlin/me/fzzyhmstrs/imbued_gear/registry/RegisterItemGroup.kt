package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.imbued_gear.IG
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text

object RegisterItemGroup {

    fun registerItemGroup(): ItemGroup {
        return Registry.register(
            Registries.ITEM_GROUP, IG.identity("ig_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.imbued_gear.ig_group"))
            .icon { ItemStack(RegisterItem.VOID_GEM.asItem()) }
            .entries { _, entries ->
                entries.addAll(RegisterItem.regItem.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterArmor.regArmor.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterTool.regTool.stream().map { item -> ItemStack(item) }.toList())
                /*entries.addAll(RegisterBlock.regBlock.values.stream()
                    .map { block -> ItemStack(block.asItem()) }
                    .toList())*/
            }.build())
    }
}