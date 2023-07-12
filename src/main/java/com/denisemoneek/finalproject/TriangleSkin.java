package com.denisemoneek.finalproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleSkin implements PlayerAesthetic{
    // create the player appearance as a triangle
    public Shape createPlayerShape() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 40.0,
                20.0, 0.0,
                40.0, 40.0
        );
        triangle.setFill(Color.MIDNIGHTBLUE);
        return triangle;
    }
}
