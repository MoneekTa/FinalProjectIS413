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
    Button pauseButton = new Button("Start Game");
    long elapsedTime;
    Timer timer = new Timer();
    boolean timerRun = false;

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane(pauseButton);

        Scene scene = new Scene(gamePane, 800, 600);

        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        timer.start();
        pauseButton.setOnAction(event -> {
            // Use this print code to debug
            System.out.println("Game Started");
            scene.setFill(layout.Color());
            player = new Player(gamePane);
            bullet = new Bullet(gamePane);
            bullet.setXPosition(player.getXposition());
            bullet.setYPosition(player.getYposition());
            // If the timerRun is false, meaning the timer is not running
            if (!timerRun) {
                timer.start();
                pauseButton.setText("Game is Running");

            } else {
                elapsedTime += timer.stop();
                pauseButton.setText("Game is Paused " +
                        "\n" + "Time: " + elapsedTime / 1000 + " seconds");
                bullet.moveBullet(true);
            }
            // Resets timerRun
            timerRun = !timerRun;
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

// using A and D keys on the keyboard to move player
    private void handleKeyPress(KeyCode key) {
        if (timerRun) {
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
            bullet.setXPosition(player.getXposition());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}