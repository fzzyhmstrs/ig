package me.fzzyhmstrs.imbued_gear.mixins;

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem;
import me.fzzyhmstrs.fzzy_core.trinket_util.TrinketUtil;
import me.fzzyhmstrs.imbued_gear.item.PendantOfMemoriesItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Shadow
    @Final
    private PlayerInventory inventory;

    @Inject(method = "addExperience", at = @At("HEAD"))
    private void imbued_gear_pendantOfMemoriesInnateAbility(int experience, CallbackInfo ci){
        List<ItemStack> stacks = TrinketUtil.INSTANCE.getTrinketStacks((LivingEntity) (Object) this);
        for (ItemStack stack: stacks){
            if (stack.getItem() instanceof PendantOfMemoriesItem){
                ((LivingEntity)(Object) this).heal(0.25f);
            }
        }
    }

    @Inject(method = "incrementStat(Lnet/minecraft/util/Identifier;)V", at = @At("HEAD"))
    private void imbued_gear_ensouledGemCheck(Identifier stat, CallbackInfo ci){
        if (stat == Stats.ANIMALS_BRED) {
            ItemStack stack2 = inventory.getStack(PlayerInventory.OFF_HAND_SLOT);
            if (stack2.isOf(RegisterItem.INSTANCE.getGEM_OF_PROMISE())) {
                me.fzzyhmstrs.imbued_gear.registry.RegisterItem.INSTANCE.getENSOULED_GEM().ensouledGemCheck(stack2, inventory, (LivingEntity) (Object) this);
            }
        }
    }

}
