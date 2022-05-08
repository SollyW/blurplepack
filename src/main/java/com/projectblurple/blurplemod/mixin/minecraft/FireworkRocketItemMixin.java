package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireworkRocketItem.class)
public abstract class FireworkRocketItemMixin {
    @Redirect(method = "use",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;isFallFlying()Z"))
    private boolean isFallFlying(PlayerEntity instance) {
        return false;
    }
}
