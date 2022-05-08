package com.projectblurple.blurplemod;

import me.shedaniel.rei.api.common.entry.EntryStack;

import java.util.Comparator;

public class WordReverser {
    public static final Comparator<? super EntryStack<?>> REI_COMPARATOR = Comparator.comparing(stack ->
            WordReverser.reverse(stack.asFormatStrippedText().getString()));

    public static String reverse(String string) {
        char[] words = string.toCharArray();
        char[] out = new char[words.length];
        int end = words.length;
        int h = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            if (i == 0) {
                for (int j = i; j < end; j++) out[h++] = words[j];
                break;
            }

            if (words[i] == ' '){
                for (int j = i + 1; j < end; j++) out[h++] = words[j];
                out[h++] = ' ';
                end = i;
            }
        }

        return new String(out);
    }
}
