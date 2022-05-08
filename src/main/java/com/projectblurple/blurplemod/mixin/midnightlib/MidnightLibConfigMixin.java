package com.projectblurple.blurplemod.mixin.midnightlib;

import eu.midnightdust.core.config.MidnightLibConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MidnightLibConfig.class)
public abstract class MidnightLibConfigMixin {
    @Shadow(remap = false)
    public static MidnightLibConfig.ConfigButton config_screen_list;

    @Shadow(remap = false)
    public static boolean special_hats;

    @Inject(method = "<clinit>",
            at = @At("TAIL"),
            remap = false)
    private static void clinit(CallbackInfo ci) {
        config_screen_list = MidnightLibConfig.ConfigButton.FALSE;
        special_hats = false;
    }
}
