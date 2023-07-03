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
    private int speed;
    private static int diamater = 20;


    public Bullet(Pane gamePane){
        this.pane = gamePane;
        this.random = new Random();
        this.moving = random.nextBoolean();

        bullet = new Circle(diamater);
        bullet.setFill(Color.BLACK);

        gamePane.getChildren().add(bullet);
        moveBullet();
    }

    public void moveBullet(int position){
        double startX;
        double endX;
        if(moving){
            startX = 0;
            endX = pane.getWidth() - diamater;
        }else{
            startX = pane.getWidth() - diamater;
            endX = 0;
        }
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.seconds(speed),bullet);
        translateTransition.setFromX(startX);
        translateTransition.setToX(endX);

        });
    }
}
