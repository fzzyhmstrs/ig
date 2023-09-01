package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.modifier_util.ModifierHelper
import me.fzzyhmstrs.amethyst_imbuement.item.scepter.CustomScepterItem
import me.fzzyhmstrs.amethyst_imbuement.item.scepter.ScepterItem
import me.fzzyhmstrs.amethyst_imbuement.material.ScepterToolMaterial
import me.fzzyhmstrs.amethyst_imbuement.util.ImbuingRecipe
import me.fzzyhmstrs.fzzy_core.coding_util.PlayerParticlesV2.scepterParticlePos
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.DustParticleEffect
import net.minecraft.recipe.RecipeType
import net.minecraft.util.DyeColor
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class CosmicScepterItem(material: ScepterToolMaterial, settings: Settings): CustomScepterItem(material, settings) {

    @Environment(EnvType.CLIENT)
    override fun emitParticles(world: World, client: MinecraftClient, user: LivingEntity) {
        val particlePos = scepterParticlePos(client, user)
        val rnd1 = world.random.nextDouble() * 0.1 - 0.05
        val rnd2 = world.random.nextDouble() * 0.2 - 0.1
        val rnd3 = world.random.nextInt()
        val colorInt = if (rnd3 == 0) DyeColor.BLACK.signColor else DyeColor.WHITE.signColor
        val color = Vec3d.unpackRgb(colorInt).toVector3f()
        world.addParticle(DustParticleEffect(color,0.8f),particlePos.x + rnd1, particlePos.y + rnd2, particlePos.z + rnd2, 0.0, 0.0, 0.0)
        super.emitParticles(world,client, user)
    }

    override fun particleChance(): Int {
        return 10
    }

}
