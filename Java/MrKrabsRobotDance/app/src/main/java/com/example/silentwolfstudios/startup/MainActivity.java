package com.example.silentwolfstudios.startup;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method
    private Button showGifOneButton = null;

    private Button showGifTwoButton = null;

    private LinearLayout gifLinearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~5500034383");

        loadInterstitialAd();
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource("android.resource://" + this.getPackageName() + "/raw/krabvideo");
        mp.prepare();
        mp.start();

        setTitle("dev2qa.com - Android Show Gif Use Movie Example.");

        initControls();

        final ShowGifView showGifView = new ShowGifView(getApplicationContext());
        gifLinearLayout.addView(showGifView);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        showGifView.setLayoutParams(layoutParams);


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                showGifView.setGifImageDrawableId(R.drawable.krabdancing);
                showGifView.drawGif();
                //your method
            }
        }, 0, 1000);//put here time 1000 milliseconds=1 second

//        showGifTwoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showGifView.setGifImageDrawableId(R.drawable.krabdancing);
//                showGifView.drawGif();
//            }
//        });

    }

    private void initControls()
    {
        if(showGifOneButton == null)
        {
            showGifOneButton = (Button)findViewById(R.id.showGifOne);
        }

        if(showGifTwoButton == null)
        {
            showGifTwoButton = (Button)findViewById(R.id.showGifTwo);
        }

        if(gifLinearLayout == null)
        {
            gifLinearLayout = (LinearLayout) findViewById(R.id.gifLinearLayout);
        }
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