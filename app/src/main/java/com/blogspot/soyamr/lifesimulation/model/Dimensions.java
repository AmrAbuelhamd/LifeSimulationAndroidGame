package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Dimensions {
    public static final int granaryWidth = GameObject.width * 4;//4*400
    public static final int granaryHeight = GameObject.height * 4;
    public static final int explosionWidth = GameObject.width * 20;
    public static final int explosionHeight = GameObject.height * 20;

    public Dimensions() {
    }
}
