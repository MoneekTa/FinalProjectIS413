package com.denisemoneek.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
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
            invaderHealth = invader.getHealth();

            bullet = new Bullet(gamePane);
            Button nextLevelBtn = new Button("Next Level");
            nextLevelBtn.setOnAction(e -> goToNextLevel());
            bullet.setXposition(player.getXposition());
            bullet.setYPosition(player.getYposition());

            gamePane.getChildren().add(nextLevelBtn);
            nextLevelBtn.setLayoutX(725);
            setUpText();
            // If the timerRun is false, meaning the timer is not running
            if (!timerRun) {
                timer.start();
                pauseButton.setText("Game is Running");
                Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
                    bulletYposition = bullet.recordingY();
                    bulletXposition = bullet.recordingX();
                    invaderStartPosition = invader.getXposition();
                    //System.out.println("invaderStartPosition: " + invaderStartPosition);
                    invaderEndPosition = invader.getwidth() + invader.getXposition();
                    //System.out.println("invaderEndPosition: " + invaderEndPosition);
                    //System.out.println("Bullet X Position: " + bulletXposition);

                    //System.out.println("invaderYposition: " + (invader.getYposition() + invader.getheight()));
                    if(bulletXposition > invaderStartPosition &
                            bulletXposition <= invaderEndPosition &
                            bulletYposition <= (invader.getYposition()- invader.getheight())){
                        System.out.println("target hit");
                        invaderHealth = invaderHealth - 1;
                        HealthDisplay.setText("Health: "+ invaderHealth);
                        if(invaderHealth == 0){
                            goToNextLevel();
                        }

                    }
                    if(bulletYposition == 0){
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
        HealthDisplay.setText("Health: "+ invaderHealth);
        LevelDisplay.setText("Level: " + currentLevel);
    }

    public void setUpText(){

        HealthDisplay = new Text("Health: " + invaderHealth);
        LevelDisplay = new Text("Level: " + currentLevel);
        gamePane.getChildren().addAll(HealthDisplay, LevelDisplay);
        HealthDisplay.setLayoutX(0);
        HealthDisplay.setLayoutY(gamePane.getHeight()-10);
        HealthDisplay.setFont(Font.font(25));
        LevelDisplay.setLayoutY(gamePane.getHeight()-10);
        LevelDisplay.setLayoutX(gamePane.getWidth()-100);
        LevelDisplay.setFont(Font.font(25));

    }
    public static void main(String[] args) {
        launch();
    }
}