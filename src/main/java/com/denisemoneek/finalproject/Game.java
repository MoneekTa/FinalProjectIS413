package com.denisemoneek.finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    long elaspedtime;
    Timer timer = new Timer();
    Boolean timerRun = false;
    @Override
    public void start(Stage primaryStage) {

        Button PauseButton = new Button("Start Game");
        Pane gamePane = new Pane(PauseButton);
        timer.start();
        PauseButton.setOnAction(event -> {
            //uses this print code to debug
            System.out.println("Game Started");
            //if the timerRun is false, meaning the timer is not running
            if(!timerRun){
                timer.start();
                PauseButton.setText("Game is Running");
            } else {
                elaspedtime = elaspedtime + timer.stop();
                PauseButton.setText("Time: " + elaspedtime + " seconds");
            }
            //resets timerRun
            timerRun = !timerRun;
        });

        Scene scene = new Scene(gamePane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





