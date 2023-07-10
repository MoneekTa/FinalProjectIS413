package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import javafx.util.Duration;

public class Game extends Application {
    private Player player;
    private Pane gamePane;
    private Bullet bullet;
    private Layout layout = new Layout();
    private Button pauseButton = new Button("Start Game");
    private long elapsedTime;
    private long bulletY;
    private long bulletX;
    private long invaderStartPosition;
    private long invaderEndPosition;
    private int invaderHealth;
    private Timer timer = new Timer();
    private boolean timerRun = false;
    private Invader invader;
    private int currentLevel = 1;
    private Text healthDisplay;
    private Text levelDisplay;
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
    // setting up the layout
        gamePane = new Pane(pauseButton);

        Scene scene = new Scene(gamePane, 800, 600);
        BackgroundFill backgroundFill =
                new BackgroundFill(layout.Color(), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        timer.start();
        pauseButton.setOnAction(event -> {
            if (gameOver) {
                restartGame();
            } else {
                startGame();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    // start game method
    private void startGame() {
        // Use this print code to debug
        System.out.println("Game Started");

        gamePane.getChildren().clear(); // Clear the game pane
        gamePane.getChildren().add(pauseButton); // Add the pause button back

        // Create player, invader, and bullet
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
            playGame();
        } else {
            elapsedTime += timer.stop();
            pauseButton.setText("Game is Paused " +
                    "\n" + "Time: " + elapsedTime / 1000 + " seconds");
        }
        // Resets timerRun
        timerRun = !timerRun;

        // start recording and playing the timeline animation
        timerRun = !timerRun;
        pauseButton.setText("Game is Running");

        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            bulletY = bullet.recordY();
            bulletX = bullet.recordX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();

            // check when bullet hits invader
            if (bulletX > invaderStartPosition && bulletX < invaderEndPosition) {
                if (bulletY == invader.getYposition()) {
                    //  displays in the terminal if the bullet hits the invader
                    System.out.println("target hit");
                    invaderHealth = invaderHealth - 1;
                    healthDisplay.setText("Health: " + invaderHealth);
                    if (invaderHealth == 0) {
                        goToNextLevel();
                    }
                }
            }

            // reset bullet position when it reaches the top
            if (bulletY == 0) {
                bullet.setXposition(player.getXposition());
                bullet.setYPosition(player.getYposition());
            }

        }));
        recordingTimeline.setCycleCount(Timeline.INDEFINITE);
        recordingTimeline.play();
    }

    //game restarts
    private void restartGame() {
        // restarts the game
        gameOver = false;
        currentLevel = 1;
        elapsedTime = 0;
        timerRun = false;
        pauseButton.setText("Start Game");

        // clear the game pane and start the game again
        gamePane.getChildren().clear();
        startGame();
    }

    private void goToNextLevel() {
        // increment the level
        currentLevel++;
        if (currentLevel > 3) {
            // If the current level exceeds 3, it's game over
            gameOver = true;
            pauseButton.setText("Game Over");
            gamePane.getChildren().clear();
            displayGameOver();
            return;
        }

        // update the invader properties for the next level
        invader.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel); // Update speed based on level
        invaderHealth = invader.getHealth();
        healthDisplay.setText("Health: " + invaderHealth);
        levelDisplay.setText("Level: " + currentLevel);
    }

    private void displayGameOver() {
        // show the game over message
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font(50));
        gameOverText.setFill(Color.DARKBLUE);
        gameOverText.setX(250);
        gameOverText.setY(300);
        gamePane.getChildren().add(gameOverText);
    }

    private void handleKeyPress(KeyCode key) {
        // Handles key press events
        if (!timerRun) {
            return; // Ignore key presses when the game is paused or over
        }

        // move with the A and D key and shoot with the M key
        switch (key) {
            case A:
                player.moveLeft();
                if (bulletY == 502) {
                    bullet.setXposition(player.getXposition());
                }
                break;
            case D:
                player.moveRight(800);
                if (bulletY == 502) {
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

    // Set up the health and level display texts
    private void setUpText() {
        //shows the health and level display texts
        healthDisplay = new Text("Health: " + invaderHealth);
        levelDisplay = new Text("Level: " + currentLevel);
        gamePane.getChildren().addAll(healthDisplay, levelDisplay);
        healthDisplay.setLayoutX(0);
        healthDisplay.setLayoutY(gamePane.getHeight() - 10);
        healthDisplay.setFont(Font.font(25));
        levelDisplay.setLayoutY(gamePane.getHeight() - 10);
        levelDisplay.setLayoutX(gamePane.getWidth() - 100);
        levelDisplay.setFont(Font.font(25));
    }

    // when the player hits the start game button this is called to show the message and
        // create an event when the bullet hits the invader, the health of the invader decreases
        // based on the position of the bullet
    public void playGame() {
        timer.start();
        pauseButton.setText("Game is Running");
        Timeline recordingTimeline =
                new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            bulletY = bullet.recordY();
            bulletX = bullet.recordX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();
            if (bulletX > invaderStartPosition - 10 &
                    bulletX < invaderEndPosition + 10)
                if (bulletY == (invader.getYposition())) {
                    System.out.println("target hit");
                    invaderHealth = invaderHealth - 1;
                    healthDisplay.setText("Health: " + invaderHealth);

                    // when the invader's health = 0 the next level is shown
                    if (invaderHealth == 0) {
                        goToNextLevel();
                    }
                }

            // reset bullet position when it reaches the top
            if (bulletY == 0) {
                bullet.setXposition(player.getXposition());
                bullet.setYPosition(player.getYposition());
            }

        }));
        recordingTimeline.setCycleCount(Timeline.INDEFINITE);
        recordingTimeline.play();
    }
    public static void main(String[] args) {
        launch();
    }
}