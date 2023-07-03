package com.denisemoneek.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;

public class Game extends Application {
    private Player player;
    private Pane gamePane;

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane();

        player = new Player();

        ComboBox<String> skinComboBox = new ComboBox<>();
        skinComboBox.getItems().addAll("Triangle", "Circle", "Square");
        skinComboBox.setValue("Triangle");
        skinComboBox.setOnAction(e -> {
            String selectedSkin = skinComboBox.getValue();
            setPlayerSkin(selectedSkin);
        });

        skinComboBox.setLayoutX(650);
        skinComboBox.setLayoutY(20);

        gamePane.getChildren().add(skinComboBox);
        updatePlayerShape();

        Scene scene = new Scene(gamePane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JFX Invaders");
        primaryStage.show();
    }

    private void setPlayerSkin(String skin) {
        switch (skin) {
            case "Triangle":
                player.setPlayerAesthetic(new TriangleSkin());
                break;
            case "Circle":
                player.setPlayerAesthetic(new CircleSkin());
                break;
            case "Square":
                player.setPlayerAesthetic(new SqaureSkin());
                break;
            default:
                player.setPlayerAesthetic(new TriangleSkin());
                break;
        }

        updatePlayerShape();
    }

    private void updatePlayerShape() {
        Shape playerShape = player.getPlayerShape();
        playerShape.setLayoutX(380);
        playerShape.setLayoutY(500);

        gamePane.getChildren().removeIf(node -> node instanceof Shape);
        gamePane.getChildren().add(playerShape);
    }
    public static void main(String[] args) {
        launch();
    }
}
