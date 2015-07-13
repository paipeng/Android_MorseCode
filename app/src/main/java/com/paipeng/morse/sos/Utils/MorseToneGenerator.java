package com.paipeng.morse.sos.Utils;

/**
 * Created by paipeng on 13.07.15.
 */
public class MorseToneGenerator {
    private final int duration = 50; // milliseconds
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate / 1000;
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 800; // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];

    public MorseToneGenerator() {

    }

    private byte[] generateDot() {
        byte generatedSnd[] = new byte[2 * numSamples];
        byte snd[] = generateTone(generatedSnd, numSamples);
        return snd;
    }

    private byte[] generateDash() {
        byte generatedSnd[] = new byte[2 * numSamples];
        byte snd[] = generateTone(generatedSnd, numSamples);
        return snd;
    }

    private byte[] generateShortSpace() {
        byte generatedSnd[] = new byte[2 * numSamples];
        byte snd[] = generateTone(generatedSnd, numSamples);
        return snd;
    }

    private byte[] generateTone(byte generatedSnd[], int numSamples) {
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
            //sample[i] = 0.3;
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

            //Log.d(TAG, dVal + " -> " + generatedSnd[idx - 2] + " " + generatedSnd[idx - 1]);
        }

        return generatedSnd;
    }
}
