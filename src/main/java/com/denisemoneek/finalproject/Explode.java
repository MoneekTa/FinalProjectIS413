package com.denisemoneek.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.util.Duration;

import java.util.Random;

public class Explode{
    private Pane pane;
    private double x;
    private double y;
    private Random random;
    private Timeline explodeTimeline;

    public Explode(Pane pane, double x, double y) {
        this.pane = pane;
        this.x = x;
        this.y = y;
        this.random = new Random();
    }

    public void play() {
        // number of particles that disperse from explosion
        int numParticles = 20;
        // size of explosion particles
        double radius = 5;

        for (int i = 0; i < numParticles; i++) {
            Circle particle = new Circle(radius);
            particle.setFill(getRandomColor());

            pane.getChildren().add(particle);
            particle.setTranslateX(x);
            particle.setTranslateY(y);

            double speed = random.nextDouble() * 5 + 1;
            double angle = random.nextDouble() * 180;
            double cosX = speed * Math.cos(Math.toRadians(angle));
            double sinY = speed * Math.sin(Math.toRadians(angle));

            KeyFrame keyF = new KeyFrame(Duration.millis(1000 / 60), event -> {
                particle.setTranslateX(particle.getTranslateX() + cosX);
                particle.setTranslateY(particle.getTranslateY() + sinY);
            });

            if (explodeTimeline == null) {
                explodeTimeline = new Timeline(keyF);
                explodeTimeline.setCycleCount(Timeline.INDEFINITE);
                explodeTimeline.play();
            } else {
                explodeTimeline.getKeyFrames().add(keyF);
            }
        }
    }

// random colors for explosion
    private Color getRandomColor() {
        double red = random.nextDouble();
        double green = random.nextDouble();
        double blue = random.nextDouble();
        return new Color(red, green, blue, 1.0);
    }
}

