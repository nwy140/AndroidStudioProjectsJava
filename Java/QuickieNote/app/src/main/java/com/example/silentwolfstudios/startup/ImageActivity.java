package com.example.silentwolfstudios.startup;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        addTouchListener();
        showPrompt();
    }

    private  void showPrompt(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //dialog is a prompt ,
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });    //set positivebutton for dialog

        //setting what shows up in the prompt
        builder.setTitle("Create Your Passpoint Sequence");
        builder.setMessage("Touch four points on the image to set the passpoint sequence. You must click the same points in future to gain acces to your notes.");

        AlertDialog dlg = builder.create(); //returns alert dialog
        dlg.show();

    }

    private void addTouchListener(){
        ImageView ivTouchCat = (ImageView) findViewById(R.id.ivTouchCat);

        ivTouchCat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float x = event.getX();
                float y = event.getY();

                String message = String.format("Coordinates: (%.2f, %.2f)", x,y);
                Toast.makeText(ImageActivity.this, message,Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }

}
