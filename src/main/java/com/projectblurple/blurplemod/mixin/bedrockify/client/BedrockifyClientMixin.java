package com.projectblurple.blurplemod.mixin.bedrockify.client;

import me.juancarloscp52.bedrockify.client.BedrockifyClient;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedrockifyClient.class)
public abstract class BedrockifyClientMixin {
    @Redirect(method = "onInitializeClient", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/keybinding/v1/KeyBindingHelper;registerKeyBinding(Lnet/minecraft/client/option/KeyBinding;)Lnet/minecraft/client/option/KeyBinding;"))
    private KeyBinding registerKeyBinding(KeyBinding keyBinding) {
        return keyBinding;
    }
}
