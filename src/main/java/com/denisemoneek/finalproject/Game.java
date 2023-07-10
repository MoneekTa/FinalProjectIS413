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
    private Bullet bullet;
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
        timer.start();
        if(gameOver){
            resetsGame();
        }else{
            gameSetUp();
        }


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
            bullet = new Bullet(gamePane);
            invader = new Invader(gamePane);
            invader.setLevel(currentLevel);
            invader.setSpeed(currentLevel);
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
        if (currentLevel > 3) {
            gameOver = true;
            pauseButton.setText("Game Over " + "\n" +
                    "Press to Start Over");
            timerRun =!timerRun;
            displayGameOver();
        }
        invader.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel); // Update speed based on level
        invaderHealth = invader.getHealth();
        HealthDisplay.setText("Health: "+ invaderHealth);
        LevelDisplay.setText("Level: " + currentLevel);


    }
    private void displayGameOver() {
        Text gameOverText = new Text("Game Over"+
                "\n" + "Elasped Time: " + elapsedTime / 1000 + " seconds");
        gameOverText.setFont(Font.font(50));
        gameOverText.setFill(Color.DARKBLUE);
        gameOverText.setX(200);
        gameOverText.setY(300);
        gamePane.getChildren().add(gameOverText);
    }

    public void setUpText(){
        Button nextLevelBtn = new Button("Next Level");
        nextLevelBtn.setOnAction(e -> goToNextLevel());
        bullet.setXposition(player.getXposition());
        bullet.setYPosition(player.getYposition());

        gamePane.getChildren().add(nextLevelBtn);
        nextLevelBtn.setLayoutX(725);
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
    public void playGame(){
        timer.start();
        pauseButton.setText("Game is Running");
        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            bulletYposition = bullet.recordY();
            bulletXposition = bullet.recordX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();
            //System.out.println("invaderStartPosition: " + invaderStartPosition);
            //System.out.println("invaderEndPosition: " + invaderEndPosition);
            //System.out.println("Bullet X Position: " + bulletXposition);
            //System.out.println("Bullet Y Position: " + bulletYposition);
            //System.out.println("invaderYposition: " + (invader.getYposition() + invader.getheight()));
            if(bulletXposition > invaderStartPosition - 10 &
                    bulletXposition < invaderEndPosition + 10)
                if(bulletYposition == (invader.getYposition())){
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

    }
    public static void main(String[] args) {
        launch();
    }
}
