package com.denisemoneek.finalproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class Invader {
    private static int width = 50;
    private static int height = 50;
    private static int speed = 2;

    private Pane pane;
    private Rectangle invaderSquare;
    private Random random;
    private boolean movingRight;

    public Invader(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.movingRight = random.nextBoolean();

        // Create invader square
        invaderSquare = new Rectangle(width, height);
        invaderSquare.setFill(Color.DARKCYAN);

        // Add invader to the game pane
        gamePane.getChildren().add(invaderSquare);

        // Start moving the invader
        moveInvader();
    }

    private void moveInvader() {
        double startX = movingRight ? 0 : pane.getWidth() - width;
        double endX = movingRight ? pane.getWidth() - width : 0;

        // Create a Timeline animation
        Timeline timeline = new Timeline();

        // Create KeyValues for smooth animation
        KeyValue startKeyValue = new KeyValue(invaderSquare.translateXProperty(), startX);
        KeyValue endKeyValue = new KeyValue(invaderSquare.translateXProperty(), endX);

        // Create KeyFrames for the animation
        KeyFrame startFrame = new KeyFrame(Duration.ZERO, startKeyValue);
        KeyFrame endFrame = new KeyFrame(Duration.seconds(2), endKeyValue);

        // Add KeyFrames to the timeline
        timeline.getKeyFrames().addAll(startFrame, endFrame);

        // Set up the callback when the movement is finished
        timeline.setOnFinished(e -> moveInvader());

        // Start the movement animation
        timeline.play();
    }
}