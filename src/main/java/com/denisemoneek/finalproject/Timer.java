package com.denisemoneek.finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
    long starttime;
    long endtime;
    long elaspedTime;

    // Start the timer
    public void start() {
        starttime = System.currentTimeMillis(); // Get the current time in milliseconds

    }

    // Stop the timer and calculate the elapsed time
    public long stop() {
        endtime = System.currentTimeMillis(); // Get the current time in milliseconds
        elaspedTime = endtime - starttime; // Calculate the elapsed time
        return elaspedTime; // Return the elapsed time
    }
}