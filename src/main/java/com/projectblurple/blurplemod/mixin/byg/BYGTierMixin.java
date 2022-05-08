package com.projectblurple.blurplemod.mixin.byg;

import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.common.item.BYGTier;

import java.util.function.Supplier;

@Mixin(BYGTier.class)
public abstract class BYGTierMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private float attackDamage;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn, CallbackInfo ci) {
        if (harvestLevelIn == 5) this.attackDamage = 2.25f;
    }
}
