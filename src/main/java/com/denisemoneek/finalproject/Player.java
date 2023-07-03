package com.denisemoneek.finalproject;

import javafx.scene.shape.Shape;
public class Player implements PlayerAesthetic {
    private PlayerAesthetic playerAesthetic;
    private Shape playerShape;

    public Player() {
        this.playerAesthetic = new TriangleSkin();
        this.playerShape = playerAesthetic.createPlayerShape();
    }

    public void setPlayerAesthetic(PlayerAesthetic playerAesthetic) {
        this.playerAesthetic = playerAesthetic;
        this.playerShape = playerAesthetic.createPlayerShape();
    }

    public Shape getPlayerShape() {
        return playerShape;
    }

    @Override
    public Shape createPlayerShape() {
        return playerAesthetic.createPlayerShape();
    }
}