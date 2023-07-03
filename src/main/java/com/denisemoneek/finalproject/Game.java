package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Game extends Application {
    Layout layout = new Layout();
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);

        Scene scene = new Scene(pane,400,400);
        scene.setFill(layout.Color());
        //int status = (int) (Math.random()*3);
        //if (status == 1) {
        //    scene.setFill(layout.Color());
        //} else {
            //Image backgroundImage = layout.Image().getImage();
            //pane.setStyle("-fx-background-image: url('" + backgroundImage.getUrl() + "'); " +
            //        "-fx-background-size: cover;");

        //};
        primaryStage.setTitle("LayoutTest");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}





