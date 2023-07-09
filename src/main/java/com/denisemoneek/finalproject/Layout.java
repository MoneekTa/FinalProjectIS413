package com.denisemoneek.finalproject;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.util.Random;

public class Layout implements LayoutChange{
    Image background;
    String currentDir = System.getProperty("user.dir");
    Random rand = new Random();
    int height = 300;
    int width = 400;

    @Override
    public ImageView Image() throws FileNotFoundException {
        int randBackground  = 1;
        //rand.nextInt(0,10);
        background = new Image("file:///" + currentDir + "/Images/" + randBackground + ".jpg");
        ImageView imageView = new ImageView(background);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        //return background;
        return imageView;
    }
    public Color Color(){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        var color = Color.rgb(r,g,b);
        return color;
    };

}

