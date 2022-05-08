package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import paulevs.betternether.world.structures.city.CityFeature;

@Mixin(CityFeature.class)
public abstract class CityFeatureMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    private static <C extends FeatureConfig> boolean checkLocation(StructureGeneratorFactory.Context<C> context) {
        return false;
    }
}
