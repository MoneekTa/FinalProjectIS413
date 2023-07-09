package com.denisemoneek.finalproject;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;
    private Pane gamePane;
    private Bullet bullet;
    // speed of the player
    private int speed = 10;

    private boolean gamePaused = false; // Added gamePaused variable

    // By default, the player is a triangle
    public Player(Pane gamePane) {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
        this.gamePane = gamePane;
        this.bullet = new Bullet(gamePane);
        updatePlayerShape();
    }

    private void updatePlayerShape() {
        // Player placed in the bottom middle of the screen
        playerShape.setLayoutX(380);
        playerShape.setLayoutY(500);
        // Remove any existing player shape
        gamePane.getChildren().removeIf(node -> node instanceof Shape);
        // Display the player shape
        gamePane.getChildren().add(playerShape);
    }

    // Allow player to move left only when the game is not paused
    public void moveLeft() {
        if (!gamePaused) {
            double newX = playerShape.getLayoutX() - speed;
            if (newX >= 0) {
                playerShape.setLayoutX(newX);
            }
        }
    }

    // Allow player to move right only when the game is not paused
    public void moveRight(double w) {
        if (!gamePaused) {
            double newX = playerShape.getLayoutX() + speed;
            double shapeWidth = playerShape.getBoundsInLocal().getWidth();
            if (newX <= w - shapeWidth) {
                playerShape.setLayoutX(newX);
            }
        }
    }

    @Override
    public Shape createPlayerShape() {
        return playerAesthetic.createPlayerShape();
    }

    public double getXposition() {
        return playerShape.getLayoutX();
    }

    public double getYposition() {
        return playerShape.getLayoutY();
    }
}