package com.projectblurple.blurplemod.mixin.create.client;

import com.simibubi.create.foundation.config.ui.OpenCreateMenuButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OpenCreateMenuButton.OpenConfigButtonHandler.class)
public abstract class OpenCreateMenuButton_OpenConfigButtonHandlerMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void onGuiInit(MinecraftClient client, Screen gui, int scaledWidth, int scaledHeight) {
    }
}
