package com.projectblurple.blurplemod.content.item;

import com.projectblurple.blurplemod.BlurpleMod;
import com.projectblurple.blurplemod.content.sound.BlurpleSoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class BlurpleItems {
    public static final Item MUSIC_DISC_DARKNESS = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_DARKNESS, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_DOG = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_DOG, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_DROOPY_LIKES_RICOCHET = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_DROOPY_LIKES_RICOCHET, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_DROOPY_LIKES_YOUR_FACE = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_DROOPY_LIKES_YOUR_FACE, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_ELEVENT = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_ELEVENT, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_FLAKE = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_FLAKE, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_KI = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_KI, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_KYOTO = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_KYOTO, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_MISSED_CALL = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_MISSED_CALL, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_NEITHERSIDE = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_NEITHERSIDE, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};
    public static final Item MUSIC_DISC_UWU = new MusicDiscItem(7, BlurpleSoundEvents.MUSIC_DISC_UWU, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)) {};

    public static void init() {
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_darkness"), MUSIC_DISC_DARKNESS);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_dog"), MUSIC_DISC_DOG);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_droopy_likes_ricochet"), MUSIC_DISC_DROOPY_LIKES_RICOCHET);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_droopy_likes_your_face"), MUSIC_DISC_DROOPY_LIKES_YOUR_FACE);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_elevent"), MUSIC_DISC_ELEVENT);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_flake"), MUSIC_DISC_FLAKE);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_ki"), MUSIC_DISC_KI);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_kyoto"), MUSIC_DISC_KYOTO);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_missed_call"), MUSIC_DISC_MISSED_CALL);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_neitherside"), MUSIC_DISC_NEITHERSIDE);
        Registry.register(Registry.ITEM, BlurpleMod.id("music_disc_uwu"), MUSIC_DISC_UWU);
    }
}
