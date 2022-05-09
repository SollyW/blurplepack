package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin {
    @SuppressWarnings("DefaultAnnotationParam")
    @Redirect(method = "method_19836",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/MinecraftClient;isInSingleplayer()Z",
                    remap = true),
            remap = false)
    private boolean isInSingleplayer(MinecraftClient instance) {
        return true;
    }
}
