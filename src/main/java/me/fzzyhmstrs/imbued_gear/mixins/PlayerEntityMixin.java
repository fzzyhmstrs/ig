package me.fzzyhmstrs.imbued_gear.mixins;

import me.fzzyhmstrs.amethyst_imbuement.registry.RegisterItem;
import me.fzzyhmstrs.imbued_gear.item.promise.EnsouledGemItem;
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

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Shadow
    @Final
    private PlayerInventory inventory;

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
