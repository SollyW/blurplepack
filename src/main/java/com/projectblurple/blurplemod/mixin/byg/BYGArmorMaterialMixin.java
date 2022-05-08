package com.projectblurple.blurplemod.mixin.byg;

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import potionstudios.byg.common.item.BYGArmorMaterial;

import java.util.function.Supplier;

@Mixin(BYGArmorMaterial.class)
public abstract class BYGArmorMaterialMixin {
    @Shadow(remap = false)
    @Final
    private int[] damageReductionAmountArray;

    @Shadow(remap = false)
    @Final
    @Mutable
    private float toughness;

    @Shadow(remap = false)
    @Final
    private String name;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn, Supplier<Ingredient> repairMaterialSupplier, CallbackInfo ci) {
        if ("ametrine".equals(nameIn)) {
            this.damageReductionAmountArray[0] = 3;
            this.damageReductionAmountArray[1] = 6;
            this.damageReductionAmountArray[2] = 8;
            this.damageReductionAmountArray[3] = 3;
            this.toughness = 1f;
        }
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public float getKnockbackResistance() {
        return "ametrine".equals(this.name) ? 0.75f : 0f;
    }
}
