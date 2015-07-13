package com.paipeng.morse.sos.Utils;

/**
 * Created by paipeng on 13.07.15.
 */
public class MorseCodePause extends MorseCode {
    public enum Mode {
        SHORT,
        LONG,
        WORD
    }

    private Mode mode;
    private int factor;

    public MorseCodePause(Mode mode) {
        this.mode = mode;

        if (mode == Mode.SHORT || mode == Mode.LONG) {
            factor = 3;
        } else if (mode == Mode.WORD) {
            factor = 7;
        } else {
            factor = 7;
        }
    }
    @Override
    public int getDuration() {
        return duration * factor;
    }

}
