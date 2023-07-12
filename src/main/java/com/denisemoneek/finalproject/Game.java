package com.denisemoneek.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Game extends Application {
    // Instance variables
    private Player player;
    private Pane gamePane;
    private PlayerBullet playerBullet;
    private InvaderBullet invaderBullet;
    private Button pauseButton = new Button("Start Game");
    private long elapsedTime;
    private long playerBulletYposition;
    private long playerBulletXposition;
    private long invaderBulletYposition;
    private long invaderBulletXposition;
    private long invaderStartPosition;
    private long invaderEndPosition;
    private long playerPosition;
    private int invaderHealth;
    private int playerHealth;
    private Timer timer = new Timer();
    private boolean timerRun = false;
    private Invader invader;
    private int currentLevel = 1;
    private Text invaderHealthDisplay;
    private Text levelDisplay;
    private Text playerHealthDisplay;
    private Layout layout = new Layout();
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        // Create the game pane
        gamePane = new Pane(pauseButton);

        // Set the background color
        backgroundfill();

        // Create the scene
        Scene scene = new Scene(gamePane, 800, 600, layout.Color());

        // Handle key presses
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        // Display instructions
        displayinstructions();

        // Start the timer
        timer.start();

        // Set up the game
        gameSetUp();

        // Set up the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    // set background color of game 
    private void backgroundfill() {
        BackgroundFill backgroundFill = new BackgroundFill(layout.Color(), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);
    }

    // how to play display 
    private void displayinstructions() {
        Text instructions = new Text("A: left D: right M: shoot");
        instructions.setFont(Font.font(50));
        instructions.setFill(Color.DARKBLUE);
        instructions.setX(150);
        instructions.setY(300);
        gamePane.getChildren().addAll(instructions);
    }

    // reset
    private void resetsGame() {
        gameOver = false;
        currentLevel = 1;
        elapsedTime = 0;
        timerRun = false;
        pauseButton.setText("Start Game");
        gameSetUp();
    }

    // when the game is running
    private void gameSetUp() {
        pauseButton.setOnAction(event -> {
            // Use this print code to debug
            System.out.println("Game Started");

            // Initialize game objects
            player = new Player(gamePane);
            playerBullet = new PlayerBullet(gamePane);
            invader = new Invader(gamePane);
            invaderBullet = new InvaderBullet(gamePane);

            // Set current level
            System.out.println(currentLevel);
            invader.setLevel(currentLevel);
            invader.setSpeed(currentLevel);
            player.setLevel(currentLevel);

            // Set player and invader health
            playerHealth = player.getHealth();
            invaderHealth = invader.getHealth();

            // Set up the text display
            setUpText();

            // If the timerRun is false, meaning the timer is not running
            if (!timerRun) {
                playGame();
            } else {
                // Update elapsed time and display pause message
                elapsedTime += timer.stop();
                pauseButton.setText("Game is Paused " +
                        "\n" + "Time: " + elapsedTime / 1000 + " seconds");
            }

            // Resets timerRun
            timerRun = !timerRun;
        });
    }

    // Handle key presses
    private void handleKeyPress(KeyCode key) {
        if (timerRun) {
            switch (key) {
                case A:
                    player.moveLeft();
                    if (playerBulletYposition == 502) {
                        playerBullet.setXposition(player.getXposition());
                    }
                    break;
                case D:
                    player.moveRight(800);
                    if (playerBulletYposition == 502) {
                        playerBullet.setXposition(player.getXposition());
                    }
                    break;
                case M:
                    playerBullet.moveBullet();
                    break;
                default:
                    // Ignore other keys
                    break;
            }
        }
    }

    // Go to the next level
    private void goToNextLevel() {
        backgroundfill();

        // Increase the level
        currentLevel++;

        // Check if the game is over
        if (currentLevel > 3) {
            gameOver = true;
            pauseButton.setText("Game Over " + "\n" + "Press to Start Over");
            resetsGame();
            displayGameOver();
        }

        // Update invader settings based on level
        invader.setLevel(currentLevel);
        player.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel);

        // Update health and level display
        invaderHealth = invader.getHealth();
        playerHealth = player.getHealth();
        invaderHealthDisplay.setText("Invader's Health: " + invaderHealth);
        playerHealthDisplay.setText("Player's Health: " + playerHealth);
        levelDisplay.setText("Level: " + currentLevel);
    }
    
    //show game over when player or invader dies
    private void displayGameOver() {
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font(50));
        gameOverText.setFill(Color.DARKBLUE);
        gameOverText.setX(200);
        gameOverText.setY(300);
        gamePane.getChildren().add(gameOverText);
    }

    //show health, of player and invader, next level button
    public void setUpText() {
        Button nextLevelBtn = new Button("Next Level");
        nextLevelBtn.setOnAction(e -> goToNextLevel());
        playerBullet.setXposition(player.getXposition());
        playerBullet.setYPosition(player.getYposition());

        invaderBullet.setXposition(invader.getXposition());
        invaderBullet.setYPosition(invader.getYposition());

        gamePane.getChildren().add(nextLevelBtn);
        nextLevelBtn.setLayoutX(725);
        invaderHealthDisplay = new Text("Invader's Health: " + invaderHealth);
        levelDisplay = new Text("Level: " + currentLevel);
        playerHealthDisplay = new Text("Player's Health: " + playerHealth);
        gamePane.getChildren().addAll(invaderHealthDisplay, levelDisplay, playerHealthDisplay);
        invaderHealthDisplay.setLayoutX(0);
        invaderHealthDisplay.setLayoutY(gamePane.getHeight() - 10);
        invaderHealthDisplay.setFont(Font.font(25));

        playerHealthDisplay.setLayoutX(gamePane.getWidth() / 2);
        playerHealthDisplay.setLayoutY(gamePane.getHeight() - 10);
        playerHealthDisplay.setFont(Font.font(25));

        levelDisplay.setLayoutX(gamePane.getWidth() - 100);
        levelDisplay.setLayoutY(gamePane.getHeight() - 10);
        levelDisplay.setFont(Font.font(25));
    }

    // play exploison
    private void showExplosion(double x, double y) {
        Explode explosion = new Explode(gamePane, x, y);
        explosion.play();
    }

    // when game is running
    public void playGame() {
        timer.start();
        pauseButton.setText("Game is Running");

        // Create a timeline to record game positions and check for collisions
        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            // Record positions
            playerBulletYposition = playerBullet.recordY();
            playerBulletXposition = playerBullet.recordX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();
            invaderBulletYposition = invaderBullet.recordY();
            invaderBulletXposition = invaderBullet.recordX();
            playerPosition = (long) player.getXposition();

            // Check collision between player bullet and invader
            if (playerBulletXposition > invaderStartPosition - 10 &
                    playerBulletXposition < invaderEndPosition + 10) {
                if (playerBulletYposition == invader.getYposition()) {
                    System.out.println("target hit");
                    invaderHealth = invaderHealth - 1;
                    invaderHealthDisplay.setText("Invader's Health: " + playerHealth);

                    // Check if invader is defeated
                    if (invaderHealth == 0) {
                        goToNextLevel();
                        showExplosion(invader.getXposition(), invader.getYposition());
                    }
                }
            }

            // Check collision between invader bullet and player
            if (invaderBulletXposition > playerPosition - 10 &
                    invaderBulletXposition < playerPosition + 10) {
                if (invaderBulletYposition < player.getYposition() &
                        playerHealth > 0) {
                    System.out.println("player hit");
                    playerHealth = playerHealth - 1;
                    playerHealthDisplay.setText("Player's Health: " + playerHealth);

                    // Check if player is defeated
                    if (playerHealth == 0) {
                        displayGameOver();
                        gameOver = true;
                        showExplosion(player.getXposition(), player.getYposition());
                        pauseButton.setText("Game Over " + "\n" + "Press to Start Over");
                        displayGameOver();
                    }
                }
            }

            // Reset invader bullet position
            if (invaderBulletYposition >= 590) {
                invaderBullet.setXposition(invader.getXposition());
                invaderBullet.setYPosition(invader.getYposition());
            }

            // Reset player bullet position
            if (playerBulletYposition == 0) {
                playerBullet.setXposition(player.getXposition());
                playerBullet.setYPosition(player.getYposition());
            }
        }));

        recordingTimeline.setCycleCount(Timeline.INDEFINITE);
        recordingTimeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
