package com.example.silentwolfstudios.startup;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;



import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdListener;

import java.lang.reflect.Field;
import java.util.Random;

public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method
    public static int x_bladeNo = 0 ;
    public static int ansemNum = 1;
    public static ImageView ivAnsem;
    public static int resID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~5500034383");

        loadInterstitialAd();
        ListenImage();
        ivAnsem = (ImageView) findViewById(R.id.ivAnsem);
        ivAnsem.setImageResource(R.drawable.ansem1);

    }

    public void ListenImage(){
        int random = new Random().nextInt((7-1) + 1) + 1;
        ansemNum = random;
        resID = getResId("dark"+ansemNum, R.raw.class); // or other resource class
        Toast.makeText(MainActivity.this,"There are 7 seven darkness voice in this app",Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"Each time you reopen this app, a new darkness voice will be used\nDiscover them all!!!",Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"Darkness Voice " + ansemNum +" Activated",Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this,"Reach 500 Darkness to unlock everlasting darkness mode!!!",Toast.LENGTH_SHORT).show();

        if(ansemNum==3){       Toast.makeText(MainActivity.this,"All Darkness voice Activated",Toast.LENGTH_SHORT).show();
        }
        if(x_bladeNo == 8){
            Toast.makeText(MainActivity.this,"There are 7 seven darkness voice in this app",Toast.LENGTH_SHORT).show();
        }
        else if(x_bladeNo == 15){
            Toast.makeText(MainActivity.this,"Each time you open this app, a new darkness voice will be used",Toast.LENGTH_SHORT).show();
        }


        final MediaPlayer x_blade= MediaPlayer.create(MainActivity.this,resID);
        if(x_blade.isPlaying()){x_blade.release();}
        if(x_blade.isPlaying()){x_blade.release();}

        final TextView tvX_Blade = (TextView) findViewById(R.id.tvX_Blade);
        final ImageView ivMasterXehanort = (ImageView) findViewById(R.id.ivAnsem);
        ivMasterXehanort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x_bladeNo++;
                resID = getResId("ansem2", R.drawable.class); // or other resource class
                ivAnsem.setImageResource(resID);
                if(x_bladeNo == 100 ){x_blade.setVolume(1000,1000);
                MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.everdark);
                okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"L E A D M E I N T O E V E R L A S T I N G D A R K N E S S",Toast.LENGTH_SHORT).show();

                }

               else if(x_bladeNo == 200){x_blade.setVolume(1000,1000);
                    MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.light);
                    okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"Light",Toast.LENGTH_SHORT).show();

                }

                else if(x_bladeNo == 300){x_blade.setVolume(1000,1000);
                    MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.sponge1);
                    okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"where is my heart???",Toast.LENGTH_SHORT).show();

                }

                else if(x_bladeNo == 400){x_blade.setVolume(1000,1000);
                    MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.okayibelieveyou);
                    okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"OKAY I DONT BELIEVE YOU",Toast.LENGTH_SHORT).show();

                }


                else if(x_bladeNo == 500){x_blade.setVolume(1000,1000);
                    MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.x_blade);
                    okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"MEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEMEMEMEMEMMEMEMEMEME",Toast.LENGTH_SHORT).show();

                }

                else if(x_bladeNo/500 >= 1){x_blade.setVolume(1000,1000);
                    MediaPlayer okayibelieveyou = MediaPlayer.create(MainActivity.this,R.raw.everdark);
                    okayibelieveyou.start();
                    Toast.makeText(MainActivity.this,"You are the 13th seeker of darkness",Toast.LENGTH_SHORT).show();

                }

                tvX_Blade.setText(x_bladeNo+ " "+"Darkness");
                Toast.makeText(MainActivity.this,"Darkness",Toast.LENGTH_SHORT).show();

                x_blade.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ivAnsem.setImageResource(R.drawable.ansem1);

                    }
                });


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
