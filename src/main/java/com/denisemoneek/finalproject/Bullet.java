package com.denisemoneek.finalproject;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
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

    public Bullet(Pane gamePane) {
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diameter);
        bullet.setFill(Color.BLACK);

        gamePane.getChildren().add(bullet);
        pane.setOnMouseClicked(e -> {
            moveBullet(pane.getHeight());
        });
    }

    public void moveBullet(double position) {
        if (!gamePaused) {
            double startY = 500;
            System.out.println("startY: " + startY);
            double endY;
            if (moving) {
                startY = 0;
                endY = 500;
            } else {
                startY = 500;
                endY = 0;
            }
            TranslateTransition translateTransition =
                    new TranslateTransition(Duration.seconds(speed), bullet);
            translateTransition.setFromY(startY);
            translateTransition.setToY(endY);
            translateTransition.play();
        }
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
        bullet.setTranslateY(yPosition + (diameter / 2));
        System.out.println("yPosition: " + yPosition);
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
        bullet.setTranslateX(xPosition + (diameter / 2) + 19);
        System.out.println("xPosition: " + xPosition);
    }
}