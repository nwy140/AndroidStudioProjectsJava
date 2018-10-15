package com.example.silentwolfstudios.startup;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.silentwolfstudios.startup.MainActivity;
import com.example.silentwolfstudios.startup.R;

/**
 * @author anuragdhunna
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_background);

        MediaPlayer xblade = MediaPlayer.create(SplashScreenActivity.this,R.raw.x_blade);
        xblade.start();


        //spash screen workaround admob ads policy
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}