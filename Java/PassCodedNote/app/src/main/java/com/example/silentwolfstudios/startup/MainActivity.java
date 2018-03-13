package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    public static final String DEBUGTAG ="NWY";  // final means can only be assigned once
    public static final String TEXTFILE = "notesquirrel.txt"; //Filename
    public static final String FILESAVED = "FileSaved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        MobileAds.initialize(this, "ca-app-pub-3043579075978700~9054078114");

        loadInterstitialAd();

        addSaveButtonListener();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        boolean fileSaved =  prefs.getBoolean(FILESAVED, false); //indicates file hasn't been saved


        if (fileSaved) {
            loadSavedFile();

        }
    }

    private void loadSavedFile(){

        try {
            FileInputStream fis = openFileInput(TEXTFILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis) )); //wrapping lots of input streams

            EditText etDes = (EditText) findViewById(R.id.etDes); //Cast to EditText

            String line;
            while ((line=reader.readLine()) != null){
                etDes.append(line); //reads txt file and appends all lines to etDes
                etDes.append("\n"); //next line
            }
            fis.close();
            }catch (Exception e){
            e.printStackTrace();
            Log.d(DEBUGTAG, "Unable to read file");

        }
    }

    private  void addSaveButtonListener(){
        final Button saveBtn = (Button)findViewById(R.id.btnSave); //Access Item With ID Save from Activity.Main
                            // Cast to Button
        saveBtn.setOnClickListener( new View.OnClickListener(){ //creating OnClickListener // new is an anymous class
            @Override
            public void onClick(View v) { // Must override View //Whatever's written in this method will be called
               EditText etDes = (EditText) findViewById(R.id.etDes); //Cast to EditText

                String etDesText = etDes.getText().toString();
//

                try {
                   FileOutputStream fos = openFileOutput(TEXTFILE, Context.MODE_PRIVATE) ; //save text to buffer file as Private , cant be accessed by user
                    fos.write(etDesText.getBytes()); //pass number of bytes of input from etDesText
                    fos.close();

                    SharedPreferences prefs =  getPreferences(MODE_PRIVATE); //only this app can access the pref files //pref are like savedata of an app
                    SharedPreferences.Editor editor = prefs.edit();  //edit preference
                    editor.putBoolean(FILESAVED,true); //indicates user have saved file
                    editor.commit(); //saved prefs

                    Toast.makeText(MainActivity.this, R.string.toast_can_save ,Toast.LENGTH_LONG).show(); //add custom toast log text

                } catch (Exception ex){
                    ex.printStackTrace(); //tells you where exception occured
                    Toast.makeText(MainActivity.this, R.string.toast_cant_save  ,Toast.LENGTH_LONG).show(); //add custom toast log text

                    //  Log.d(DEBUGTAG, "Unable to save file");
                }

              //  Log.d(DEBUGTAG, "SaveButtonClicked: "+ etDesText); //Debug Logs to LogCat only// Must enable Debuggable on LogCat to see the Log
            }
        }) ;


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
