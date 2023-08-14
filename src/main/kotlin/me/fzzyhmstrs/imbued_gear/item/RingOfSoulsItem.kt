package me.fzzyhmstrs.imbued_gear.item

import me.fzzyhmstrs.amethyst_core.item_util.AbstractAugmentJewelryItem
import me.fzzyhmstrs.amethyst_core.modifier_util.AugmentModifier
import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.fzzy_core.interfaces.Modifiable
import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.interfaces.KillTracking
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.imbued_gear.IG
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.world.World
import kotlin.math.min

class RingOfSoulsItem(settings: Settings): AbstractAugmentJewelryItem(settings), KillTracking, Modifiable {

    private val tierLeveler = TierLeveler(IgConfig.items.ringOfSouls.killTierMultiplier.get(),IgConfig.items.ringOfSouls.baseKillsPerTier.get())

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        val nbt = stack.nbt ?: return
        if (nbt.contains("tier") && nbt.contains("amount")){
            val tier = nbt.getInt("tier")
            if (tier == IgConfig.items.ringOfSouls.maxTier.get()){
                tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier_max",IgConfig.items.ringOfSouls.maxTier.get()).formatted(Formatting.GOLD))
            } else {
                val amount = nbt.getInt("amount")
                val kills = getKillCount(stack)
                tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier",tier,kills,amount).formatted(Formatting.GOLD))
            }
        } else {
            tooltip.add(AcText.translatable("item.viscerae.ring_of_souls.tier","1","0",IgConfig.items.ringOfSouls.baseKillsPerTier.get()).formatted(Formatting.GOLD))
        }
    }

    override fun onWearerKilledOther(stack: ItemStack, wearer: LivingEntity, victim: LivingEntity, world: ServerWorld) {
        incrementKillCount(stack)
        val (tier, amount) = tierLeveler.getTierInfo(getKillCount(stack))
        val nbt = stack.orCreateNbt
        nbt.putInt("tier", min(tier,IgConfig.items.ringOfSouls.maxTier.get()))
        nbt.putInt("amount",amount)
        super.onWearerKilledOther(stack, wearer, victim, world)
    }

    private fun upgrade(stack: ItemStack, world: World, owner: LivingEntity) {
        TODO("Not yet implemented")
    }

    private fun canUpgrade(stack: ItemStack, world: World): Boolean {
        TODO("Not yet implemented")
    }

    override fun defaultModifiers(type: ModifierHelperType?): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) return mutableListOf(SOULS_MODIFIER.modifierId)
        return super.defaultModifiers(type)
    }

    companion object{
        private val SOUL_MODIFIER_SCEPTER = AugmentModifier(Identifier(IG.MOD_ID,"souls_modifier_scepter_tier_1")).withDamage(0.5f).also { ModifierRegistry.register(it) }
        private val SOULS_MODIFIER = EquipmentModifier(Identifier(IG.MOD_ID,"souls_modifier_tier_1"), persistent = true, randomSelectable = false).withModifiers(
            SOUL_MODIFIER_SCEPTER.modifierId).also { ModifierRegistry.register(it) }
        init{
            if (IgConfig.items.ringOfSouls.maxTier.get() > 1){
                for (i in 2..IgConfig.items.ringOfSouls.maxTier.get()){
                    val soulModifierScepterTierX = AugmentModifier(Identifier(IG.MOD_ID,"souls_modifier_scepter_tier_$i")).withDamage(0.5f*i)
                    ModifierRegistry.register(soulModifierScepterTierX)
                    val soulModifierTierX = EquipmentModifier(Identifier(IG.MOD_ID,"souls_modifier_tier_$i"), persistent = true, randomSelectable = false).withModifiers(
                        soulModifierScepterTierX.modifierId)
                    ModifierRegistry.register(soulModifierTierX)
                }
            }
        }
    }

    class TierLeveler(private val multiplier: Float, private val basePerTier: Int) {

        private fun amountToNextTier(perTier: Int, tier: Int): Int{
            if (tier <= 1) return basePerTier
            return (perTier * (multiplier * (tier - 1))).toInt()
        }

        fun getTierInfo(amount:Int): Pair<Int,Int>{
            if (amount < basePerTier) return Pair(1,basePerTier)
            var tierAmount = basePerTier
            var tier = 2
            while (tierAmount < amount){
                tierAmount += amountToNextTier(basePerTier,tier)
                tier++
            }
            tier -= 1
            return Pair(tier,tierAmount)
        }

    }

}