package com.projectblurple.blurplemod.mixin.rei.client;

import me.shedaniel.rei.RoughlyEnoughItemsCoreClient;
import me.shedaniel.rei.api.client.config.ConfigObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RoughlyEnoughItemsCoreClient.class)
public abstract class RoughlyEnoughItemsCoreClientMixin {
    @Redirect(method = "reloadPlugins",
            at = @At(value = "INVOKE",
                    target = "Lme/shedaniel/rei/api/client/config/ConfigObject;doesRegisterRecipesInAnotherThread()Z"),
            remap = false)
    private static boolean doesRegisterRecipesInAnotherThread(ConfigObject instance) {
        return false;
    }
}
