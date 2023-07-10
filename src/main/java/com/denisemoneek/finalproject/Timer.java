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
        System.out.println(starttime / 1000); // Print the start time in seconds (optional)

        // Create a timeline with a duration of 1 second
        Timeline timeline =
                new Timeline(new KeyFrame(Duration.millis(1000)));
        timeline.setCycleCount(Animation.INDEFINITE); // Set the cycle count to indefinite
        timeline.play(); // Start the timeline
    }

    // Stop the timer and calculate the elapsed time
    public long stop() {
        endtime = System.currentTimeMillis(); // Get the current time in milliseconds
        System.out.println(endtime / 1000); // Print the end time in seconds (optional)
        elaspedTime = endtime - starttime; // Calculate the elapsed time
        return elaspedTime; // Return the elapsed time
    }
}