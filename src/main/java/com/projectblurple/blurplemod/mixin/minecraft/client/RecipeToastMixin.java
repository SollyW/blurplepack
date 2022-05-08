package com.projectblurple.blurplemod.mixin.minecraft.client;

import net.minecraft.client.toast.RecipeToast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RecipeToast.class)
public abstract class RecipeToastMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public static void show(ToastManager manager, Recipe<?> recipes) {
    }
}
