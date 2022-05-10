package com.projectblurple.blurplemod.mixin.minecraft;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFightMixin {
    @Redirect(method = "dragonKilled",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/entity/boss/dragon/EnderDragonFight;previouslyKilled:Z",
                    ordinal = 0))
    private boolean getPreviouslyKilled(EnderDragonFight instance) {
        return instance.hasPreviouslyKilled();
    }
}
