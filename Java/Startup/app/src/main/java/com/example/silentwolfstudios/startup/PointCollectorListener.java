package com.example.silentwolfstudios.startup;

import android.graphics.Point;

import java.util.List;

/**
 * Created by 17109182 on 13/3/2018.
 */

public interface PointCollectorListener { // the reason why we use interface is because multple inheritance is not supported in java , so interface is used instead. Unlike C++ , C++ rocks
    public void pointsCollected(List<Point> points);
}
