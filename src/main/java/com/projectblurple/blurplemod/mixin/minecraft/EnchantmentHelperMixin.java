package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Inject(method = "getProtectionAmount",
            at = @At("RETURN"),
            cancellable = true)
    private static void getProtectionAmount(Iterable<ItemStack> equipment, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValueI() >> 2);
    }

    @Inject(method = "getAttackDamage",
            at = @At("RETURN"),
            cancellable = true)
    private static void getAttackDamage(ItemStack stack, EntityGroup group, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValueF() * 0.5f);
    }
}
