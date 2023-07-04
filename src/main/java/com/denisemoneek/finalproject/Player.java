package com.denisemoneek.finalproject;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;
    private ComboBox<String> skinChoices;
    private Pane gamePane;
    // speed of the player
    private int xSpeed = 10;
    private Bullet bullet;


//by default the player is a triangle
    public Player(Pane gamePane) {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
        this.gamePane = gamePane;
        this.bullet = new Bullet(gamePane);
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

// when the drop down options are clicked, one of the cases
    // will show a shape from one of the shapes class
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
// the shape of the player should update after the option has been clicked
        playerShape = playerAesthetic.createPlayerShape();
        updatePlayerShape();
    }

    private void updatePlayerShape() {
        //player placed in the bottom middle of the screen
        playerShape.setLayoutX(380);
        playerShape.setLayoutY(500);
        // remove a player skin that is being used when another skin is selected
        gamePane.getChildren().removeIf(node -> node instanceof Shape);
        // displays the new skin
        gamePane.getChildren().add(playerShape);
    }

// allow player to move left
    public void moveLeft() {
        double newX = playerShape.getLayoutX() - xSpeed;
        if (newX >= 0) {
            playerShape.setLayoutX(newX);
        }
    }
// allow player to move right
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

    public double getXposition(){
        double xPosition;
        xPosition = playerShape.getLayoutX();
        return xPosition;
    };
    public double getYposition(){
        double yPosition;
        yPosition = playerShape.getLayoutY();
        return yPosition;
    };
}