package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
    private Player player;
    private Pane gamePane;

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane();

        player = new Player(gamePane);

        Scene scene = new Scene(gamePane, 800, 600);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight(800);
                break;
            default:
                // Ignore other keys
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
