package com.example.nz160.drawingapp;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by nz160 on 30-01-2017.
 */
public class PathWithPaint {
    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    private Paint mPaint;

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}