package me.fzzyhmstrs.imbued_gear.model

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.ProjectileEntityRenderer
import net.minecraft.entity.projectile.ArrowEntity
import net.minecraft.util.Identifier

class CustomArrowEntityRenderer<T:ArrowEntity>(context: EntityRendererFactory.Context, private val texture: Identifier): ProjectileEntityRenderer<T>(
    context
) {
    override fun getTexture(entity: T): Identifier {
        return texture
    }
}