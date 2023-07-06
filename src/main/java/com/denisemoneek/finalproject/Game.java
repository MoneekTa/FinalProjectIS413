package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

public class Game extends Application {
    private Player player;
    private Pane gamePane;
    private Bullet bullet;
    Layout layout = new Layout();
    Button pauseButton = new Button("Start Game");
    long elapsedTime;
    Timer timer = new Timer();
    boolean timerRun = false;
    private Invader invader;
    private int currentLevel = 1;

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
            invader = new Invader(gamePane);
            bullet = new Bullet(gamePane);
            Button nextLevelBtn = new Button("Next Level");
            nextLevelBtn.setOnAction(e -> goToNextLevel());
            bullet.setXPosition(player.getXposition());
            bullet.setYPosition(player.getYposition());
//            BorderPane root = new BorderPane();
//            root.setCenter(gamePane);
//            root.setBottom(nextLevelBtn);
//            BorderPane.setAlignment(nextLevelBtn, Pos.CENTER);
            gamePane.getChildren().add(nextLevelBtn);
            nextLevelBtn.setLayoutX(725);
            // If the timerRun is false, meaning the timer is not running
            if (!timerRun) {
                timer.start();
                pauseButton.setText("Game is Running");
            } else {
                elapsedTime += timer.stop();
                pauseButton.setText("Game is Paused " +
                        "\n" + "Time: " + elapsedTime / 1000 + " seconds");
            }
            // Resets timerRun
            timerRun = !timerRun;
        });
        // btn layout

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
                case M:
                    bullet.moveBullet();
                    break;
                default:
                    // Ignore other keys
                    break;
            }
            if(bullet.getYPosition() == 0) {
                bullet.setXPosition(player.getXposition());
            }
        }
    }
    private void goToNextLevel() {
        currentLevel++;
        invader.setLevel(currentLevel);
        invader.setSpeed(currentLevel); // Update speed based on level
    }

    public static void main(String[] args) {
        launch();
    }
}