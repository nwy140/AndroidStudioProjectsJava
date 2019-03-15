package com.example.silentwolfstudios.startup;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
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

        // Advertisements code
        /// Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 // Go to https://apps.admob.com/v2 for ads id
        MobileAds.initialize(this, getString(R.string.app_ads_sample));
        loadInterstitialAd();   //LoadBannerAd();

        // Your code
        initControls();

        LoadAndPlayVideo();


    }
    @Override
    public void onStop(){
        //Save your data here
//        startActivity(new Intent(MainActivity.this, Splash.class)); //switch back to splash activity
//        finish(); //Kill Contect Activity.
    }



    private void initControls()
    {
//        btn_Play =  (Button) findViewById(R.id.btn_play);
        videoV_Vid1 = (VideoView) findViewById(R.id.videoV_Vid1);
    }

    public void LoadAndPlayVideo(){
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.krabbord);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(300f,300f);
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
       // Sample ad unit banner id : ca-app-pub-3940256099942544/1033173712
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_sample));

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

    // Banner ads
    private AdView mAdView;

    public void LoadBannerAd(){
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(getString(R.string.banner_ad_sample));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


}