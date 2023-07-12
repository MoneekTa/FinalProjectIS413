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
    private Player player;
    private Pane gamePane;
    private PlayerBullet playerBullet;
    private InvaderBullet invaderBullet;
    Button pauseButton = new Button("Start Game");
    long elapsedTime;
    long playerBulletYposition;
    long playerBulletXposition;
    long invaderBulletYposition;
    long invaderBulletXposition;
    long invaderStartPosition;
    long invaderEndPosition;
    long playerPosition;
    int invaderHealth;
    int playerHealth;
    Timer timer = new Timer();
    boolean timerRun = false;
    private Invader invader;
    private int currentLevel = 1;
    Text invaderHealthDisplay;
    Text levelDisplay;
    Text playerHealthDisplay;
    Layout layout = new Layout();
    boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {

        gamePane = new Pane(pauseButton);
        //gamePane.setBackground();
        BackgroundFill backgroundFill = new BackgroundFill(layout.Color(), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);
        Scene scene = new Scene(gamePane, 800, 600,layout.Color());
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        Text Instructions = new Text("A: left D: right M: shoot");
        Instructions.setFont(Font.font(50));
        Instructions.setFill(Color.DARKBLUE);
        Instructions.setX(150);
        Instructions.setY(300);
        gamePane.getChildren().add(Instructions);
        timer.start();
        gameSetUp();
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();

    }
    private void resetsGame(){
        gameOver = false;
        currentLevel = 1;
        elapsedTime = 0;
        timerRun = false;
        pauseButton.setText("Start Game");
        gameSetUp();
    }
    private void gameSetUp(){
        pauseButton.setOnAction(event -> {
            // Use this print code to debug
            System.out.println("Game Started");
            player = new Player(gamePane);
            playerBullet = new PlayerBullet(gamePane);
            invader = new Invader(gamePane);
            invaderBullet = new InvaderBullet(gamePane);
            System.out.println(currentLevel);
            invader.setLevel(currentLevel);
            invader.setSpeed(currentLevel);
            player.setLevel(currentLevel);
            playerHealth = player.getHealth();
            invaderHealth = invader.getHealth();
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
        });

    }

// using A and D keys on the keyboard to move player
    private void handleKeyPress(KeyCode key) {
        if (timerRun) {
            switch (key) {
                case A:
                    player.moveLeft();
                    if(playerBulletYposition == 502) {
                        playerBullet.setXposition(player.getXposition());
                    }
                    break;
                case D:
                    player.moveRight(800);
                    if(playerBulletYposition == 502) {
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
    private void goToNextLevel() {
        BackgroundFill backgroundFill = new BackgroundFill(layout.Color(), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        gamePane.setBackground(background);
        currentLevel++;
        if (currentLevel > 3) {
            gameOver = true;
            pauseButton.setText("Game Over " + "\n" +
                    "Press to Start Over");
            resetsGame();
            displayGameOver();
        }
        invader.setLevel(currentLevel);
        player.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel); // Update speed based on level
        invaderHealth = invader.getHealth();
        playerHealth = player.getHealth();
        invaderHealthDisplay.setText("Invader's Health: "+ invaderHealth);
        playerHealthDisplay.setText("Player's Health: "+ playerHealth);
        levelDisplay.setText("Level: " + currentLevel);


    }
    private void displayGameOver() {

        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font(50));
        gameOverText.setFill(Color.DARKBLUE);
        gameOverText.setX(200);
        gameOverText.setY(300);
        gamePane.getChildren().add(gameOverText);
    }

    public void setUpText(){
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
        invaderHealthDisplay.setLayoutY(gamePane.getHeight()-10);
        invaderHealthDisplay.setFont(Font.font(25));

        playerHealthDisplay.setLayoutX(gamePane.getWidth()/2);
        playerHealthDisplay.setLayoutY(gamePane.getHeight()-10);
        playerHealthDisplay.setFont(Font.font(25));

        levelDisplay.setLayoutX(gamePane.getWidth()-100);
        levelDisplay.setLayoutY(gamePane.getHeight()-10);
        levelDisplay.setFont(Font.font(25));

    }
    private void showExplosion(double x, double y) {
        Explode explosion = new Explode(gamePane, x, y);
        explosion.play();
    }
    public void playGame(){
        timer.start();
        pauseButton.setText("Game is Running");
        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            playerBulletYposition = playerBullet.recordY();
            playerBulletXposition = playerBullet.recordX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();
            invaderBulletYposition = invaderBullet.recordY();
            invaderBulletXposition = invaderBullet.recordX();
            playerPosition = (long) player.getXposition();

            if(playerBulletXposition > invaderStartPosition - 10 &
                    playerBulletXposition < invaderEndPosition + 10){
                if(playerBulletYposition == (invader.getYposition())){
                    System.out.println("target hit");
                    invaderHealth = invaderHealth - 1;
                    invaderHealthDisplay.setText("Invader's Health: "+ playerHealth);
                    if(invaderHealth == 0){
                        goToNextLevel();
                        showExplosion(invader.getXposition(), invader.getYposition());

                    }
                }}

            if(invaderBulletXposition > playerPosition - 10 &
                    invaderBulletXposition < playerPosition + 10){
                if(invaderBulletYposition < (player.getYposition()) &
                        playerHealth > 0) {
                    System.out.println("player hit");
                    playerHealth = playerHealth- 1;
                    playerHealthDisplay.setText("Player's Health: " + playerHealth);
                    if (playerHealth == 0) {
                        displayGameOver();
                        gameOver = true;
                        showExplosion(player.getXposition(), player.getYposition());
                        pauseButton.setText("Game Over " + "\n" +
                                "Press to Start Over");
                        displayGameOver();

                    }
                }}
            if(invaderBulletYposition >= 590){
                invaderBullet.setXposition(invader.getXposition());
                invaderBullet.setYPosition(invader.getYposition());
            }

            if(playerBulletYposition == 0){
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
