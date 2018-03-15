package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import com.google.android.gms.ads.MobileAds;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method
    public static int x_bladeNo = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~2137820558");

        loadInterstitialAd();
        ListenImage();
    }

    public void ListenImage(){
        final MediaPlayer x_blade= MediaPlayer.create(MainActivity.this,R.raw.x_blade);
        if(x_blade.isPlaying()){x_blade.release();}

        final TextView tvX_Blade = (TextView) findViewById(R.id.tvX_Blade);
        final ImageView ivMasterXehanort = (ImageView) findViewById(R.id.ivMasterXehanort);
        ivMasterXehanort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x_bladeNo++;
                if(x_bladeNo == 100){x_blade.setVolume(1000,1000);
                MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.okayibelieveyou);
                okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"Okay, I BELIEVE YOU",Toast.LENGTH_SHORT).show();

                }
                tvX_Blade.setText(x_bladeNo+" χ-blades");
                Toast.makeText(MainActivity.this,R.string.app_name,Toast.LENGTH_SHORT).show();

                x_blade.start();
//TODO add okay i believe you for each 100s // add HighScore!!

            }
        });
    }



// InterstitialAd
    private InterstitialAd mInterstitialAd;

//https://code.tutsplus.com/tutorials/how-to-monetize-your-android-apps-with-admob--cms-29255

    public void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
       // mInterstitialAd.setAdUnitId("ca-app-pub-3043579075978700/4699570562");
        mInterstitialAd.setAdUnitId("ca-app-pub-3043579075978700/6240717097");

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

}
