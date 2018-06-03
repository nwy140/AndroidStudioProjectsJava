package com.example.silentwolfstudios.startup;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;

import java.util.Random;


public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method

    TextView tvDidYouKnow;
    TextView tvFactDes ;
    Button btnFunFact ;
    ConstraintLayout mConstraint;

    Random randomGenerator;
    int randomNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~9054078114");

        loadInterstitialAd();
        getIdsandFacts();
        addbtnFunFactListener();
    }

    private void getIdsandFacts(){
        tvDidYouKnow = (TextView)findViewById(R.id.tvDidYouKnow);
        tvFactDes = (TextView)findViewById(R.id.tvFactDes);
        btnFunFact = (Button)findViewById(R.id.btnFunFact);
        mConstraint = (ConstraintLayout)findViewById(R.id.mConstraint);


    }

private void addbtnFunFactListener(){
      //  btnFunFact = (Button)findViewById(R.id.btnFunFact);

    randomGenerator = new Random();
    final String[] facts= {

            "Ostrict can fly",
            "Ostrict cannot jump",
            "Ostrict cannot fly",
            "Ostrict eats humans",
            "Ostrict can live for 1000 years"
    };
    final String[] colors= {
            "#FF6633", "#FFB399", "#FF33FF", "#FFFF99", "#00B3E6",
            "#E6B333", "#3366E6", "#999966", "#99FF99", "#B34D4D",
            "#80B300", "#809900", "#E6B3B3", "#6680B3", "#66991A",
            "#FF99E6", "#CCFF1A", "#FF1A66", "#E6331A", "#33FFCC",
            "#66994D", "#B366CC", "#4D8000", "#B33300", "#CC80CC",
            "#66664D", "#991AFF", "#E666FF", "#4DB3FF", "#1AB399",
            "#E666B3", "#33991A", "#CC9999", "#B3B31A", "#00E680",
            "#4D8066", "#809980", "#E6FF80", "#1AFF33", "#999933",
            "#FF3380", "#CCCC00", "#66E64D", "#4D80CC", "#9900B3",
            "#E64D66", "#4DB380", "#FF4D4D", "#99E6E6", "#6666FF"
    };
        btnFunFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tvFactDes = (TextView)findViewById(R.id.tvFactDes);
            //    Toast.makeText(getApplicationContext(), "Button Clicked " + tvFactDes.getText().toString(), Toast.LENGTH_SHORT).show();
                randomNumbers = randomGenerator.nextInt(facts.length);
               tvFactDes.setText(facts[randomNumbers]);
               randomNumbers = randomGenerator.nextInt(colors.length);
                mConstraint.setBackgroundColor(Color.parseColor(colors[randomNumbers]));
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
