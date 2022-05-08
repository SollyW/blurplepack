package com.projectblurple.blurplemod.mixin.betterend;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import potionstudios.byg.common.item.BYGItems;
import ru.betterend.recipe.AlloyingRecipes;
import ru.betterend.recipe.builders.AlloyingRecipe;
import ru.betterend.registry.EndBlocks;
import ru.betterend.registry.EndItems;

@Mixin(AlloyingRecipes.class)
public abstract class AlloyingRecipesMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void register() {
        AlloyingRecipe.Builder.create("aeternium_ingot").setInput(EndBlocks.TERMINITE.ingot, BYGItems.AMETRINE_GEMS).setOutput(EndItems.AETERNIUM_INGOT, 1).setExpiriense(4.5F).setSmeltTime(18520).build();
        AlloyingRecipe.Builder.create("terminite_ingot_thallasium").setInput(EndBlocks.THALLASIUM.ingot, EndItems.ENDER_DUST).setOutput(EndBlocks.TERMINITE.ingot, 1).setExpiriense(2.5F).setSmeltTime(1450).build();
    }
}
