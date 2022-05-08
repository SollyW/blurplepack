package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EndSpikeFeature.Spike.class)
public abstract class EndSpikeFeature_SpikeMixin {
    @ModifyVariable(method = "<init>",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true)
    private static int modifyHeight(int value) {
        return value * 8 / 7 + 10;
    }

    @ModifyVariable(method = "<init>",
            at = @At("HEAD"),
            index = 5,
            argsOnly = true)
    private static boolean modifyGuarded(boolean value) {
        return true;
    }
}
