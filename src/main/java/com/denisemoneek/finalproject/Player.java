package com.denisemoneek.finalproject;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;
    private ComboBox<String> skinChoices;
    private Pane gamePane;
    private int xSpeed = 10; // speed of the player

//by default the player is a triangle
    public Player(Pane gamePane) {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
        this.gamePane = gamePane;
        skinDropDown(gamePane);
        updatePlayerShape();
    }

//drop down of the player skin options are displayed
    private void skinDropDown(Pane gamePane) {
        skinChoices = new ComboBox<>();
        skinChoices.getItems().addAll("Triangle", "Circle", "Square");
        skinChoices.setValue("Player Skins");
        skinChoices.setOnAction(skinEvent -> {
            String selectedSkin = skinChoices.getValue();
            setPlayerSkin(selectedSkin);
        });

        skinChoices.setLayoutX(650);
        skinChoices.setLayoutY(20);

        gamePane.getChildren().add(skinChoices);
    }

// when the drop down options are clicked, one of the cases will show a shape from the shapes class
    private void setPlayerSkin(String skins) {
        switch (skins) {
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

    public void moveLeft() {
        double newX = playerShape.getLayoutX() - xSpeed;
        if (newX >= 0) {
            playerShape.setLayoutX(newX);
        }
    }

    public void moveRight(double w) {
        double newX = playerShape.getLayoutX() + xSpeed;
        double shapeWidth = playerShape.getBoundsInLocal().getWidth();
        if (newX <= w - shapeWidth) {
            playerShape.setLayoutX(newX);
        }
    }

    @Override
    public Shape createPlayerShape() {
        return playerAesthetic.createPlayerShape();
    }
}