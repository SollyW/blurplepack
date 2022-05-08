package com.projectblurple.blurplemod.mixin.betternether;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import paulevs.betternether.world.structures.piece.CityPiece;

import java.util.Random;

@Mixin(CityPiece.class)
public abstract class CityPieceMixin {
    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public void generate(StructureWorldAccess world, StructureAccessor arg, ChunkGenerator chunkGenerator, Random random, BlockBox blockBox, ChunkPos chunkPos, BlockPos blockPos) {
    }
}
