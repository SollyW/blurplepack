package com.projectblurple.blurplemod.mixin.minecraft;

import com.google.gson.JsonElement;
import com.projectblurple.blurplemod.DisabledRecipes;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @ModifyVariable(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
            index = 1,
            at = @At("HEAD"), argsOnly = true)
    private Map<Identifier, JsonElement> filterDisabledRecipes(Map<Identifier, JsonElement> value) {
        return value.entrySet().stream()
                .filter(entry -> !(DisabledRecipes.DISABLED_RECIPES.contains(entry.getKey())
                        || "bedrockify".equals(entry.getKey().getNamespace())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
