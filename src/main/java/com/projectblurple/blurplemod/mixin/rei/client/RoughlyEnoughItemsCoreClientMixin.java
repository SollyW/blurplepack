package com.projectblurple.blurplemod.mixin.rei.client;

import me.shedaniel.rei.RoughlyEnoughItemsCore;
import me.shedaniel.rei.RoughlyEnoughItemsCoreClient;
import me.shedaniel.rei.api.client.config.ConfigObject;
import me.shedaniel.rei.api.common.registry.ReloadStage;
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

    @SuppressWarnings("UnstableApiUsage")
    @Redirect(method = "reloadPlugins",
            at = @At(value = "INVOKE",
                    target = "Lme/shedaniel/rei/RoughlyEnoughItemsCore;_reloadPlugins(Lme/shedaniel/rei/api/common/registry/ReloadStage;)V"),
            remap = false)
    private static void _reloadPlugins(ReloadStage reloadStage) {
        try {
            RoughlyEnoughItemsCore._reloadPlugins(reloadStage);
        } catch (Exception ignored) {
        }
    }
}
