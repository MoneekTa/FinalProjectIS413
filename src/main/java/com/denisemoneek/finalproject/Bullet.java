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

    public Bullet(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diameter);
        bullet.setFill(Color.BLACK);

        gamePane.getChildren().add(bullet);

        recording();
    }

    public void moveBullet() {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), bullet);
        double startY = 500;
        System.out.println("startY: " + startY);
        double endY = 0;

//            if (moving){
//                startY = 550;
//                endY = 0;
//            } else {
//                startY = 500;
//                endY = 0;
//            }

        translateTransition.setFromY(startY);
        //System.out.println("Bullet yPosition: " + bullet.getLayoutY());
        translateTransition.setToY(endY);
        translateTransition.setFromY(startY);
        translateTransition.play();
    }


    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
        bullet.setTranslateY(yPosition + (diameter / 2));
        //System.out.println("yPosition: " + yPosition);
    }

    public void setXposition(double xPosition) {
        //System.out.println("Bullet yPosition: " + bullet.getLayoutY());
        if (bullet.getLayoutY() == 0) {
            this.xPosition = xPosition;
            bullet.setTranslateX(xPosition + (diameter / 2) + 19);
            System.out.println("xPosition: " + xPosition);
        }
    }

    public long getYposition() {
        return (long) bullet.getLayoutY();
    }

    public long recording() {
        recordedPosition = (long) (bullet.getTranslateY() + bullet.getCenterY());
        System.out.println("Recorded Position: " + recordedPosition);
        return recordedPosition;

    }
}