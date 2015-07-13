package com.paipeng.morse.sos.Utils;

/**
 * Created by paipeng on 13.07.15.
 */
public class MorseCodeDash extends MorseCode {
    @Override
    public int getDuration() {
        return duration*3;
    }
}
