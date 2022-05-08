package com.projectblurple.blurplemod.mixin.minecraft.client;

import com.projectblurple.blurplemod.helper.minecraft.PlayerEntityExt;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin extends Screen {
    @Shadow
    private Text scoreText;

    private boolean awaitingLifetime = true;

    private DeathScreenMixin(Text title) {
        super(title);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "render", at = @At("HEAD"))
    private void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (awaitingLifetime) {
            int lifetime = ((PlayerEntityExt) this.client.player).blurple_getTimeSinceDeath();
            if (lifetime != -1) {
                int[] seperator = {0};

                String newText = blurple_format(lifetime / 20 % 60, " second", seperator);
                newText = blurple_format(lifetime / 1200 % 60, " minute", seperator) + newText;
                newText = blurple_format(lifetime / 72000 % 24, " hour", seperator) + newText;
                newText = blurple_format(lifetime / 1728000, " day", seperator) + newText;

                this.scoreText = new LiteralText("Lived for " + newText);
                awaitingLifetime = false;
            }
        }
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.scoreText = new LiteralText("f");
    }

    private static String blurple_format(int time, String unit, int[] seperator) {
        if (time == 0) return "";

        String sepText = switch (seperator[0]) {
            case 0 -> "";
            case 1 -> " and ";
            default -> ", ";
        };

        seperator[0]++;

        return (time == 1) ? time + unit + sepText
        : time + unit + "s" + sepText;
    }
}
