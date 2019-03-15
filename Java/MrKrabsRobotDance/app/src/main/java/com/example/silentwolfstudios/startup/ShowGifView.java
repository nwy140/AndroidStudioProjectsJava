package com.example.silentwolfstudios.startup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

import java.io.InputStream;

/**
 * Created by zhaosong on 2018/6/18.
 */

public class ShowGifView extends View {

    private InputStream inputStream;

    private Movie movie;

    private int gifImageDrawableId;

    private Context ctx = null;

    private long gifStart = 0;

    public ShowGifView(Context context) {
        super(context);

        // Make the custom view focus.
        setFocusable(true);

        ctx = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long now = System.currentTimeMillis();

        if (gifStart == 0) {
            gifStart = now;
        }

        if (movie != null) {
            // Get gif movie duration time.
            int duration = movie.duration();

            if (duration == 0) {
                duration = 1000;
            }

            // Get played frame percentage.
            int relTime = (int)((now - gifStart) % duration);

            // Set current gif frame time.
            movie.setTime(relTime);

            // Get custom view width and height.
            int width = this.getWidth();
            int height = this.getHeight();

            // Get gif image width and height.
            int movieWidth = movie.width();
            int movieHeight = movie.height();

            // Scale canvas size to fit the custom view.
            canvas.scale(width / movieWidth, height / movieHeight);

            // Draw the gif image frame to custom view canvas.
            movie.draw(canvas, 1, 1);

            // This method will invoke onDraw method.
            invalidate();
        }
    }

    public int getGifImageDrawableId() {
        return gifImageDrawableId;
    }

    public void setGifImageDrawableId(int gifImageDrawableId) {
        this.gifImageDrawableId = gifImageDrawableId;
    }

    // Call this method to read the drawable gif image to create movie object.
    public void drawGif()
    {
        Resources resources = ctx.getResources();

        inputStream = resources.openRawResource(gifImageDrawableId);

        movie = Movie.decodeStream(inputStream);

        // Invalidate the view and invoke onDraw method.
        invalidate();
    }
}