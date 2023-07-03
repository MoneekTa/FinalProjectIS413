package com.denisemoneek.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Invader {
    private static int width = 50;
    private static int height = 50;
    private static int speed = 2;

    private Pane gamePane;
    private ImageView invaderView;
    private Random random;
    private boolean movingRight;

    public Invader(Pane gamePane) {
        this.gamePane = gamePane;
        this.random = new Random();
        this.movingRight = random.nextBoolean();

        // Load invader image
        Image invaderImage = new Image("invader.png");
        invaderView = new ImageView(invaderImage);
        invaderView.setFitWidth(width);
        invaderView.setFitHeight(height);

        // Add invader to the game pane
        gamePane.getChildren().add(invaderView);

        // Start moving the invader
        moveInvader();
    }

    private void moveInvader() {
        javafx.animation.TranslateTransition translateTransition = new javafx.animation.TranslateTransition(Duration.millis(1000), invaderView);

        // Set the movement direction (left or right)
        int direction = movingRight ? 1 : -1;

        // Calculate the new X position
        double newX = invaderView.getTranslateX() + (direction * speed);

        // Check if the invader reaches the game pane's boundaries
        if (newX <= 0 || newX >= gamePane.getWidth() - width) {
            // Change the movement direction
            movingRight = !movingRight;
            direction = movingRight ? 1 : -1;
        }

        // Update the invader's position
        translateTransition.setToX(newX);

        // Set up the callback when the movement is finished
        translateTransition.setOnFinished(event -> moveInvader());

        // Start the movement animation
        translateTransition.play();
    }
}
