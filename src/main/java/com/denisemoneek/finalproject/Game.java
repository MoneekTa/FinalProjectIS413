package com.denisemoneek.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Game extends Application {
    private Player player;
    private Pane gamePane;
    private Bullet bullet;
    Layout layout = new Layout();
    Button pauseButton = new Button("Start Game");
    long elapsedTime;
    long bulletYposition;
    long bulletXposition;
    long invaderStartPosition;
    long invaderEndPosition;
    int invaderHealth;
    Timer timer = new Timer();
    boolean timerRun = false;
    private Invader invader;
    private int currentLevel = 1;
    Text HealthDisplay;
    Text LevelDisplay;

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
            invader.setLevel(currentLevel);
            invader.setSpeed(currentLevel);
            System.out.println(invader.getSpeed());
            invaderHealth = invader.getHealth();
            bullet = new Bullet(gamePane);
            Button nextLevelBtn = new Button("Next Level");
            nextLevelBtn.setOnAction(e -> goToNextLevel());
            bullet.setXposition(player.getXposition());
            bullet.setYPosition(player.getYposition());

            gamePane.getChildren().add(nextLevelBtn);
            nextLevelBtn.setLayoutX(725);
            // If the timerRun is false, meaning the timer is not running
            if (!timerRun) {
                timer.start();
                pauseButton.setText("Game is Running");
                Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(1), event2 -> {
                    bulletYposition = bullet.recordingY();
                    bulletXposition = bullet.recordingX();
                    invaderStartPosition = invader.getXposition();
                    //System.out.println("invaderStartPosition: " + invaderStartPosition);
                    invaderEndPosition = invader.getwidth() + invader.getXposition();
                    //System.out.println("invaderEndPosition: " + invaderEndPosition);
                    //System.out.println("Bullet X Position: " + bulletXposition);
//                    HealthDisplay.setText("Health: " + invaderHealth);
//                    gamePane.getChildren().add(HealthDisplay);
//                    HealthDisplay.setLayoutY(gamePane.getLayoutY());
//                    HealthDisplay.setLayoutX(0);
//
                    //System.out.println("HealthDisplay: "+ HealthDisplay);
                    LevelDisplay.setText("Level: " + currentLevel);
                    gamePane.getChildren().add(LevelDisplay);
                    LevelDisplay.setLayoutY(gamePane.getLayoutY());
                    LevelDisplay.setLayoutX(gamePane.getLayoutX());
                    System.out.println("Level: "+currentLevel);
                    if(bulletXposition > invaderStartPosition &
                            bulletXposition < invaderEndPosition &
                            bulletYposition == invader.getYposition() ){
                        System.out.println("target hit");

                    }
                    if(bulletYposition == invader.getYposition()){
                        bullet.setXposition(player.getXposition());
                        bullet.setYPosition(player.getYposition());
                    }

                }));
                recordingTimeline.setCycleCount(Timeline.INDEFINITE);
                recordingTimeline.play();
            } else {
                elapsedTime += timer.stop();
                pauseButton.setText("Game is Paused " +
                        "\n" + "Time: " + elapsedTime / 1000 + " seconds");
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
                    if(bulletYposition == 502) {
                        bullet.setXposition(player.getXposition());
                    }
                    break;
                case D:
                    player.moveRight(800);
                    if(bulletYposition == 502) {
                        bullet.setXposition(player.getXposition());
                    }
                    break;
                case M:
                    bullet.moveBullet();
                    break;
                default:
                    // Ignore other keys
                    break;

            }
        }
    }
    private void goToNextLevel() {
        currentLevel++;
        invader.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel); // Update speed based on level
        invaderHealth = invader.getHealth();

    }

    public static void main(String[] args) {
        launch();
    }
}