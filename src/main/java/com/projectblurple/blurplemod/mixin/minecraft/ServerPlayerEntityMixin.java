package com.projectblurple.blurplemod.mixin.minecraft;

import com.mojang.authlib.GameProfile;
import com.projectblurple.blurplemod.helper.minecraft.PlayerEntityExt;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements PlayerEntityExt {
    @Shadow
    @Final
    private ServerStatHandler statHandler;

    private ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "onDeath",
            at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        this.blurple_setTimeSinceDeath(this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_DEATH)));
    }

    @Redirect(method = "copyFrom",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean getBoolean(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        return instance.getBoolean(rule) || rule == GameRules.KEEP_INVENTORY;
    }

    @Redirect(method = "readCustomDataFromNbt",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/nbt/NbtCompound;contains(Ljava/lang/String;I)Z",
                    ordinal = 1))
    private boolean contains(NbtCompound instance, String key, int type) {
        return false;
    }
}
