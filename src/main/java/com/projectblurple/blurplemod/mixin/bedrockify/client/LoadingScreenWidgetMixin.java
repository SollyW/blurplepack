package com.projectblurple.blurplemod.mixin.bedrockify.client;

import me.juancarloscp52.bedrockify.client.features.loadingScreens.LoadingScreenWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LoadingScreenWidget.class)
public abstract class LoadingScreenWidgetMixin {
    private static final String[] BLURPLE_TIPS = {
            "Welcome to PBMC 2022!"
    };

    @Shadow
    private Text tip;

    @Inject(method = "getTip", at = @At(value = "FIELD", target = "Lme/juancarloscp52/bedrockify/client/features/loadingScreens/LoadingScreenWidget;lastTipUpdate:J", opcode = Opcodes.PUTFIELD))
    private void setTip(CallbackInfoReturnable<Text> cir) {
        this.tip = new LiteralText(BLURPLE_TIPS[new Random().nextInt(BLURPLE_TIPS.length)]);
    }
}
