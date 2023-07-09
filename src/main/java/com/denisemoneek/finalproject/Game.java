package com.denisemoneek.finalproject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Layout layout = new Layout();
    private Button pauseButton = new Button("Start Game");
    private long elapsedTime;
    private long bulletYposition;
    private long bulletXposition;
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
        gamePane = new Pane(pauseButton);

        Scene scene = new Scene(gamePane, 800, 600);
        BackgroundFill backgroundFill = new BackgroundFill(layout.Color(), CornerRadii.EMPTY, Insets.EMPTY);
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

    private void startGame() {
        // Use this print code to debug
        System.out.println("Game Started");

        gamePane.getChildren().clear(); // Clear the game pane
        gamePane.getChildren().add(pauseButton); // Add the pause button back

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

        timerRun = !timerRun;
        pauseButton.setText("Game is Running");

        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(100), event2 -> {
            bulletYposition = bullet.recordingY();
            bulletXposition = bullet.recordingX();
            invaderStartPosition = invader.getXposition();
            invaderEndPosition = invader.getwidth() + invader.getXposition();

            if (bulletXposition > invaderStartPosition && bulletXposition < invaderEndPosition) {
                if (bulletYposition == invader.getYposition()) {
                    System.out.println("target hit");
                    invaderHealth = invaderHealth - 1;
                    healthDisplay.setText("Health: " + invaderHealth);
                    if (invaderHealth == 0) {
                        goToNextLevel();
                    }
                }
            }

            if (bulletYposition == 0) {
                bullet.setXposition(player.getXposition());
                bullet.setYPosition(player.getYposition());
            }

        }));
        recordingTimeline.setCycleCount(Timeline.INDEFINITE);
        recordingTimeline.play();
    }

    private void restartGame() {
        gameOver = false;
        currentLevel = 1;
        elapsedTime = 0;
        timerRun = false;
        pauseButton.setText("Start Game");
        gamePane.getChildren().clear(); // Clear the game pane
        startGame();
    }

    private void goToNextLevel() {
        currentLevel++;
        if (currentLevel > 3) {
            gameOver = true;
            pauseButton.setText("Game Over");
            gamePane.getChildren().clear();
            displayGameOver();
            return;
        }
        invader.setLevel(currentLevel);
        currentLevel = invader.getLevel();
        invader.setSpeed(currentLevel); // Update speed based on level
        invaderHealth = invader.getHealth();
        healthDisplay.setText("Health: " + invaderHealth);
        levelDisplay.setText("Level: " + currentLevel);
    }

    private void displayGameOver() {
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font(50));
        gameOverText.setFill(Color.DARKBLUE);
        gameOverText.setX(250);
        gameOverText.setY(300);
        gamePane.getChildren().add(gameOverText);
    }

    private void handleKeyPress(KeyCode key) {
        if (!timerRun) {
            return; // Ignore key presses when the game is paused or over
        }

        switch (key) {
            case A:
                player.moveLeft();
                if (bulletYposition == 502) {
                    bullet.setXposition(player.getXposition());
                }
                break;
            case D:
                player.moveRight(800);
                if (bulletYposition == 502) {
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

    private void setUpText() {
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

    public static void main(String[] args) {
        launch();
    }
}
