package com.denisemoneek.finalproject;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;
    private ComboBox<String> skinComboBox;
    private Pane gamePane;

    public Player(Pane gamePane) {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
        this.gamePane = gamePane;
        initializeSkinComboBox(gamePane);
        updatePlayerShape();
    }

    private void initializeSkinComboBox(Pane gamePane) {
        skinComboBox = new ComboBox<>();
        skinComboBox.getItems().addAll("Triangle", "Circle", "Square");
        skinComboBox.setValue("Skins");
        skinComboBox.setOnAction(e -> {
            String selectedSkin = skinComboBox.getValue();
            setPlayerSkin(selectedSkin);
        });

        skinComboBox.setLayoutX(650);
        skinComboBox.setLayoutY(20);

        gamePane.getChildren().add(skinComboBox);
    }

    private void setPlayerSkin(String skin) {
        switch (skin) {
            case "Triangle":
                playerAesthetic = new TriangleSkin();
                break;
            case "Circle":
                playerAesthetic = new CircleSkin();
                break;
            case "Square":
                playerAesthetic = new SquareSkin();
                break;
            default:
                playerAesthetic = new TriangleSkin();
                break;
        }

        playerShape = playerAesthetic.createPlayerShape();
        updatePlayerShape();
    }

    private void updatePlayerShape() {
        playerShape.setLayoutX(380);
        playerShape.setLayoutY(500);

        gamePane.getChildren().removeIf(node -> node instanceof Shape);
        gamePane.getChildren().add(playerShape);
    }

    public Shape getPlayerShape() {
        return playerShape;
    }

    @Override
    public Shape createPlayerShape() {
        return playerAesthetic.createPlayerShape();
    }
}