package com.projectblurple.blurplemod.mixin.bclib;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import ru.bclib.recipes.CraftingRecipes;

@Mixin(CraftingRecipes.class)
public abstract class CraftingRecipesMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void init() {
    }
}
