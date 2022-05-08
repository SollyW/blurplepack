package com.projectblurple.blurplemod.mixin.charm.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import svenhjol.charm.block.CharmBlock;
import svenhjol.charm.loader.CharmModule;
import svenhjol.charm.module.block_of_ender_pearls.EnderPearlBlock;

import java.util.Random;

@Mixin(EnderPearlBlock.class)
public abstract class EnderPearlBlockMixin extends CharmBlock {
    private EnderPearlBlockMixin(CharmModule module, String name, Settings props) {
        super(module, name, props);
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Environment(EnvType.CLIENT)
    @Overwrite
    public void randomDisplayTick(BlockState state, World level, BlockPos pos, Random random) {
        super.randomDisplayTick(state, level, pos, random);
    }
}
