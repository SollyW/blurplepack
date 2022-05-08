package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import paulevs.betternether.blocks.complex.NetherWoodenMaterial;
import ru.bclib.config.PathConfig;
import ru.bclib.recipes.GridRecipe;

@Mixin(NetherWoodenMaterial.class)
public abstract class NetherWoodenMaterialMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void makeTaburetRecipe(PathConfig config, Identifier id, Block taburet, Block planks) {
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void makeChairRecipe(PathConfig config, Identifier id, Block chair, Block planks) {
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite(remap = false)
    public static void makeBarStoolRecipe(PathConfig config, Identifier id, Block barStool, Block planks) {
    }
}
