package com.projectblurple.blurplemod.mixin.rei.client;

import me.shedaniel.rei.api.client.registry.screen.OverlayDecider;
import me.shedaniel.rei.impl.client.registry.screen.ScreenRegistryImpl;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ScreenRegistryImpl.class)
public abstract class ScreenRegistryImplMixin {
    @Shadow(remap = false)
    private Class<? extends Screen> tmpScreen;

    @Inject(method = "filterResponsible",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void filterResponsible(OverlayDecider handler, CallbackInfoReturnable<Boolean> cir) {
        if (this.tmpScreen == null) cir.setReturnValue(false);
    }
}
