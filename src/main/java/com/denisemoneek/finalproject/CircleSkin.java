package com.denisemoneek.finalproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
public class CircleSkin implements PlayerAesthetic{
    public Shape createPlayerShape() {
        Circle circle = new Circle(20);
        circle.setFill(Color.MAROON);
        return circle;
    }
}
