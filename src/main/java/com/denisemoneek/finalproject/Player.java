package com.denisemoneek.finalproject;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;
    private Pane gamePane;
    private PlayerBullet playerBullet;
    // speed of the player
    private int speed = 10;
    int level;
    int hearts;
    private boolean gamePaused = false; // Added gamePaused variable

    // By default, the player is a triangle
    public Player(Pane gamePane) {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
        this.gamePane = gamePane;
        this.playerBullet = new PlayerBullet(gamePane);
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
    public void setLevel(int level) {
        this.level = level;
        // Update invader properties based on the level
        switch (level) {
            case 1:
                hearts = 5;
                break;
            case 2:
                hearts = 2;
                break;
            case 3:
                hearts = 1;
                break;
            default:
                break;
        }
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

    // used to display the shape of the player
    @Override
    public Shape createPlayerShape() {
        return playerAesthetic.createPlayerShape();
    }

    // gets the x position of the player
    public double getXposition() {
        return playerShape.getLayoutX();
    }

    // gets the y position of the player
    public double getYposition() {
        return playerShape.getLayoutY();
    }
    //will be used to display the level the player is on
    public int getLevel(){
        return level;
    }

    // used to display the health of the invader
    public int getHealth(){
        return hearts;}
}
