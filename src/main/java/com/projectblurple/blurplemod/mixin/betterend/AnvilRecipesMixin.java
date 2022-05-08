package com.projectblurple.blurplemod.mixin.betterend;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.betterend.recipe.AnvilRecipes;

@Mixin(AnvilRecipes.class)
public abstract class AnvilRecipesMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void register() {
    }
}
