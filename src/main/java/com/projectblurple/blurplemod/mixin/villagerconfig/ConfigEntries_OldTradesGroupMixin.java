package com.projectblurple.blurplemod.mixin.villagerconfig;

import me.drex.villagerconfig.config.ConfigEntries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfigEntries.OldTradesGroup.class)
public abstract class ConfigEntries_OldTradesGroupMixin {
    @Shadow(remap = false)
    public boolean enabled;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(CallbackInfo ci) {
        this.enabled = true;
    }
}
