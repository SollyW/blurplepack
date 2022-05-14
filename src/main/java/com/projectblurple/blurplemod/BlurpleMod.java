package com.projectblurple.blurplemod;

import com.projectblurple.blurplemod.content.item.BlurpleItems;
import com.projectblurple.blurplemod.content.particle.BlurpleParticleTypes;
import com.projectblurple.blurplemod.content.sound.BlurpleSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;

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

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
				dispatcher.register(literal("home").executes(context -> {
					ServerPlayerEntity player = context.getSource().getPlayer();

					ServerWorld world = context.getSource().getServer().getWorld(player.getSpawnPointDimension());
					BlockPos pos = player.getSpawnPointPosition();

					Optional<Vec3d> maybeRespawnPosition = world != null && pos != null
							? PlayerEntity.findRespawnPosition(world, pos, player.getSpawnAngle(),
									true, true)
							: Optional.empty();

					ServerWorld respawnWorld = maybeRespawnPosition.isPresent()
							? world
							: context.getSource().getServer().getOverworld();

					Vec3d respawnPosition = maybeRespawnPosition.isPresent()
							? maybeRespawnPosition.get()
							: Vec3d.ofCenter(respawnWorld.getSpawnPos());

					ChunkPos chunkPos = new ChunkPos(new BlockPos(respawnPosition));
					respawnWorld.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT,
							chunkPos, 1, player.getId());
					player.stopRiding();
					if (player.isSleeping()) {
						player.wakeUp(true, true);
					}
					if (world == player.world) {
						player.networkHandler.requestTeleport(respawnPosition.getX(),
								respawnPosition.getY(),
								respawnPosition.getZ(),
								player.getYaw(),
								player.getPitch());
					} else {
						player.teleport(world,
								respawnPosition.getX(),
								respawnPosition.getY(),
								respawnPosition.getZ(),
								player.getYaw(),
								player.getPitch());
					}

					return 1;
		})));
	}

	public static Identifier id(String id) {
		return new Identifier(BLURPLEMOD, id);
	}
}
