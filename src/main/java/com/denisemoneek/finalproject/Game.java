package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Game extends Application {
    private Player player;
    private Pane gamePane;
    private Bullet bullet;
    Layout layout = new Layout();
    Button PauseButton = new Button("Start Game");
    long elaspedtime;
    Timer timer = new Timer();
    Boolean timerRun = false;
    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane(PauseButton);

        Scene scene = new Scene(gamePane, 800, 600);

        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        timer.start();
        PauseButton.setOnAction(event -> {
            //uses this print code to debug
            System.out.println("Game Started");
            scene.setFill(layout.Color());
            player = new Player(gamePane);
            bullet = new Bullet(gamePane);
            bullet.setXposition(player.getXposition());
            bullet.setYposition(player.getYposition());
            //if the timerRun is false, meaning the timer is not running
            if(!timerRun){
                timer.start();
                PauseButton.setText("Game is Running");
            } else {
                elaspedtime = elaspedtime + timer.stop();
                PauseButton.setText("Game is Paused " +
                        "\n" + "Time: " + elaspedtime/1000 + " seconds");
            }
            //resets timerRun
            timerRun = !timerRun;
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    private void handleKeyPress(KeyCode key) {
        switch (key) {
            case A:
                player.moveLeft();

                break;
            case D:
                player.moveRight(800);
                break;
            default:
                // Ignore other keys
                break;

        }
        bullet.setXposition(player.getXposition());
    }

    public static void main(String[] args) {
        launch();
    }
}
