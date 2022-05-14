package com.projectblurple.blurplemod.mixin.rei.client;

import com.projectblurple.blurplemod.BlurpleMod;
import me.shedaniel.rei.RoughlyEnoughItemsCore;
import me.shedaniel.rei.RoughlyEnoughItemsCoreClient;
import me.shedaniel.rei.api.client.config.ConfigObject;
import me.shedaniel.rei.api.common.registry.ReloadStage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.text.LiteralText;
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
        BlurpleMod.LOGGER.warn("REI connection error: ");
        try {
            RoughlyEnoughItemsCore._reloadPlugins(reloadStage);
        } catch (Exception e) {
            BlurpleMod.LOGGER.warn("REI connection error: " + e.getMessage());
            MinecraftClient.getInstance().setScreen(new DisconnectedScreen(
                    MinecraftClient.getInstance().currentScreen,
                    new LiteralText("Disconnected"),
                    new LiteralText("An error ocurred whilst connecting. Try restarting your client.")));
        }
    }
}
