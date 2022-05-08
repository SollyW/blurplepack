package com.projectblurple.blurplemod.mixin.villagerconfig;

import me.drex.villagerconfig.config.ConfigEntries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfigEntries.FeaturesGroup.class)
public abstract class ConfigEntries_FeaturesGroupMixin {
    @Shadow(remap = false)
    public double maxDiscount;

    @Shadow(remap = false)
    public double conversionChance;

    @Shadow(remap = false)
    public boolean tradeCycling;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(CallbackInfo ci) {
        this.maxDiscount = 30;
        this.conversionChance = 100;
        this.tradeCycling = false;
    }
}
