package com.denisemoneek.finalproject;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;
public class Bullet {
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

    //Bullet object
    public Bullet(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diameter);
        bullet.setFill(Color.BLACK);

        // displaying bullet
        gamePane.getChildren().add(bullet);
        // call the recordY function to get the axis
        recordY();
    }

    // bullet movement
    public void moveBullet() {
    // moving the bullet vertically
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), bullet);
        double startY = 500; // starting y coordinate of the bullet
        double endY = 0; // the y coordinate of the bullet which is the top of the screen

        translateTransition.setFromY(startY);
        translateTransition.setToY(endY);
        translateTransition.setFromY(startY);
        translateTransition.play();
    }

    public void setYPosition(double yPosition) {
        // set the y-position of the bullet
        this.yPosition = yPosition;
        bullet.setTranslateY(yPosition + (diameter / 2));
    }

    public void setXposition(double xPosition) {
        // set the x-position of the bullet if it is at the top of the screen
        if (bullet.getLayoutY() == 0) {
            this.xPosition = xPosition;
            bullet.setTranslateX(xPosition + (diameter / 2) + 19);
        }
    }

    // calculates and returns the recorded position of the bullet along the y-axis.
        // adding the translation in the y-direction
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