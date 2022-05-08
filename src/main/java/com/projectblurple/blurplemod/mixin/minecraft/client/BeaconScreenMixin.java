package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeaconScreen.class)
public abstract class BeaconScreenMixin {
    @Redirect(method = "init",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/block/entity/BeaconBlockEntity;EFFECTS_BY_LEVEL:[[Lnet/minecraft/entity/effect/StatusEffect;"))
    private StatusEffect[][] getEffectsByLevel() {
        return new StatusEffect[0][];
    }
}
