package me.fzzyhmstrs.imbued_gear.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fzzyhmstrs.amethyst_imbuement.augment.GuardianAugment;
import me.fzzyhmstrs.gear_core.interfaces.ActiveGearSetsTracking;
import me.fzzyhmstrs.gear_core.set.GearSet;
import me.fzzyhmstrs.gear_core.set.GearSets;
import me.fzzyhmstrs.imbued_gear.registry.RegisterStatus;
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow public abstract @Nullable StatusEffectInstance getStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

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

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void imbued_gear_spellShieldEffect(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (this.hasStatusEffect(RegisterStatus.INSTANCE.getSPELL_SHIELD())){
            if (!source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY) && !source.isIn(GuardianAugment.Companion.getGUARDIAN_IGNORES_DAMAGE_TAG())){
                ((Entity) (Object) this).getWorld().playSound(null,((Entity) (Object) this).getBlockPos(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS,0.5f,1.0f);
                ((Entity) (Object) this).getWorld().playSound(null,((Entity) (Object) this).getBlockPos(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS,0.5f,1.0f);
                StatusEffectInstance instance = this.getStatusEffect(RegisterStatus.INSTANCE.getSPELL_SHIELD());
                if (instance != null){
                    int amplifier = instance.getAmplifier();
                    this.removeStatusEffect(RegisterStatus.INSTANCE.getSPELL_SHIELD());
                    if (amplifier > 0) {
                        this.addStatusEffect(new StatusEffectInstance(RegisterStatus.INSTANCE.getSPELL_SHIELD(), instance.getDuration(), amplifier - 1));
                    }
                } else {
                    this.removeStatusEffect(RegisterStatus.INSTANCE.getSPELL_SHIELD());
                }
                cir.setReturnValue(false);
            }
        }
    }

}
