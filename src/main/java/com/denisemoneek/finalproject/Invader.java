package com.denisemoneek.finalproject;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Random;
public class Invader implements Levels {
    private static int width = 50;
    private static int height = 50;
    private Pane pane;
    private Rectangle invaderSquare;
    private Random random;
    private boolean moving;
    private int level;
    private int speed;
    private int hearts;

    public Invader(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();
        this.level = 1;

        // Create invader square
        invaderSquare = new Rectangle(width, height);
        invaderSquare.setFill(Color.GREEN);

        // Add invader to the game pane
        gamePane.getChildren().add(invaderSquare);

        // Set initial level
        setLevel(level);

        // Start moving the invader
        moveInvader();
    }

    // Invader movement
   public void moveInvader() {
        double startX;
        double endX;

        if (moving) {
            startX = 0;
            endX = pane.getWidth() - width;
        } else {
            startX = pane.getWidth() - width;
            endX = 0;
        }

        // Timeline animation for each movement
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), invaderSquare);

        // Set the movement direction
        translateTransition.setFromX(startX);
        translateTransition.setToX(endX);

        // When invader is finished moving, it will reverse
        translateTransition.setOnFinished(e -> {
            // Reverse the direction
            moving = !moving;
            // Restart the movement animation
            moveInvader();
        });

        // Start the movement animation
        translateTransition.play();
    }

    public void setLevel(int level) {
        this.level = level;

        // Update invader properties based on the level
        switch (level) {
            case 1:
                hearts = 1;
                speed = 3;
                invaderSquare.setFill(Color.DARKCYAN);
                break;
            case 2:
                hearts = 2;
                speed = 2;
                invaderSquare.setFill(Color.GOLD);
                break;
            case 3:
                hearts = 5;
                speed = 1;
                invaderSquare.setFill(Color.MAROON);
                break;
            default:
                hearts = 1;
                speed = 3;
                invaderSquare.setFill(Color.DARKCYAN);
                break;
        }
    }

    public void setSpeed(int level) {
        switch (level) {
            case 1:
                speed = 3;
                break;
            case 2:
                speed = 2;
                break;
            case 3:
                speed = 1;
                break;
            default:
                speed = 3;
                break;
        }
    }
}