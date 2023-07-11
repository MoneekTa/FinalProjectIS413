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
    int level;
    int speed;
    int hearts;
    double startX;
    double endX;

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
//    public void moveInvader() {
//        double startX;
//        double endX;
//
//        if (moving) {
//            // if invader is moving to the left
//            startX = 0;
//            endX = pane.getWidth() - width;
//        } else {
//            // if invader is moving to the right
//            startX = pane.getWidth() - width;
//            endX = 0;
//        }
//
//        // timeline animation for each movement
//        TranslateTransition translateTransition =
//                new TranslateTransition(Duration.seconds(speed), invaderSquare);
//
//        // set the movement direction
//        translateTransition.setFromX(startX);
//        translateTransition.setToX(endX);
//
//        // when invader is finished moving, it will reverse
//        translateTransition.setOnFinished(e -> {
//            // Reverse the direction
//            moving = !moving;
//            // Restart the movement animation
//            moveInvader();
//        });
//
//        // Start the movement animation
//        translateTransition.play();
//    }
    public void moveInvader() {
        startX = invaderSquare.getTranslateX();
        endX = random.nextInt((int) pane.getWidth() - width);

        // timeline animation for each movement
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), invaderSquare);

        // set the movement direction
        translateTransition.setFromX(startX);
        translateTransition.setToX(endX);

        // when invader is finished moving, it will reverse
        translateTransition.setOnFinished(e -> {
            // Reverse the direction
            startX = invaderSquare.getTranslateX();
            endX = random.nextInt((int) pane.getWidth() - width);
            translateTransition.setFromX(startX);
            translateTransition.setToX(endX);

            // Restart the movement animation
            translateTransition.play();
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
                break;
        }
    }

    // speed of level is set
    public void setSpeed(int level) {
        switch (level) {
            case 1:
                speed = 0;
                break;
            case 2:
                speed = 0;
                break;
            case 3:
                speed = 0;
                break;
            default:
                speed = 0;
                break;
        }
    }

    // y position of the invader gets returned
    public long getYposition() {
        return (long) invaderSquare.getTranslateY();
    }

    // x position of the invader gets returned
    public long getXposition(){
        return (long) invaderSquare.getTranslateX();
    }

    // get the width of the pane and return the width
    public long getwidth(){
        return (long) width;
    }
    public long getheight(){
        return (long) height;
    }


    //will be used to display the level the player is on
    public int getLevel(){
        return level;
    }

    // used to display the health of the invader
    public int getHealth(){
        return hearts;}
}