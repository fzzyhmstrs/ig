package me.fzzyhmstrs.imbued_gear.model

import me.fzzyhmstrs.fzzy_core.coding_util.compat.FzzyRotation
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.entity.CelestialTridentAvatarEntity
import me.fzzyhmstrs.imbued_gear.entity.CelestialTridentEntity
import me.fzzyhmstrs.imbued_gear.registry.RegisterRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper

@Suppress("PrivatePropertyName")
class CelestialTridentEntityRenderer(context: EntityRendererFactory.Context) : EntityRenderer<CelestialTridentEntity>(context) {
    private val TEXTURE = IG.identity("textures/entity/celestial_trident.png")
    private val TEXTURE_AVATAR = IG.identity("textures/entity/celestial_trident_avatar.png")
    private val model = CelestialTridentEntityModel(context.getPart(RegisterRenderer.CELESTIAL_TRIDENT))

    override fun render(
        glisteringTridentEntity: CelestialTridentEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        matrixStack.push()
        matrixStack.multiply(
            FzzyRotation.POSITIVE_Y.degrees(
                MathHelper.lerp(
                    g,
                    glisteringTridentEntity.prevYaw,
                    glisteringTridentEntity.yaw
                ) - 90.0f
            )
        )
        matrixStack.multiply(
            FzzyRotation.POSITIVE_Z.degrees(
                MathHelper.lerp(
                    g,
                    glisteringTridentEntity.prevPitch,
                    glisteringTridentEntity.pitch
                ) + 90.0f
            )
        )
        val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumerProvider,
            model.getLayer(getTexture(glisteringTridentEntity)),
            false,
            glisteringTridentEntity.isEnchanted
        )
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrixStack.pop()
        super.render(glisteringTridentEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: CelestialTridentEntity): Identifier {
        return if (entity is CelestialTridentAvatarEntity) TEXTURE_AVATAR else TEXTURE
    }
}
