package com.denisemoneek.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.Random;

public class Layout implements LayoutChange{
    Image background;
    String currentDir = System.getProperty("user.dir"); // Get the current directory path
    Random rand = new Random(); // Random number generator
    int height = 300; // height of the background image
    int width = 400; // width of the background image

    @Override
    public ImageView Image() throws FileNotFoundException {
        int randBackground = 1; // Randomly selected background image (change as needed)
        // Load the background image from file
        background = new Image("file:///" + currentDir + "/Images/" + randBackground + ".jpg");
        ImageView imageView = new ImageView(background); // Create an ImageView to display the image
        imageView.setFitWidth(width); // Set the width of the ImageView
        imageView.setFitHeight(height); // Set the height of the ImageView
        return imageView; // Return the ImageView
    }

    public Color Color() {
        int r = rand.nextInt(256); // Random value for the red component (0-255)
        int g = rand.nextInt(256); // Random value for the green component (0-255)
        int b = rand.nextInt(256); // Random value for the blue component (0-255)
        var color = Color.rgb(r, g, b); // Create a Color object with the random RGB values
        return color; // Return the generated color
    }
}

