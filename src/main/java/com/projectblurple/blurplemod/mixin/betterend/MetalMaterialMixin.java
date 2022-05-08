package com.projectblurple.blurplemod.mixin.betterend;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import ru.bclib.blocks.BaseOreBlock;
import ru.bclib.recipes.AnvilRecipe;
import ru.bclib.recipes.GridRecipe;
import ru.bclib.recipes.SmithingTableRecipe;
import ru.betterend.complexmaterials.MetalMaterial;
import ru.betterend.recipe.builders.AlloyingRecipe;

import java.util.function.Supplier;

@Mixin(MetalMaterial.class)
public abstract class MetalMaterialMixin {
    @Redirect(method = "<init>",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=end_metal_chandelier")),
            at = @At(value = "INVOKE",
                    target = "Lru/bclib/recipes/GridRecipe;build()V"),
            remap = false)
    private void build(GridRecipe instance) {
    }

    @Redirect(method = "<init>",
            slice = @Slice(
                    from = @At(value = "CONSTANT",
                            args = "stringValue=end_metal_anvil")),
            at = @At(value = "INVOKE",
                    target = "Lru/bclib/recipes/GridRecipe;build()V",
                    ordinal = 0),
            remap = false)
    private void build0(GridRecipe instance, String name) {
    }

    @Redirect(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lru/bclib/recipes/SmithingTableRecipe;build()V"),
            remap = false)
    private void build(SmithingTableRecipe instance) {
    }

    @Redirect(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lru/bclib/recipes/AnvilRecipe$Builder;build()V"),
            remap = false)
    private void build(AnvilRecipe.Builder instance) {
    }

    @Redirect(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lru/betterend/recipe/builders/AlloyingRecipe$Builder;build()V"),
            remap = false)
    private void build(AlloyingRecipe.Builder instance) {
    }

    @SuppressWarnings("All")
    @Redirect(method = "<init>",
            at = @At(value = "NEW",
                    target = "(Ljava/util/function/Supplier;III)Lru/bclib/blocks/BaseOreBlock;"),
            remap = false)
    private static BaseOreBlock baseOreBlock(Supplier<Item> drop, int minCount, int maxCount, int experience, String name) {
        if ("thallasium".equals(name)) return new BaseOreBlock(drop, 0, 1, experience);
        return new BaseOreBlock(drop, minCount, maxCount, experience);
    }
}
