package me.fzzyhmstrs.imbued_gear.model

import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import me.fzzyhmstrs.imbued_gear.model.CelestialTridentEntityModel.Companion.TEXTURE
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack

object CelestialTridentItemEntityRenderer: BuiltinItemRendererRegistry.DynamicItemRenderer {

    private val modelLoader by lazy {
        ItemModelRegistry.getEntityModelLoader(RegisterItem.CELESTIAL_TRIDENT)
    }

    override fun render(
        stack: ItemStack,
        mode: ModelTransformation.Mode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val renderModel = modelLoader.getModel()
        matrices.push()
        matrices.scale(1.0f, -1.0f, -1.0f)
        val block = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumers, renderModel.getLayer(
                TEXTURE
            ), false, stack.hasGlint()
        )
        renderModel.render(matrices, block, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
        matrices.pop()
    }
}
