package com.projectblurple.blurplemod.mixin.betterend;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import ru.betterend.recipe.InfusionRecipes;
import ru.betterend.recipe.builders.InfusionRecipe;
import ru.betterend.registry.EndItems;

@Mixin(InfusionRecipes.class)
public abstract class InfusionRecipesMixin {
    @Redirect(method = "register",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=crystalite_elytra")),
            at = @At(value = "INVOKE",
                    target = "Lru/betterend/recipe/builders/InfusionRecipe$Builder;setTime(I)Lru/betterend/recipe/builders/InfusionRecipe$Builder;",
                    ordinal = 0),
            remap = false)
    private static InfusionRecipe.Builder setTime(InfusionRecipe.Builder instance, int time) {
        instance.addCatalyst(4, EndItems.CRYSTALITE_CHESTPLATE);
        instance.setTime(time);
        return instance;
    }
}
