package com.projectblurple.blurplemod.mixin.betterend;

import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.betterend.item.material.EndToolMaterial;

import java.util.function.Supplier;

@Mixin(EndToolMaterial.class)
public abstract class EndToolMaterialMixin {
    @Shadow(remap = false)
    @Final
    @Mutable
    private float attackDamage;

    @Inject(method = "<init>",
            at = @At("TAIL"),
            remap = false)
    private void init(String id, int ordinal, int miningLevel, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient, CallbackInfo ci) {
        switch (id) {
            case "THALLASIUM" -> this.attackDamage = 1.5f;
            case "TERMINITE" -> this.attackDamage = 3f;
            case "AETERNIUM" -> this.attackDamage = 3.5f;
        }
    }
}
