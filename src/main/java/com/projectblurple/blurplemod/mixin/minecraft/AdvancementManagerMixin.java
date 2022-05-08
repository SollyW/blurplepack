package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AdvancementManager.class)
public abstract class AdvancementManagerMixin {
    @Inject(method = "load",
            at = @At("HEAD"),
            cancellable = true)
    private void load(Map<Identifier, Advancement.Builder> map, CallbackInfo ci) {
        ci.cancel();
    }
}
