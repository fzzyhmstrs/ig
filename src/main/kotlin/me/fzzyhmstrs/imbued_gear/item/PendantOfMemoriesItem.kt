package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.imbued_gear.registry.RegisterTool
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World

class PendantOfMemoriesItem(settings: Settings): AbstractAugmentJewelryItem(settings), ManaItem, KillTracking {

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        val desc = AcText.translatable("item.imbued_gear.pendant_of_memories.innate.desc").formatted(Formatting.ITALIC)
        tooltip.add(AcText.translatable("item.imbued_gear.pendant_of_memories.innate", desc).formatted(Formatting.DARK_PURPLE))
        val desc2 = AcText.translatable("item.imbued_gear.pendant_of_memories.innate2.desc").formatted(Formatting.ITALIC)
        tooltip.add(AcText.translatable("item.imbued_gear.pendant_of_memories.innate2", desc2).formatted(Formatting.DARK_PURPLE))
    }

    override fun intermittentTick(stack: ItemStack, entity: LivingEntity) {
        if (entity.world.random.nextFloat() < 0.1 && stack.isDamaged){
            heal(stack)
        } else if (!stack.isDamaged){
            val nbt = stack.orCreateNbt
            nbt.putBoolean("active",true)
        }
        if (entity is PlayerEntity && getActive(stack)) {
            val stacks: MutableList<ItemStack> = mutableListOf()
            for (stack2 in entity.inventory.main) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack2.isOf(RegisterTool.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            } // iterate over the inventory and look for items that are interfaced with "ManaItem"
            for (stack2 in entity.inventory.offHand) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack2.isOf(RegisterTool.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            }
            for (stack2 in entity.inventory.armor) {
                if (stack2.item is ManaItem && stack2.isDamaged && !stack2.isOf(RegisterTool.PENDANT_OF_MEMORIES)) {
                    stacks.add(stack2)
                }
            }
            val stacks2 = TrinketUtil.getTrinketStacks(entity)
            stacks2.forEach {
                if (it.item is ManaItem && it.isDamaged && !it.isOf(RegisterTool.PENDANT_OF_MEMORIES)) {
                    stacks.add(it)
                }
            }
            if (this.manaDamage(stack,entity.world,entity,1, message = AcText.empty())){
                val nbt = stack.orCreateNbt
                nbt.putBoolean("active",false)
            } else {
                if (stacks.isNotEmpty()){
                    val i = entity.world.random.nextInt(stacks.size)
                    this.healDamage(1,stacks[i])
                } else {
                    entity.addExperience(1)
                }
            }
        }
        super.intermittentTick(stack, entity)
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        heal(stack)
        if (stack.isDamaged) {
            heal(stack)
        }
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

    private fun getActive(stack: ItemStack): Boolean{
        val nbt = stack.nbt
        return nbt?.getBoolean("active") == true
    }

    private fun heal(stack: ItemStack){
        this.healDamage(1,stack)
        if (!stack.isDamaged){
            val nbt = stack.orCreateNbt
            nbt.putBoolean("active",true)
        }
    }

    override fun getRepairTime(): Int {
        return 0
    }

    override fun manaDamage(
        stack: ItemStack,
        world: World,
        entity: LivingEntity,
        amount: Int,
        message: Text,
        unbreakingFlag: Boolean): Boolean {
        val currentDmg = stack.damage
        val maxDmg = stack.maxDamage
        var newCurrentDmg = currentDmg
        if (currentDmg == (maxDmg - 1)) return true
        for (i in 1..amount) {
            newCurrentDmg++
            if (newCurrentDmg == (maxDmg - 1)) {
                if (!unbreakingFlag) {
                    stack.damage = newCurrentDmg
                } else {
                    unbreakingDamage(stack,entity,newCurrentDmg - currentDmg)
                }
                return true
            }
        }
        if (!unbreakingFlag) {
            stack.damage = newCurrentDmg
        } else {
            unbreakingDamage(stack,entity,newCurrentDmg - currentDmg)
        }
        return false
    }

    private fun unbreakingDamage(stack: ItemStack,entity: LivingEntity, amount: Int){
        val player = if(entity is ServerPlayerEntity){
            entity
        } else {
            null
        }
        for (i in 1..amount){
            stack.damage(1,entity.random, player)
        }
    }

}
