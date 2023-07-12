package com.denisemoneek.finalproject;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class InvaderBullet implements Bullet{
    private Pane pane;
    private Circle bullet;
    private Random random;
    private boolean moving;
    private int speed = 1;
    private static final int diameter = 5;
    private double xPosition = 0;
    private double yPosition = 0;

    private boolean gamePaused = false; // Added gamePaused variable
    private long recordedPosition;

    //InvaderBullet object
    public InvaderBullet(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diameter);
        bullet.setFill(Color.BLACK);

        // displaying bullet
        gamePane.getChildren().add(bullet);
        moveBullet();
    }

    // bullet movement
    public void moveBullet() {
        double startY = 50;
        double endY = 600;


        // Timeline animation for each movement
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), bullet);

        // Set the movement direction
        translateTransition.setFromY(startY);
        translateTransition.setToY(endY);

        // When invader is finished moving, it will reverse
        translateTransition.setOnFinished(e -> {
            // Reverse the direction
            moving = !moving;
            // Restart the movement animation
            translateTransition.setFromX(xPosition);
            moveBullet();

        });

        // Start the movement animation
        translateTransition.play();
    }

    public void setYPosition(double yPosition) {
        // set the y-position of the bullet
        this.yPosition = yPosition;
        bullet.setTranslateY(yPosition + (diameter / 2));
    }

    public void setXposition(double xPosition) {
        // set the x-position of the bullet
            this.xPosition = xPosition;
            bullet.setTranslateX(xPosition + (diameter / 2) + 19);
        }

    // calculates and returns the recorded position of the bullet along the y-axis.
    public long recordY() {
        // Calculates and returns the recorded position of the bullet along the y-axis
        recordedPosition = (long) (bullet.getTranslateY() + bullet.getCenterY());
        return recordedPosition;
    }

    public long recordX() {
        // Calculates and returns the recorded position of the bullet along the x-axis
        recordedPosition = (long) (bullet.getTranslateX() + bullet.getCenterX());
        return recordedPosition;
    }
}
