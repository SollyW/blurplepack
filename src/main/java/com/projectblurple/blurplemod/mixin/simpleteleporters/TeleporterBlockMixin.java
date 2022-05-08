package com.projectblurple.blurplemod.mixin.simpleteleporters;

import com.projectblurple.blurplemod.content.particle.BlurpleParticleTypes;
import com.shnupbups.simpleteleporters.block.TeleporterBlock;
import com.shnupbups.simpleteleporters.block.entity.TeleporterBlockEntity;
import com.shnupbups.simpleteleporters.init.SimpleTeleportersBlocks;
import com.shnupbups.simpleteleporters.init.SimpleTeleportersItems;
import com.shnupbups.simpleteleporters.item.TeleportCrystalItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TeleporterBlock.class)
public abstract class TeleporterBlockMixin {
    @Shadow
    @Final
    @Mutable
    protected static VoxelShape SHAPE;

    private int blurple_i = 0;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        SHAPE = VoxelShapes.union(
                Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.0D, 15.0D),
                Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 5.0D, 3.0D),
                Block.createCuboidShape(2.0D, 2.0D, 2.0D, 3.0D, 5.0D, 14.0D),
                Block.createCuboidShape(2.0D, 2.0D, 13.0D, 14.0D, 5.0D, 14.0D),
                Block.createCuboidShape(13.0D, 2.0D, 2.0D, 14.0D, 5.0D, 14.0D)
        ).simplify();
    }

    @Redirect(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private void addParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        if (blurple_i++ == 6) {
            instance.addParticle(BlurpleParticleTypes.TELEPORTER, x, y, z, velocityX, velocityY, velocityZ);
            blurple_i = 0;
        }
    }

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private Item getItem(ItemStack instance, BlockState state, World world, BlockPos pos) {
        if (instance.getItem() != Items.COMPASS
                || !TeleportCrystalItem.hasPosition(instance.getNbt())
                || !TeleportCrystalItem.getPosition(instance.getNbt()).isWithinDistance(pos, 128)) {
            return Items.AIR;
        }

        return SimpleTeleportersItems.ENDER_SHARD;
    }

    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;sendMessage(Lnet/minecraft/text/Text;Z)V"))
    private void sendMessage(PlayerEntity instance, Text message, boolean actionBar) {
    }

    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;sendMessage(Lnet/minecraft/text/Text;Z)V"))
    private void sendMessage2(ServerPlayerEntity instance, Text message, boolean actionBar) {
    }

    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;shouldSuffocate(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean shouldSuffocate(BlockState instance, BlockView blockView, BlockPos blockPos, BlockState state, World world) {
        if (world.getBlockState(blockPos.down()).getBlock() == Blocks.LODESTONE) {
            return instance.shouldSuffocate(blockView, blockPos);
        }

        return true;
    }

    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lcom/shnupbups/simpleteleporters/block/entity/TeleporterBlockEntity;getTeleportPos()Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos getTeleportPos(TeleporterBlockEntity instance) {
        BlockPos out = instance.getTeleportPos();
        if (out != null
                && instance.getWorld() != null
                && instance.getWorld().getBlockState(out).getBlock() == SimpleTeleportersBlocks.TELEPORTER
                && instance.getWorld().getBlockEntity(out) instanceof TeleporterBlockEntity teleporterBlockEntity) {
            teleporterBlockEntity.setCooldown(50);
        }
        return out;
    }

    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;requestTeleport(DDDFF)V"))
    private void requestTeleport(ServerPlayNetworkHandler instance, double x, double y, double z, float yaw, float pitch) {
        instance.requestTeleport(x, y + 0.3, z, yaw, pitch);
    }
}
