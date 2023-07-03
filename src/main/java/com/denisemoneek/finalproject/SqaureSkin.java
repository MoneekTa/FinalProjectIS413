package com.denisemoneek.finalproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public class SqaureSkin implements PlayerAesthetic{

    public Shape createPlayerShape() {
        Rectangle square = new Rectangle(40, 40);
        square.setFill(Color.BLUE);
        return square;
    }
}

