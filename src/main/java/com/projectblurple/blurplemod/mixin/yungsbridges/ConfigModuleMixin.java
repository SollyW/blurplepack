package com.projectblurple.blurplemod.mixin.yungsbridges;

import com.yungnickyoung.minecraft.yungsbridges.module.ConfigModule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfigModule.class)
public abstract class ConfigModuleMixin {
    @Shadow(remap = false)
    public ConfigModule.SpawnRates spawnRates;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(CallbackInfo ci) {
        this.spawnRates.largeBridges = 1;
        this.spawnRates.mediumBridges = 1;
    }
}
