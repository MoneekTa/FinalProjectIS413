package com.denisemoneek.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public interface LayoutChange {

    public ImageView Image() throws FileNotFoundException;
    public Color Color();



}