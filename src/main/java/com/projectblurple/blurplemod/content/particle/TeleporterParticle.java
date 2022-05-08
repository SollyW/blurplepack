package com.projectblurple.blurplemod.content.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class TeleporterParticle extends PortalParticle {
    protected TeleporterParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        //this.colorGreen = this.random.nextFloat(0.4f) + 0.5f;
        //this.colorBlue = (1 - this.colorGreen) * 0.4f + 0.6f;
        //this.colorRed = 2 * Math.abs(this.colorBlue - 0.8f) + 0.6f;
        this.red = 0.8f + this.random.nextFloat(0.2f);
        this.green = 0.8f + this.random.nextFloat(0.2f);
        this.blue = 0.8f + this.random.nextFloat(0.2f);
    }

    @Environment(value = EnvType.CLIENT)
    public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            TeleporterParticle particle = new TeleporterParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setSprite(this.spriteProvider);
            return particle;
        }
    }
}
