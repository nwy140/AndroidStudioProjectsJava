package com.example.silentwolfstudios.startup;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.MobileAds;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method

//    Button btn_Play;
    VideoView videoV_Vid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~5500034383"); //always same no matter what app

        loadInterstitialAd();

        initControls();

        LoadAndPlayVideo();


    }
    @Override
    public void onPause() {
        super.onPause();
        //Save your data here

        finish(); //Kill Contect Activity.
    }

    private void initControls()
    {
//        btn_Play =  (Button) findViewById(R.id.btn_play);
        videoV_Vid1 = (VideoView) findViewById(R.id.videoV_Vid1);
    }

    public void LoadAndPlayVideo(){
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.krabbord);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        videoplay(videoV_Vid1);


        videoV_Vid1.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MediaPlayer mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_RING);

                try {
                    mPlayer.setDataSource(getApplicationContext(),
                            Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.bibububabububi));
                    mPlayer.prepare();
                } catch (IOException e) {
                    Log.e("VidLogTag", "Could not setup media player for ringtone");
                    mPlayer = null;
                    return false;
                }
                mPlayer.setLooping(false);
                mPlayer.start();

                return false;
            }
        });


    }

    public void videoplay(VideoView v){
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.krabvideo;
        Uri url = Uri.parse(videopath);
        videoV_Vid1.setVideoPath(videopath);
        videoV_Vid1.start();
        // loop
        videoV_Vid1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0f, 0f);

            }
        });
    }


// InterstitialAd
    private InterstitialAd mInterstitialAd;

//https://code.tutsplus.com/tutorials/how-to-monetize-your-android-apps-with-admob--cms-29255

    public void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
       // mInterstitialAd.setAdUnitId("ca-app-pub-3043579075978700/4699570562");
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(getApplicationContext(), "onAdLoaded()", Toast.LENGTH_SHORT).show();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Toast.makeText(getApplicationContext(), "onAdFailedToLoad()", Toast.LENGTH_SHORT).show();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }


    public static int getResId(String resName, Class<?> c) {
        //get ID by string
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}