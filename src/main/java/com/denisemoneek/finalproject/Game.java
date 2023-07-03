package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    private Bullet bullet;
    @Override
    public void start(Stage primaryStage) {
        Pane gamePane = new Pane();
        bullet = new Bullet(gamePane);

        Scene scene = new Scene(gamePane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





