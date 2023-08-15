@file:Suppress("MemberVisibilityCanBePrivate")

package me.fzzyhmstrs.imbued_gear.registry

import me.fzzyhmstrs.amethyst_core.nbt_util.NbtKeys
import me.fzzyhmstrs.amethyst_core.scepter_util.SpellType
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.model.CelestialTridentEntityModel
import me.fzzyhmstrs.imbued_gear.model.CelestialTridentEntityRenderer
import me.fzzyhmstrs.imbued_gear.model.ChampionsTridentEntityModel
import me.fzzyhmstrs.imbued_gear.model.ChampionsTridentEntityRenderer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

@Environment(value = EnvType.CLIENT)
object RegisterRenderer {

    val CELESTIAL_TRIDENT: EntityModelLayer = EntityModelLayer(Identifier(IG.MOD_ID,"celestial_trident"),"celestial_trident_model")
    val CHAMPIONS_TRIDENT: EntityModelLayer = EntityModelLayer(Identifier(IG.MOD_ID,"champions_trident"),"champions_trident_model")

    fun registerAll() {
/*      BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.EXPERIENCE_BUSH, RenderLayer.getCutout())
        BlockRenderLayerMap.INSTANCE.putBlock(RegisterBlock.HARD_LIGHT_BLOCK, RenderLayer.getTranslucent())*/


        /*BlockEntityRendererRegistry.register(RegisterEntity.IMBUING_TABLE_BLOCK_ENTITY
        ){context: BlockEntityRendererFactory.Context ->
            ImbuingTableBlockEntityRenderer(
                context
            )
        }*/

        EntityRendererRegistry.register(
            RegisterEntity.CELESTIAL_TRIDENT_ENTITY
        ){context: EntityRendererFactory.Context ->
            CelestialTridentEntityRenderer(
                context
            )
        }

        EntityRendererRegistry.register(
            RegisterEntity.CELESTIAL_TRIDENT_AVATAR_ENTITY
        ){context: EntityRendererFactory.Context ->
            CelestialTridentEntityRenderer(
                context
            )
        }

        EntityRendererRegistry.register(
            RegisterEntity.CHAMPIONS_TRIDENT_ENTITY
        ){context: EntityRendererFactory.Context ->
            ChampionsTridentEntityRenderer(
                context
            )
        }

        EntityModelLayerRegistry.registerModelLayer(CELESTIAL_TRIDENT, CelestialTridentEntityModel::getTexturedModelData)
        EntityModelLayerRegistry.registerModelLayer(CHAMPIONS_TRIDENT, ChampionsTridentEntityModel::getTexturedModelData)

        ModelPredicateProviderRegistry.register(
            RegisterTool.CADUCEUS, Identifier("blocking")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f
        }
        


        ModelPredicateProviderRegistry.register(
            RegisterTool.CROWN_OF_SORROWS, Identifier("active")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            if (entity != null && stack.nbt?.getBoolean("active") == true) 1.0f else 0.0f
        }

        ModelPredicateProviderRegistry.register(
            RegisterTool.PENDANT_OF_MEMORIES, Identifier("active")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            if (entity != null && stack.nbt?.getBoolean("active") == true) 1.0f else 0.0f
        }

        ModelPredicateProviderRegistry.register(
            RegisterTool.RING_OF_SOULS, Identifier("active")
        ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int ->
            val nbt = stack.nbt?:return@register 0.0f
            if (entity != null && nbt.getInt("tier") > 1) 1.0f else 0.0f
        }
    }
}


