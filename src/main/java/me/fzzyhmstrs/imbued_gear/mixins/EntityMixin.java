package me.fzzyhmstrs.imbued_gear.mixins;

import me.fzzyhmstrs.amethyst_imbuement.item.promise.GemOfPromiseItem;
import me.fzzyhmstrs.imbued_gear.registry.RegisterItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "tickInVoid",at = @At("HEAD"))
    private void imbued_gear_TickVoidGem(CallbackInfo ci){
        if (((Entity)(Object)this) instanceof ItemEntity itemEntity){
            Entity chk = itemEntity.getOwner();
            if (chk instanceof PlayerEntity entity) {
                ItemStack stack = itemEntity.getStack();
                if (stack.getItem() instanceof GemOfPromiseItem){
                    RegisterItem.INSTANCE.getVOID_GEM().voidGemCheck(stack,entity.getInventory());
                }
            }
        }
    }
}
