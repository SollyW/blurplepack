package com.projectblurple.blurplemod;

import com.projectblurple.blurplemod.content.particle.BlurpleParticleTypes;
import net.fabricmc.api.ClientModInitializer;

public class BlurpleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlurpleParticleTypes.initClient();
    }
}
