package com.denisemoneek.finalproject;

public interface Levels {
    // used to determine the level
    void setLevel(int level);

    //used to move the invader
    void moveInvader();

    // used to set the speed of the invader
    void setSpeed(int level);
}