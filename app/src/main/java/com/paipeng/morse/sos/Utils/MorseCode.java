package com.paipeng.morse.sos.Utils;

/**
 * Created by paipeng on 13.07.15.
 */
public abstract class MorseCode {
    public final int duration = 50; // milliseconds
    protected final int sampleRate = 8000;
    private final double freqOfTone = 800; // hz


    public MorseCode() {

    }

    protected byte[] generateTone(int numSamples) {
        byte generatedSnd[] = new byte[numSamples];
        for (int i = 0; i < numSamples; ++i) {
            double sample = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));

            short val = (short) (sample * 32767);
            generatedSnd[i] = (byte) (val & 0x00ff);
        }

        return generatedSnd;
    }

    protected int getSampleNumber() {
        return getDuration() * sampleRate / 1000;
    }

    public byte[] generate() {
        byte snd[] = generateTone(getSampleNumber());
        return snd;
    }

    public abstract int getDuration();
}
