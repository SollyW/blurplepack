package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public abstract class WindowMixin {
    @Inject(method = "calculateScaleFactor", at = @At("RETURN"), cancellable = true)
    private void calculateScaleFactor(int guiScale, boolean forceUnicodeFont, CallbackInfoReturnable<Integer> cir) {
        if (guiScale == 0) cir.setReturnValue(cir.getReturnValueI() <= 2 ? 1 : 2);
    }
}
