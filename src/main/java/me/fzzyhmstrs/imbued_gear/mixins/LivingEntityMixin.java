package me.fzzyhmstrs.imbued_gear.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fzzyhmstrs.gear_core.interfaces.ActiveGearSetsTracking;
import me.fzzyhmstrs.gear_core.set.GearSet;
import me.fzzyhmstrs.gear_core.set.GearSets;
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private final Identifier huntersGearSet = new Identifier("gear_core:gear_core_sets/hunters_set.json");

    @WrapOperation(method = "getArmorVisibility", at = @At(value = "INVOKE", target = "net/minecraft/item/ItemStack.isEmpty ()Z"))
    private boolean imbued_gear_huntersGearArmorInvisible(ItemStack instance, Operation<Boolean> operation){
        return (operation.call(instance) || instance.isIn(RegisterTag.INSTANCE.getHUNTERS_GEAR()));
    }

    @WrapOperation(method = "getAttackDistanceScalingFactor", at = @At(value = "INVOKE", target = "net/minecraft/entity/LivingEntity.isInvisible ()Z"))
    private boolean imbued_gear_reduceVisibilityOnHuntersSet(LivingEntity instance, Operation<Boolean> operation){
        HashMap<GearSet, Integer> sets = ((ActiveGearSetsTracking)instance).gear_core_getActiveSets();
        Integer level = sets.get(GearSets.INSTANCE.getGearSet(huntersGearSet));
        if (level != null && level > 1) return true;
        return operation.call(instance);
    }

}
