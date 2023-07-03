package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Game extends Application {
    private Invader invader;
    private int currentLevel = 1;

    @Override
    public void start(Stage primaryStage) {
        Pane gamePane = new Pane();

        // Display Invader shape
        invader = new Invader(gamePane); // Remove the variable declaration

        Button nextLevelBtn = new Button("Next Level");
        nextLevelBtn.setOnAction(e -> goToNextLevel());

        // btn layout
        BorderPane root = new BorderPane();
        root.setCenter(gamePane);
        root.setBottom(nextLevelBtn);
        BorderPane.setAlignment(nextLevelBtn, Pos.CENTER);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    private void goToNextLevel() {
        currentLevel++;
        invader.setLevel(currentLevel);
        invader.setSpeed(currentLevel); // Update speed based on level
    }
}
