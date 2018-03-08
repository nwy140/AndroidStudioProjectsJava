package com.example.silentwolfstudios.startup;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity { //ctrl + p is used to show parameters of a method

    public static final String DEBUGTAG ="NWY";  // final means can only be assigned once
    public static final String TEXTFILE = "notesquirrel.txt"; //Finame

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        addSaveButtonListener();

        loadSavedFile();
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
        final Button saveBtn = (Button)findViewById(R.id.save); //Access Item With ID Save from Activity.Main
                            // Cast to Button
        saveBtn.setOnClickListener( new View.OnClickListener(){ //creating OnClickListener // new is an anymous class
            @Override
            public void onClick(View v) { // Must override View //Whatever's written in this method will be called
               EditText etDes = (EditText) findViewById(R.id.etDes); //Cast to EditText

                String etDesText = etDes.getText().toString();

                try {
                   FileOutputStream fos = openFileOutput(TEXTFILE, Context.MODE_PRIVATE) ; //save text to buffer file as Private , cant be accessed by user
                    fos.write(etDesText.getBytes()); //pass number of bytes of input from etDesText
                    fos.close();
                } catch (Exception ex){
                    ex.printStackTrace(); //tells you where exception occured
                    Log.d(DEBUGTAG, "Unable to save file");
                }

                Log.d(DEBUGTAG, "SaveButtonClicked: "+ etDesText); //Debug Logs to LogCat only// Must enable Debuggable on LogCat to see the Log

            }
        }) ;


    }
}
