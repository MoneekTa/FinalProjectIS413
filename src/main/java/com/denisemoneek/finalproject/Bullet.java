package com.denisemoneek.finalproject;

public interface Bullet {

    // movement of bullet
    public void moveBullet();

    // where the bullet will be 
    public void setYPosition(double yPosition);
    public void setXposition(double xPosition);

    // where the bullet will end at 
    public long recordY();
    public long recordX();

}
