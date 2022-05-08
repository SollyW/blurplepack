package com.projectblurple.blurplemod.mixin.iris.client;

import net.coderbot.iris.Iris;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Iris.class)
public abstract class IrisMixin {
    @Redirect(method = "onEarlyInitialize",
            at = @At(value = "INVOKE",
                    target = "Lnet/fabricmc/fabric/api/client/keybinding/v1/KeyBindingHelper;registerKeyBinding(Lnet/minecraft/client/option/KeyBinding;)Lnet/minecraft/client/option/KeyBinding;"),
            remap = false)
    private KeyBinding registerKeyBinding(KeyBinding keyBinding) {
        KeyBinding newBinding = new KeyBinding(keyBinding.getTranslationKey(), InputUtil.UNKNOWN_KEY.getCode(), KeyBinding.MISC_CATEGORY);
        if (keyBinding.getTranslationKey().equals("iris.keybind.toggleShaders")){
            return KeyBindingHelper.registerKeyBinding(newBinding);
        }
        return newBinding;
    }
}
