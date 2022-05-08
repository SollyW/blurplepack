package com.projectblurple.blurplemod;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

import java.nio.file.Files;
import java.util.*;

public class DisabledRecipes {
    public static final Set<Identifier> DISABLED_RECIPES = new HashSet<>();

    static {
        Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("blurplemod");
        try {
            for (String line : Files.readAllLines(container.orElseThrow()
                    .findPath("disabled_recipes")
                    .orElseThrow())) {
                try {
                    DISABLED_RECIPES.add(new Identifier(line));
                } catch (InvalidIdentifierException ignored) {
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load disabled recipes", e);
        }
    }
}
