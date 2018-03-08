package com.example.silentwolfstudios.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //setContentView method sets Activity currently being displayed

        addSaveButtonListener();
    }

    private  void addSaveButtonListener(){
        final Button saveBtn = (Button)findViewById(R.id.save); //Access Item With ID Save from Activity.Main
                            // Cast to Button
        saveBtn.setOnClickListener( new View.OnClickListener(){ //creating OnClickListener // new is an anymous class
            @Override
            public void onClick(View v) { // Must override View //Whatever's written in this method will be called
                saveBtn.setText("ok");
            }
        }) ;


    }
}
