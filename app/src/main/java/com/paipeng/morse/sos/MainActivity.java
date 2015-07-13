package com.paipeng.morse.sos;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.paipeng.morse.sos.Utils.MorseCodeEncode;


public class MainActivity extends ActionBarActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private final int duration = 50; // milliseconds
    private final int sampleRate = 8000;
    private int numSamples = duration * sampleRate / 1000;
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 1000; // hz

    private byte generatedSnd[] = new byte[2 * numSamples];

    Handler handler = new Handler();
    Thread thread;


    private ToneGenerator toneGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // Use a new tread as this can take a while
        thread = new Thread(new Runnable() {
            public void run() {
                genTone();
                handler.post(new Runnable() {

                    public void run() {
                        playSound();
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (thread != null) {
            try {
                thread.join();

            } catch (InterruptedException e) {

            }

            thread = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void genTone(){
        /*
        // fill out the array
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
        */

        generatedSnd = MorseCodeEncode.encode();
        numSamples = generatedSnd.length*2;

    }

    void playSound(){
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_8BIT, numSamples,
                AudioTrack.MODE_STATIC);

        //do {
            audioTrack.write(generatedSnd, 0, numSamples);
            //audioTrack.setLoopPoints(0, numSamples, 10);
            audioTrack.play();
            //audioTrack.flush();

            /*
            try {
                Thread.sleep(1500);

            } catch (InterruptedException e) {

            }
            */
        //} while (true);


        //

        //toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    }
}
