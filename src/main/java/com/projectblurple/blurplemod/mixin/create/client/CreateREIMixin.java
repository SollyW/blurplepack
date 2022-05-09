package com.projectblurple.blurplemod.mixin.create.client;

import com.simibubi.create.compat.rei.CreateREI;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@SuppressWarnings("CancellableInjectionUsage")
@Mixin(CreateREI.class)
public abstract class CreateREIMixin {
    @Inject(method = "consumeTypedRecipes",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void getNetworkHandler(Consumer<Recipe<?>> consumer, RecipeType<?> type, CallbackInfo ci) {
//        if (MinecraftClient.getInstance().getNetworkHandler() == null) ci.cancel();
    }

    @Inject(method = "consumeAllRecipes",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void consumeAllRecipes(Consumer<Recipe<?>> consumer, CallbackInfo ci) {
//        if (MinecraftClient.getInstance().world == null) ci.cancel();
    }
}
