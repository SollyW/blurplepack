package com.projectblurple.blurplemod.mixin.waystones;

import com.projectblurple.blurplemod.content.particle.BlurpleParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wraith.waystones.block.WaystoneBlockEntity;
import wraith.waystones.registry.BlockRegistry;

@Mixin(WaystoneBlockEntity.class)
public abstract class WaystoneBlockEntityMixin extends LootableContainerBlockEntity {
    @Shadow(remap = false)
    private String name;

    private WaystoneBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V")
    private void init(BlockPos pos, BlockState state, CallbackInfo ci) {
        this.name = pos.getX() + ", " + pos.getZ();
    }

    @Redirect(method = "addParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    public void addParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        if (parameters == ParticleTypes.PORTAL && instance.getBlockState(this.pos).getBlock() == BlockRegistry.WAYSTONE) {
            instance.addParticle(BlurpleParticleTypes.TELEPORTER, x, y - 0.4, z, velocityX, velocityY, velocityZ);
        }
    }
}
