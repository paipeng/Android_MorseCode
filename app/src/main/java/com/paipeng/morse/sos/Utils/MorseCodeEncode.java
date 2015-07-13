package com.paipeng.morse.sos.Utils;

/**
 * Created by paipeng on 13.07.15.
 */
public class MorseCodeEncode {
    public static byte[] encode() {
        MorseCodeDot morseCodeDot = new MorseCodeDot();
        byte[] dot = morseCodeDot.generate();

        MorseCodePause morseCodePause = new MorseCodePause(MorseCodePause.Mode.SHORT);
        byte[] shortPause = morseCodePause.generate();

        byte[] temp = concatByteArray(dot, shortPause);


        temp = concatByteArray(temp, dot);
        temp = concatByteArray(temp, shortPause);
        temp = concatByteArray(temp, dot);

        return temp;
    }

    public static byte[] concatByteArray(byte[] one, byte[] two) {
        byte[] combined = new byte[one.length + two.length];

        for (int i = 0; i < combined.length; ++i)
        {
            combined[i] = i < one.length ? one[i] : two[i - one.length];
        }

        return combined;
    }
}
