package me.fzzyhmstrs.imbued_gear.mixins;

import me.fzzyhmstrs.amethyst_imbuement.item.promise.GemOfPromiseItem;
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "requestTeleport", at = @At("HEAD"))
    private void imbued_gear_checkRealityGemProgressRequestTeleport(double destX, double destY, double destZ, CallbackInfo ci) {
        ItemStack stack = ((ServerPlayerEntity)(Object)this).getOffHandStack();
        if (stack.getItem() instanceof GemOfPromiseItem) {
            RegisterItem.INSTANCE.getREALITY_GEM().realityGemCheck(stack,((ServerPlayerEntity)(Object)this).getBlockPos(), BlockPos.ofFloored(destX,destY, destZ), ((ServerPlayerEntity)(Object)this).getInventory());
        }
    }

    @Inject(method = "requestTeleportAndDismount", at = @At("HEAD"))
    private void imbued_gear_checkRealityGemProgressRequestTeleportAndDismount(double destX, double destY, double destZ, CallbackInfo ci) {
        ItemStack stack = ((ServerPlayerEntity)(Object)this).getOffHandStack();
        if (stack.getItem() instanceof GemOfPromiseItem) {
            RegisterItem.INSTANCE.getREALITY_GEM().realityGemCheck(stack,((ServerPlayerEntity)(Object)this).getBlockPos(), BlockPos.ofFloored(destX,destY, destZ), ((ServerPlayerEntity)(Object)this).getInventory());
        }
    }

}
