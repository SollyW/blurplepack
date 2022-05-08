package com.projectblurple.blurplemod.mixin.betterend.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.betterend.client.BetterEndClient;

@Mixin(BetterEndClient.class)
public abstract class BetterEndClientMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void registerTooltips() {
    }
}
