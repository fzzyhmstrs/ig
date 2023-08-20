package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterModifier
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.raycaster_util.RaycasterUtil
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.registry.RegisterSound
import me.fzzyhmstrs.imbued_gear.registry.RegisterStatus
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.Tameable
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

open class BoneRattleItem(settings: Settings)
    :
    SpecialityOffhandItem(settings,listOf(),listOf(RegisterModifier.SUMMONERS_ASPECT.modifierId))
{

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        val nbt = stack.nbt ?: return
        if (!nbt.contains("bone_ability")) return
        val ability = nbt.getString("bone_ability")
        tooltip.add(AcText.empty())
        val abilityText = AcText.translatable("item.imbued_gear.bone_rattle.$ability")
        tooltip.add(AcText.translatable("item.imbued_gear.bone_rattle.bone_ability",abilityText).formatted(Formatting.DARK_GREEN, Formatting.BOLD))
        tooltip.add(AcText.translatable("item.imbued_gear.bone_rattle.$ability.desc").formatted(Formatting.DARK_GREEN, Formatting.ITALIC))
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        val nbt = stack.nbt ?: return TypedActionResult.pass(stack)
        if (!nbt.contains("bone_ability"))
            nbt.putString("bone_ability", powersList[0])
        val target = RaycasterUtil.raycastEntity(5.0,user) ?: return TypedActionResult.pass(stack)
        if (target !is LivingEntity || target !is Tameable) return TypedActionResult.pass(stack)
        return when (nbt.getString("bone_ability")){
            "bone_power" ->{
                target.addStatusEffect(StatusEffectInstance(RegisterStatus.BLADE_RAGE,600,9))
                val box = target.boundingBox.expand(4.0)
                val otherSummons = world.getOtherEntities(target,box) {it !== user}
                for (entity in otherSummons){
                    if (entity is LivingEntity){
                        entity.addStatusEffect(StatusEffectInstance(RegisterStatus.BLADE_RAGE,600,3))
                    }
                }
                world.playSound(null,user.blockPos,RegisterSound.BONE_RATTLE,SoundCategory.PLAYERS,1f,1f)
                user.itemCooldownManager.set(RegisterTool.BONE_RATTLE,1200)
                TypedActionResult.success(stack)
            }
            "bone_healing" ->{
                target.heal(target.maxHealth * 0.5f)
                val box = target.boundingBox.expand(4.0)
                val otherSummons = world.getOtherEntities(target,box) {it !== user}
                for (entity in otherSummons){
                    if (entity is LivingEntity){
                        entity.heal(entity.maxHealth * 0.2f)
                    }
                }
                world.playSound(null,user.blockPos,RegisterSound.BONE_RATTLE,SoundCategory.PLAYERS,1f,1f)
                user.itemCooldownManager.set(RegisterTool.BONE_RATTLE,1200)
                TypedActionResult.success(stack)
            }
            "bone_armor"->{
                val armor = target.getStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BONE_ARMOR)?.amplifier ?: -1
                target.addStatusEffect(StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BONE_ARMOR,600,armor + 3))
                val box = target.boundingBox.expand(4.0)
                val otherSummons = world.getOtherEntities(target,box) {it !== user}
                for (entity in otherSummons){
                    if (entity is LivingEntity){
                        val otherArmor = entity.getStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BONE_ARMOR)?.amplifier ?: -1
                        entity.addStatusEffect(StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BONE_ARMOR,600,otherArmor + 1))
                    }
                }
                world.playSound(null,user.blockPos,RegisterSound.BONE_RATTLE,SoundCategory.PLAYERS,1f,1f)
                user.itemCooldownManager.set(RegisterTool.BONE_RATTLE,1200)
                TypedActionResult.success(stack)
            }
            "bone_haste" ->{
                target.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED,600,2))
                val box = target.boundingBox.expand(4.0)
                val otherSummons = world.getOtherEntities(target,box) {it !== user}
                for (entity in otherSummons){
                    if (entity is LivingEntity){
                        entity.addStatusEffect(StatusEffectInstance(StatusEffects.SPEED,600,0))
                    }
                }
                world.playSound(null,user.blockPos,RegisterSound.BONE_RATTLE,SoundCategory.PLAYERS,1f,1f)
                user.itemCooldownManager.set(RegisterTool.BONE_RATTLE,1200)
                TypedActionResult.success(stack)
            }

            else -> {
                super.use(world, user, hand)
            }
        }
    }

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        val nbt = stack.orCreateNbt
        if (!nbt.contains("bone_ability")){
            nbt.putString("bone_ability", powersList[0])
        }
    }

    override fun onCraft(stack: ItemStack, world: World, player: PlayerEntity) {
        stack.orCreateNbt.putString("bone_ability", powersList[0])
    }

    companion object{
        private val powersList: List<String> = listOf(
            "bone_power",
            "bone_healing",
            "bone_armor",
            "bone_haste"
        )
        private val BONE_RATTLE_SENDER = IG.identity("bone_rattle_sender")

        fun sendRattleUpdateFromClient(bl: Boolean){
            val buf = PacketByteBufs.create()
            buf.writeBoolean(bl)
            ClientPlayNetworking.send(BONE_RATTLE_SENDER,buf)
        }
        fun registerServer(){
            ServerPlayNetworking.registerGlobalReceiver(BONE_RATTLE_SENDER){_,player,_,buf,_ ->
                val up = buf.readBoolean()
                val rattle = player.mainHandStack
                if (rattle.item !is BoneRattleItem) return@registerGlobalReceiver
                val nbt = rattle.orCreateNbt
                if (!nbt.contains("bone_ability")){
                    nbt.putString("bone_ability",powersList[0])
                } else {
                    val previousPower = nbt.getString("bone_ability")
                    val index = powersList.indexOf(previousPower)
                    if (index == -1){
                        nbt.putString("bone_ability",powersList[0])
                    } else if (index == 0 && up){
                        nbt.putString("bone_ability",powersList[powersList.lastIndex])
                    } else if (index == powersList.lastIndex && !up){
                        nbt.putString("bone_ability",powersList[0])
                    } else if (up){
                        nbt.putString("bone_ability",powersList[index - 1])
                    } else {
                        nbt.putString("bone_ability",powersList[index + 1])
                    }
                }
            }
        }

    }
    
}
