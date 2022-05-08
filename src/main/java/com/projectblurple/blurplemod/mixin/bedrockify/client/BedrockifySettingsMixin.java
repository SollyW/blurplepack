package com.projectblurple.blurplemod.mixin.bedrockify.client;

import com.projectblurple.blurplemod.ClientConfig;
import me.juancarloscp52.bedrockify.BedrockifySettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BedrockifySettings.class)
public abstract class BedrockifySettingsMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public boolean isReacharoundEnabled() {
        return ClientConfig.reacharound;
    }
}
