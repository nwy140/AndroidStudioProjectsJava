package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    TextView tvDidYouKnow;
    TextView tvFactDes ;
    Button btnFunFact ;
    ConstraintLayout nConstraint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~9054078114");

        loadInterstitialAd();
        getIds();
        addbtnFunFactListener();
    }

    private void getIds(){
        tvDidYouKnow = (TextView)findViewById(R.id.tvDidYouKnow);
        tvFactDes = (TextView)findViewById(R.id.tvFactDes);
        btnFunFact = (Button)findViewById(R.id.btnFunFact);

    }


private void addbtnFunFactListener(){
      //  btnFunFact = (Button)findViewById(R.id.btnFunFact);
        btnFunFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //tvFactDes = (TextView)findViewById(R.id.tvFactDes);
                Toast.makeText(getApplicationContext(), "Button Clicked " + tvFactDes.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
}







    // InterstitialAd
    private InterstitialAd mInterstitialAd;

//https://code.tutsplus.com/tutorials/how-to-monetize-your-android-apps-with-admob--cms-29255

    public void loadInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3043579075978700/1530811311");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // Toast.makeText(getApplicationContext(), "onAdLoaded()", Toast.LENGTH_SHORT).show();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                //  Toast.makeText(getApplicationContext(), "onAdFailedToLoad()", Toast.LENGTH_SHORT).show();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

}
