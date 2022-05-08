package com.projectblurple.blurplemod.mixin.minecraft;

import com.projectblurple.blurplemod.helper.minecraft.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {
    @Shadow
    public int totalExperience;

    private static final TrackedData<Integer> BLURPLE_TIME_SINCE_DEATH = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Shadow
    public abstract void addExperienceLevels(int levels);

    private PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(BLURPLE_TIME_SINCE_DEATH, -1);
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    public int getNextLevelExperience() {
        return 36;
    }

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    protected int getXpToDrop(PlayerEntity player) {
        return this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || this.isSpectator()
                ? 0 : Math.min(this.totalExperience, 576);
    }

    @Override
    protected void dropXp() {
        super.dropXp();
        this.addExperienceLevels(-16);
    }

    @Override
    public int blurple_getTimeSinceDeath() {
        return this.dataTracker.get(BLURPLE_TIME_SINCE_DEATH);
    }

    @Override
    public void blurple_setTimeSinceDeath(int value) {
        this.dataTracker.set(BLURPLE_TIME_SINCE_DEATH, value);
    }
}
