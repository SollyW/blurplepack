package com.projectblurple.blurplemod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.shedaniel.rei.api.client.config.entry.EntryStackProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.nbt.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.*;

@SuppressWarnings("UnstableApiUsage")
public class ReiFilter {
    public static final List<EntryStackProvider<?>> FILTERED_STACKS = new ArrayList<>();

    static {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("blurplemod");
        if (container.isPresent()) {
            try {
                for (String line : Files.readAllLines(container.get()
                        .findPath("rei_filtered")
                        .orElseThrow(IOException::new))) {
                    try {
                        FILTERED_STACKS.add(EntryStackProvider.defer(StringNbtReader.parse(line)));
                    } catch (CommandSyntaxException ignored) { }
                }
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to load rei filter", e);
            }
        } else {
            throw new RuntimeException("Failed to load rei filter");
        }
    }
}
