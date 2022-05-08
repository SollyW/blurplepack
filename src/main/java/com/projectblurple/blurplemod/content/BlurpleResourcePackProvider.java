package com.projectblurple.blurplemod.content;

import io.github.fabricators_of_create.porting_lib.util.PathResourcePack;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;

import java.util.Optional;
import java.util.function.Consumer;

public class BlurpleResourcePackProvider implements ResourcePackProvider {
    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("blurplemod");
        try {
            profileAdder.accept(ResourcePackProfile.of("builtin/betterleaves",
                    true,
                    () -> new PathResourcePack("Better Leaves", container.orElseThrow()
                            .findPath("pack/betterleaves")
                            .orElseThrow()), factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));

            profileAdder.accept(ResourcePackProfile.of("builtin/journeyman",
                    true,
                    () -> new PathResourcePack("Journeyman", container.orElseThrow()
                            .findPath("pack/journeyman")
                            .orElseThrow()), factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));

            profileAdder.accept(ResourcePackProfile.of("builtin/vanillatweaks",
                    true,
                    () -> new PathResourcePack("Vanilla Tweaks", container.orElseThrow()
                            .findPath("pack/vanillatweaks")
                            .orElseThrow()), factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));

            profileAdder.accept(ResourcePackProfile.of("builtin/blurple",
                    true,
                    () -> new PathResourcePack("Blurple Resources", container.orElseThrow()
                            .findPath("pack/blurple")
                            .orElseThrow()), factory,
                    ResourcePackProfile.InsertionPosition.TOP,
                    ResourcePackSource.PACK_SOURCE_BUILTIN));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load blurple resource pack", e);
        }
    }
}
