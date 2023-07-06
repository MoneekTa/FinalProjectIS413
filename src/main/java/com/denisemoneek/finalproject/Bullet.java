package com.denisemoneek.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;
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
        pane.setOnMouseClicked(e -> {
            moveBullet();
        });
        // startRecording();
    }

    public void moveBullet() {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed), bullet);
        double startY = 500;
        System.out.println("startY: " + startY);
        double endY = 0;
//            if (moving)
//                startY = 0;
//                endY = 500;
//            } else {
//                startY = 500;
//                endY = 0;
//            }

        translateTransition.setFromY(startY);
        //System.out.println("Bullet yPosition: " + bullet.getLayoutY());
        translateTransition.setToY(endY);
        translateTransition.play();
    }


    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
        bullet.setTranslateY(yPosition + (diameter / 2));
        //System.out.println("yPosition: " + yPosition);
    }

    public void setXPosition(double xPosition) {
        //System.out.println("Bullet yPosition: " + bullet.getLayoutY());
        if (bullet.getLayoutY() == 0) {
            this.xPosition = xPosition;
            bullet.setTranslateX(xPosition + (diameter / 2) + 19);
            System.out.println("xPosition: " + xPosition);
        }
    }

    public long getYPosition() {
        return (long) bullet.getLayoutY();
    }
}
//    private void startRecording() {
//        Timeline recordingTimeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
//            if (!gamePaused) {
//                recordedPosition = (long) (bullet.getTranslateY() + bullet.getCenterY());
//                if (recordedPosition == 0) {
//
//                }
//            }
//        }));
//        recordingTimeline.setCycleCount(Timeline.INDEFINITE);
//        recordingTimeline.play();
//    }
//}