package com.projectblurple.blurplemod.content.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlurpleParticleTypes {
    public static final DefaultParticleType TELEPORTER = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("blurplemod", "teleporter"), TELEPORTER);
    }

    public static void initClient() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) ->
                registry.register(new Identifier("blurplemod", "particle/teleporter")));

        ParticleFactoryRegistry.getInstance().register(TELEPORTER, TeleporterParticle.Factory::new);
    }
}
