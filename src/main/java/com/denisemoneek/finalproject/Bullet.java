package com.denisemoneek.finalproject;

import javafx.animation.TranslateTransition;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
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
    private static int diamater = 20;
    public static final EventType<MouseEvent> MOUSE_CLICKED = null;

    public Bullet(Pane gamePane){
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diamater);
        bullet.setFill(Color.BLACK);

        gamePane.getChildren().add(bullet);
        pane.setOnMouseClicked(e-> {
            moveBullet((int)pane.getHeight());
        });
    }

    public void moveBullet(int position){
        double startY = position;
        double endY;
        if(moving){
            startY = pane.getHeight() - diamater;
            endY = 0;
        }else{
            startY = 0;
            endY = pane.getHeight() - diamater;

        }
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed),bullet);
        translateTransition.setFromY(startY);
        translateTransition.setToY(endY);
        translateTransition.play();

        };
    }

