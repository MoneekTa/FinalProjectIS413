package com.denisemoneek.finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
    long starttime;
    long endtime;
    long time;
    long elaspedTime;

    public void start(int time){
        starttime = time + System.currentTimeMillis();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public long stop(){
        endtime = System.currentTimeMillis();
        elaspedTime = endtime - starttime;
        return elaspedTime/1000;
    }
}
