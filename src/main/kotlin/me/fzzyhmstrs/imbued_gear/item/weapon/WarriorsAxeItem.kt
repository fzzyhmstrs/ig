package me.fzzyhmstrs.imbued_gear.item.weapon

import me.fzzyhmstrs.amethyst_imbuement.item.scepter.LethalityScepterItem
import me.fzzyhmstrs.fzzy_core.coding_util.PlayerParticlesV2
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.item_util.FlavorHelper
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.interfaces.DamageTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.registry.RegisterModifier
import net.minecraft.client.MinecraftClient
import net.minecraft.client.item.TooltipContext
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.particle.DustParticleEffect
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import org.joml.Vector3f

class WarriorsAxeItem(material: ToolMaterial, settings: Settings): AxeItem(material,5f,-3f,settings), Modifiable, DamageTracking {

    companion object{
        private val SMALL_DUST = DustParticleEffect(Vec3d.unpackRgb(0xFFEA66).toVector3f(),0.8f)
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        if (entity !is PlayerEntity) return
        if (stack.nbt?.getBoolean("charged") != true) return
        if (world.time - (stack.nbt?.getLong("charged_time") ?: 0L) > 200L){
            stack.nbt?.remove("charged")
            stack.nbt?.remove("charged_time")
            world.playSound(null,entity.blockPos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.PLAYERS,0.5f,1.0f)
        }
        if (world.isClient && selected){
            val rnd = world.random.nextInt(8)
            if (rnd < 1){
                emitParticles(world, MinecraftClient.getInstance(), entity)
            }
        }
    }

    private fun emitParticles(world: World, client: MinecraftClient, user: LivingEntity) {
        val particlePos = PlayerParticlesV2.scepterParticlePos(client, user)
        val rnd1 = world.random.nextDouble() * 0.1 - 0.05
        val rnd2 = world.random.nextDouble() * 0.2 - 0.1
        world.addParticle(SMALL_DUST,particlePos.x + rnd1, particlePos.y + rnd2, particlePos.z + rnd2, 0.0, 0.0, 0.0)
    }

    override fun defaultModifiers(type: ModifierHelperType): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType())
            return mutableListOf(RegisterModifier.FLESH_RENDING.modifierId)
        return super.defaultModifiers(type)
    }

    private val flavorText: MutableText by lazy{
        FlavorHelper.makeFlavorText(this)
    }

    private val flavorTextDesc: MutableText by lazy{
        FlavorHelper.makeFlavorTextDesc(this)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 40
    }

    override fun use(world: World?, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack>? {
        val itemStack = user.getStackInHand(hand)
        if (itemStack.damage >= itemStack.maxDamage - 1) {
            return TypedActionResult.fail(itemStack)
        }
        if (itemStack.nbt?.getBoolean("charged") == true)
            return TypedActionResult.fail(itemStack)
        user.setCurrentHand(hand)
        return TypedActionResult.consume(itemStack)
    }

    override fun usageTick(world: World, user: LivingEntity, stack: ItemStack, remainingUseTicks: Int) {
        super.usageTick(world, user, stack, remainingUseTicks)
        if (world.isClient) return
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i == 36){
            world.playSound(null,user.blockPos, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS,0.6f,1.5f)
            stack.orCreateNbt.putBoolean("charged", true)
            stack.orCreateNbt.putLong("charged_time", world.time)
        }
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.SPYGLASS
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        FlavorHelper.addFlavorText(tooltip, context, flavorText, flavorTextDesc)
    }

}