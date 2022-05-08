package com.projectblurple.blurplemod.mixin.minecraft.client;

import com.projectblurple.blurplemod.content.BlurpleResourcePackProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Arrays;
import java.util.Random;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Unique
    private static final String[] TITLES = {
            "why dont you want to be bacon?",
            "how tf do you breed your food",
            "I hate this",
            "@Rocked03, the server room is on \uD83D\uDD25!!",
            "is this bloody pancake our god now?",
            "iPantherr was squashed by QBACAx",
            "oops remove the ass",
            "so i reproduce-",
            "as tasty as that egg your about to lay",
            "itsboy sucks btw",
            "shall i kill the children?",
            "i got a new toothbrush today,",
            "anAutomated external defibrillator",
            "special sugar",
            "i am a furry uwu",
            "Chair is having a baby",
            "QBACAx fell from a high place",
            "PEEPEE WATERFALL",
            "Communal Piss Bottle",
            "Is this for us?",
            "I made a fun toy",
            "pr yo van smnlet ore",
            "oh cool lets breed",
            "i love sussy amogus butt",
            "uwu",
            "Qbaca is honorary gamer girl",
            "can i have qbacas urine uwu owo",
            "Jesus was squashed by a falling anvil",
            "do what you want. i cant unfuck your mom",
            "are you a shulker? because i want to murder a city of you for your loot",
            "solly's playing with the sexy piston",
            "*unzips pants*",
            "villager hanging because better mending trade found (not clickbait)",
            "oh noo i lost boy's private parts",
            "mmmm, children",
            "why do blenders sound like a child screaming",
            "Imma sleep next to you uwu uwu",
            "hehe im a maggot",
            "Everyone needs to report the issue to speed up the fixing",
            "DDOSING PB'S MINECRAFT SERVER AT 1 AM !!!!!!!!!",
            "is it good that it's using less ram?",
            "noice",
            "Prison opening is temporarily delayed as we wrap up development",
            "let's go recruit some cultist member and aim for world domination"
    };

    @Unique
    private String windowTitle;

    /**
     * @author Solly
     * @reason aaaa
     */
    @Overwrite
    private String getWindowTitle() {
        return windowTitle == null
                ? windowTitle = "PBMC: " + TITLES[new Random().nextInt(TITLES.length)]
                : windowTitle;
    }

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyArg(method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourcePackProfile$Factory;[Lnet/minecraft/resource/ResourcePackProvider;)V"),
            index = 1)
    private ResourcePackProvider[] resourcePackManagers(ResourcePackProvider[] providers) {
        ResourcePackProvider[] copy = Arrays.copyOf(providers, providers.length + 1);
        copy[providers.length] = new BlurpleResourcePackProvider();
        return copy;
    }

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyArg(method = "createServerDataManager",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourceType;[Lnet/minecraft/resource/ResourcePackProvider;)V"),
            index = 1)
    private static ResourcePackProvider[] createServerDataManager(ResourcePackProvider[] providers) {
        ResourcePackProvider[] copy = Arrays.copyOf(providers, providers.length + 1);
        copy[providers.length] = new BlurpleResourcePackProvider();
        return copy;
    }
}
