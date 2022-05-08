package com.projectblurple.blurplemod;

import com.projectblurple.blurplemod.content.item.BlurpleItems;
import com.projectblurple.blurplemod.content.particle.BlurpleParticleTypes;
import com.projectblurple.blurplemod.content.sound.BlurpleSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.resource.ClientBuiltinResourcePackProvider;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class BlurpleMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("blurplemod");
	public static final UUID SOLLY = new UUID(0x004679d731634e06L, 0xa36f8c6c531d7681L);
	public static final String BLURPLEMOD = "blurplemod";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		BlurpleParticleTypes.init();
		BlurpleSoundEvents.init();
		BlurpleItems.init();
	}

	public static Identifier id(String id) {
		return new Identifier(BLURPLEMOD, id);
	}
}
