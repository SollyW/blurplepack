package com.projectblurple.blurplemod.mixin.betterend;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.betterend.recipe.SmithingRecipes;

@Mixin(SmithingRecipes.class)
public abstract class SmithingRecipesMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void register() {
    }
}
