package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17109182 on 13/3/2018.
 */
// In Java, Interface is like a class that can be inherited by multiple class
public class PointCollector implements View.OnTouchListener { // class implements OnTouchListener means inheriting an abstract method or interface // OnTouchListener is the abstract method  // like extending a class , creating a subclass

    private PointCollectorListener listener; // use alt+insert to shorcut create getters and setters

    private List<Point> points = new ArrayList<Point>(); //creating an array list //Points is a type that holds 2 INT coordinates

    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getX()); //round to wholenumber
        int y = Math.round(event.getY());

        String message = String.format("Coordinates: (%i , %i)", x, y); //%.2f means 2 decimal place values//%.2f was changed to d
        Log.d(MainActivity.DEBUGTAG, message);

        points.add(new Point(x, y));

        if (points.size() == 4) { // if there are 4 (x,y) coordinates held in ArrayList points,
            if (listener != null) {
                listener.pointsCollected(points); //pass points collected tolistener interface
                points.clear();
            }


        }

        return false;
    }
    public void setListener(PointCollectorListener listener) {
        this.listener = listener; // set listener to any listener passed in
    }

}
// you know , the way java is a very unatural programming language