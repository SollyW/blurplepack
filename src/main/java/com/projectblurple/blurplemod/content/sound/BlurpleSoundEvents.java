package com.projectblurple.blurplemod.content.sound;

import com.projectblurple.blurplemod.BlurpleMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class BlurpleSoundEvents {
    public static final SoundEvent MUSIC_DISC_DARKNESS = new SoundEvent(BlurpleMod.id("music_disc.darkness"));
    public static final SoundEvent MUSIC_DISC_DOG = new SoundEvent(BlurpleMod.id("music_disc.dog"));
    public static final SoundEvent MUSIC_DISC_DROOPY_LIKES_RICOCHET = new SoundEvent(BlurpleMod.id("music_disc.droopy_likes_ricochet"));
    public static final SoundEvent MUSIC_DISC_DROOPY_LIKES_YOUR_FACE = new SoundEvent(BlurpleMod.id("music_disc.droopy_likes_your_face"));
    public static final SoundEvent MUSIC_DISC_ELEVENT = new SoundEvent(BlurpleMod.id("music_disc.elevent"));
    public static final SoundEvent MUSIC_DISC_FLAKE = new SoundEvent(BlurpleMod.id("music_disc.flake"));
    public static final SoundEvent MUSIC_DISC_KI = new SoundEvent(BlurpleMod.id("music_disc.ki"));
    public static final SoundEvent MUSIC_DISC_KYOTO = new SoundEvent(BlurpleMod.id("music_disc.kyoto"));
    public static final SoundEvent MUSIC_DISC_MISSED_CALL = new SoundEvent(BlurpleMod.id("music_disc.missed_call"));
    public static final SoundEvent MUSIC_DISC_NEITHERSIDE = new SoundEvent(BlurpleMod.id("music_disc.neitherside"));
    public static final SoundEvent MUSIC_DISC_UWU = new SoundEvent(BlurpleMod.id("music_disc.uwu"));

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_DARKNESS.getId(), MUSIC_DISC_DARKNESS);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_DOG.getId(), MUSIC_DISC_DOG);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_DROOPY_LIKES_RICOCHET.getId(), MUSIC_DISC_DROOPY_LIKES_RICOCHET);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_DROOPY_LIKES_YOUR_FACE.getId(), MUSIC_DISC_DROOPY_LIKES_YOUR_FACE);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_ELEVENT.getId(), MUSIC_DISC_ELEVENT);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_FLAKE.getId(), MUSIC_DISC_FLAKE);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_KI.getId(), MUSIC_DISC_KI);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_KYOTO.getId(), MUSIC_DISC_KYOTO);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_MISSED_CALL.getId(), MUSIC_DISC_MISSED_CALL);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_NEITHERSIDE.getId(), MUSIC_DISC_NEITHERSIDE);
        Registry.register(Registry.SOUND_EVENT, MUSIC_DISC_UWU.getId(), MUSIC_DISC_UWU);
    }
}
