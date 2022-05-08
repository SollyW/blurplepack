package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulevs.betternether.items.materials.BNToolMaterial;

@Mixin(BNToolMaterial.class)
public abstract class BNToolMaterialMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private float damage;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(String id, int ordinal, int level, int uses, float speed, float damage, int enchantibility, ItemConvertible reapair, CallbackInfo ci) {
        switch (id) {
            case "CINCINNASITE" -> this.damage = 1f;
            case "CINCINNASITE_DIAMOND" -> this.damage = 1.5f;
            case "NETHER_RUBY" -> this.damage = 1.75f;
        }
    }
}
