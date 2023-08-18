@file:Suppress("unused")

package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.registry.RegisterAttribute
import me.fzzyhmstrs.amethyst_imbuement.AI
import me.fzzyhmstrs.amethyst_imbuement.item.AiItemSettings
import me.fzzyhmstrs.amethyst_imbuement.item.SpellcastersReagentFlavorItem
import me.fzzyhmstrs.amethyst_imbuement.item.promise.GemOfPromiseItem
import me.fzzyhmstrs.amethyst_imbuement.item.promise.IgnitedGemItem
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.item.promise.EnsouledGemItem
import me.fzzyhmstrs.imbued_gear.item.promise.VoidGemItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Rarity
import java.util.*

// don't know if this is better as a class or object. as an object it allows me to call it without needing to initialize an instance of it.
object RegisterItem {

    private val regItem: MutableList<Item> = mutableListOf()

    private fun <T: Item> register(item: T, name: String): T{
        if (item is IgnitedGemItem){
            GemOfPromiseItem.register(item)
        }
        regItem.add(item)
        return Registry.register(Registries.ITEM, IG.identity(name), item)
    }

    //Make Tigers Eye??
    val SERPENTINE = register(Item(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM)),"serpentine")
    val ENSOULED_GEM = register(EnsouledGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)),"ensouled_gem")
    //make a spellcasters reagent that does less magic resist
    //val REALITY_GEM = register(RealityGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)),"reality_gem")
    val VOID_GEM = register(VoidGemItem(AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.UNCOMMON)),"void_gem")
    //val MYSTIC_FRAGMENT = register(SpellcastersReagentFlavorItem(RegisterAttribute.DAMAGE_MULTIPLICATION,
    //    EntityAttributeModifier(UUID.fromString("f2a00170-d1c6-11ed-afa1-0242ac120002"),"mystic_modifier",-0.05,EntityAttributeModifier.Operation.MULTIPLY_TOTAL),
    //    AiItemSettings().aiGroup(AiItemSettings.AiItemGroup.GEM).rarity(Rarity.EPIC)).withGlint(),"mystic_fragment")
    val PURESTEEL_INGOT = register(Item(AiItemSettings()),"puresteel_ingot")
    val VOIDSTEEL_INGOT = register(Item(AiItemSettings()),"voidsteel_ingot")
    val NULL_POWDER = register(Item(FabricItemSettings()),"null_powder") //lighting bedrock on fire
    val PURE_POWDER = register(Item(FabricItemSettings()),"pure_powder") //killing a mob with gem fire
    val SOUL_POWDER = register(Item(FabricItemSettings()),"soul_powder") //killing a mob with gem fire
    val WITHER_BONE = register(Item(FabricItemSettings()),"wither_bone") //killing a mob with gem fire

    //////////////////////////////

    val IG_GROUP: ItemGroup by lazy{
        registerItemGroup()
    }

    fun registerItemGroup(): ItemGroup{
        return Registry.register(Registries.ITEM_GROUP, IG.identity("ig_group"),FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.imbued_gear.ig_group"))
            .icon { ItemStack(VOID_GEM.asItem()) }
            .entries { _, entries ->
                entries.addAll(regItem.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterArmor.regArmor.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterTool.regTool.stream().map { item -> ItemStack(item) }.toList())
                /*entries.addAll(RegisterBlock.regBlock.values.stream()
                    .map { block -> ItemStack(block.asItem()) }
                    .toList())*/
            }.build())
    }

    fun registerAll() {
        val group = IG_GROUP
    }
}
