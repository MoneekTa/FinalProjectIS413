package com.denisemoneek.finalproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
public class CircleSkin implements PlayerAesthetic {
    Circle circle = new Circle(20);
    public Shape createPlayerShape() {
        circle.setFill(Color.MAROON);
        return circle;
    }
}
