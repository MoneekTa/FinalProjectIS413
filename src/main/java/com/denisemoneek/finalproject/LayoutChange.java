package com.denisemoneek.finalproject;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public interface LayoutChange {

    // image object to show image
    public ImageView Image() throws FileNotFoundException;

    // used to change background color
    public Color Color();
}