package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.blogspot.soyamr.lifesimulation.model.Animal;
import com.blogspot.soyamr.lifesimulation.model.Plant;

import java.util.List;
import java.util.Map;

public interface Controller {

    void update();

    void draw(Canvas canvas);

    void addOnePlant();

    SurfaceHolder getHolder();

    void increaseAnimalsHunger();

    void updateInfo();

    void controlBirthPlease();
}
