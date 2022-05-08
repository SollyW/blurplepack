package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "processF3(I)Z", at = @At("HEAD"))
    private void processF3(int key, CallbackInfoReturnable<Boolean> ci) {
        if (key == 72 && !(this.client.player != null && this.client.player.isSneaking() && this.client.player.hasPermissionLevel(3))) {
            this.client.options.advancedItemTooltips = true;
        }
    }
}
