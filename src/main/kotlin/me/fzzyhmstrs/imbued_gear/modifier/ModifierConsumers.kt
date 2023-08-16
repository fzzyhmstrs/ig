package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentConsumer
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.mana_util.ManaItem
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterStatus
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import kotlin.math.max
import kotlin.math.min

object ModifierConsumers {

    val HEALERS_REWARD_CONSUMER = AugmentConsumer({ list: List<LivingEntity> -> healersRewardConsumer(list) }, AugmentConsumer.Type.AUTOMATIC)
    private fun healersRewardConsumer(list: List<LivingEntity>){
        list.forEach {
            if (it is PlayerEntity)
                it.addExperience(1)
        }
    }

    val NULL_SPACE_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? -> 
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IgConfig.modifiers.nullAndVoidHitChance.get()){
                if (user.hasStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.CURSED)){
                    val effect = user.getStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.CURSED)
                    val amp = effect?.amplifier?:0
                    val duration = effect?.duration?:0
                    if (duration > 0){
                        val duration2 = if(duration < 200) {200} else {duration}
                        user.addStatusEffect(StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.CURSED,duration2,min(amp + 1,4)))
                    }
                } else {
                    user.addStatusEffect(
                        StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.CURSED, 100)
                    )
                }
            }
        }

    val NOTHINGNESS_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IgConfig.modifiers.nihilBladeNothingnessChance.get()){
                val health = target.health
                val maxHealth = target.maxHealth
                if (health > maxHealth - 4f){
                    if (health - 4f < 0f){
                        target.health = 0.1f
                        target.removeStatusEffect(StatusEffects.STRENGTH)
                        target.addStatusEffect(
                                StatusEffectInstance(StatusEffects.WEAKNESS, 400, 4)
                            )
                        target.addStatusEffect(
                                StatusEffectInstance(StatusEffects.SLOWNESS, 400, 2)
                            )
                        target.removeStatusEffect(StatusEffects.JUMP_BOOST)
                        target.addStatusEffect(
                                StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, -3)
                            )
                    } else {
                        target.health = health - 4f
                        if (target.hasStatusEffect(RegisterStatus.NIHILISM)){
                            val effect = target.getStatusEffect(RegisterStatus.NIHILISM)
                            val amp = effect?.amplifier?:0
                            val duration = effect?.duration?:0
                            if (duration > 0){
                                val duration2 = if(duration < 400) {400} else {duration}
                                target.addStatusEffect(StatusEffectInstance(RegisterStatus.NIHILISM,duration2,amp + 1))
                            }
                        } else {
                            target.addStatusEffect(
                                StatusEffectInstance(RegisterStatus.NIHILISM, 400)
                            )
                        }
                    }
                }  
            }
        }

    val RADIANT_DEVOTION_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, _: LivingEntity? ->
            if (user.hasStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BLESSED)){
                val effect = user.getStatusEffect(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BLESSED)
                val amp = effect?.amplifier?:0
                val duration = effect?.duration?:0
                if (duration > 0){
                    val duration2 = if(duration < 100) {100} else {duration}
                    user.addStatusEffect(StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BLESSED,duration2,min(amp + 1,4)))
                }
            } else {
                user.addStatusEffect(
                    StatusEffectInstance(me.fzzyhmstrs.amethyst_imbuement.registry.RegisterStatus.BLESSED, 100)
                )
            }
        }

    val MANA_VAMPIRIC_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IgConfig.modifiers.gear.manaVampiricHitChance.get()){
                manaHealItems(user,IgConfig.modifiers.gear.manaVampiricHitAmount.get())
            }
        }

    val MANA_VAMPIRIC_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            manaHealItems(user,IgConfig.modifiers.gear.manaVampiricKillAmount.get())
        }

    val MANA_DRAINING_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            if (user.world.random.nextFloat() < IgConfig.modifiers.gear.manaDrainingHitChance.get()){
                manaDamageItems(user,IgConfig.modifiers.gear.manaDrainingHitAmount.get())
            }
        }

    val MANA_DRAINING_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            manaDamageItems(user,IgConfig.modifiers.gear.manaDrainingKillAmount.get())
        }
        
    /////////////////////////////
    
    private fun manaDamageItems(user: LivingEntity, damageAmount: Int){
        if (user !is PlayerEntity) return
        val stacks = getManaStacks(user)
        manaDamageItems(stacks,user,damageAmount)
    }
    
    private fun manaDamageItems(list: MutableList<ItemStack>, user: PlayerEntity, damageLeft: Int): Int{
        var hl = damageLeft
        if (hl <= 0 || list.isEmpty()) return max(0,hl)
        val rnd = user.world.random.nextInt(list.size)
        val stack = list[rnd]
        val damageAmount = min(5,hl)
        val bl = (stack.item as ManaItem).manaDamage(stack,user.world,user,damageAmount, AcText.empty())
        hl -= damageAmount
        if (bl){
            list.remove(stack)
        }
        return manaDamageItems(list,user,hl)
    }
        
    internal fun manaHealItems(user: LivingEntity, healAmount: Int){
        if (user !is PlayerEntity) return
        val stacks = getManaStacks(user)
        manaHealItems(stacks,user.world,healAmount)
    }
    
    private fun manaHealItems(list: MutableList<ItemStack>, world: World, healLeft: Int): Int{
        var hl = healLeft
        if (hl <= 0 || list.isEmpty()) return max(0,hl)
        val rnd = world.random.nextInt(list.size)
        val stack = list[rnd]
        val healAmount = min(5,hl)
        val healedAmount = (stack.item as ManaItem).healDamage(healAmount,stack)
        hl -= min(healAmount,healedAmount)
        if (!stack.isDamaged){
            list.remove(stack)
        }
        return manaHealItems(list,world,hl)
    }
    
    internal fun getManaStacks(user: PlayerEntity): MutableList<ItemStack>{
        val stacks: MutableList<ItemStack> = mutableListOf()
        for (stack2 in user.inventory.main){
            if (stack2.item is ManaItem && stack2.isDamaged){
                stacks.add(stack2)
            }
        } // iterate over the inventory and look for items that are interfaced with "ManaItem"
        for (stack2 in user.inventory.offHand){
            if (stack2.item is ManaItem && stack2.isDamaged){
                stacks.add(stack2)
            }
        }
        for (stack2 in user.inventory.armor){
            if (stack2.item is ManaItem && stack2.isDamaged){
                stacks.add(stack2)
            }
        }
        val stacks2 = TrinketUtil.getTrinketStacks(user)
        stacks2.forEach {
            if (it.item is ManaItem && it.isDamaged){
                stacks.add(it)
            }
        }
        return stacks
    }
}
