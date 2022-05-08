package com.projectblurple.blurplemod.mixin.betterend;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import ru.bclib.recipes.GridRecipe;
import ru.betterend.complexmaterials.StoneMaterial;

@Mixin(StoneMaterial.class)
public abstract class StoneMaterialMixin {
    @Redirect(method = "<init>",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=end_stone_ITEM_FURNACES")),
            at = @At(value = "INVOKE",
                    target = "Lru/bclib/recipes/GridRecipe;build()V"),
            remap = false)
    private void build(GridRecipe instance) {
    }
}
