package com.projectblurple.blurplemod.content.tag;

import com.projectblurple.blurplemod.BlurpleMod;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class BlurpleItemTags {
    public static final TagKey<Item> KEEP_ON_DEATH = TagKey.of(Registry.ITEM_KEY, BlurpleMod.id("keep_on_death"));
}
