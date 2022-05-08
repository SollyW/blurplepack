package com.projectblurple.blurplemod.mixin.byg;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.common.world.biome.LayersBiomeData;
import potionstudios.byg.common.world.biome.end.EndBiomesConfig;

@Mixin(EndBiomesConfig.class)
public abstract class EndBiomesConfigMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private boolean forceBYGEndBiomeSource;

    @Inject(method = "<init>",
            at = @At("RETURN"))
    private void init(boolean forceBYGEndBiomeSource, boolean addAllEndBiomeCategoryEntries, int skyLayerStartY, LayersBiomeData islandLayers, LayersBiomeData voidLayers, LayersBiomeData skyLayers, CallbackInfo ci) {
        this.forceBYGEndBiomeSource = false;
    }
}
