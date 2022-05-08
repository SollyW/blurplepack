package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin {
    @ModifyArg(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index = 4)
    private int colour(MatrixStack matrices, Text text, float x, float y, int color) {
        return 0xbbbbbb;
    }
}
