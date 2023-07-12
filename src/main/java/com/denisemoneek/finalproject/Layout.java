package com.denisemoneek.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.Random;

public class Layout implements LayoutChange{

    Random rand = new Random(); // Random number generator

    public Color Color() {
        int r = rand.nextInt(256); // Random value for the red component (0-255)
        int g = rand.nextInt(256); // Random value for the green component (0-255)
        int b = rand.nextInt(256); // Random value for the blue component (0-255)
        var color = Color.rgb(r, g, b); // Create a Color object with the random RGB values
        return color; // Return the generated color
    }
}

