package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin {
    @Redirect(method = "canAccept",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/enchantment/ProtectionEnchantment;protectionType:Lnet/minecraft/enchantment/ProtectionEnchantment$Type;",
                    ordinal = 2))
    private ProtectionEnchantment.Type fall(ProtectionEnchantment instance) {
        return ProtectionEnchantment.Type.FALL;
    }

    @Inject(method = "getProtectionAmount",
            at = {
                @At(value = "RETURN",
                        ordinal = 2),
                @At(value = "RETURN",
                        ordinal = 4),
                @At(value = "RETURN",
                        ordinal = 5)},
            cancellable = true)
    private void getProtectionAmount(int level, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(level);
    }
}
