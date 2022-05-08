package com.projectblurple.blurplemod.mixin.waystones;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import wraith.waystones.block.WaystoneBlock;

@Mixin(WaystoneBlock.class)
public abstract class WaystoneBlockMixin {
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;sendMessage(Lnet/minecraft/text/Text;Z)V"))
    public void sendMessage(PlayerEntity instance, Text message, boolean actionBar) {
    }
}
