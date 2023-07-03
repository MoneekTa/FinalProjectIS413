package com.denisemoneek.finalproject;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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

    private void moveInvader() {
        double startX = moving ? 0 : pane.getWidth() - width;
        double endX = moving ? pane.getWidth() - width : 0;

        // Create a new Timeline animation for each movement
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), invaderSquare);

        // Set the movement direction
        translateTransition.setFromX(startX);
        translateTransition.setToX(endX);

        // when invader is finished moving, it will reverse
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
                invaderSquare.setFill(Color.YELLOW);
                break;
            case 3:
                hearts = 5;
                speed = 1;
                invaderSquare.setFill(Color.RED);
                break;
            default:
                hearts = 1;
                speed = 3;
                invaderSquare.setFill(Color.DARKCYAN);
                break;
        }
// display hearts on screen
//        public void heart();

    }
}