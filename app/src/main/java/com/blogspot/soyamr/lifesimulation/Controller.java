package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.List;
import java.util.Map;

public interface Controller {
    List<Animal> getAnimals();

    Map<String, Plant> getPlants();

    void update();

    void draw(Canvas canvas);

    void addOnePlant();

    SurfaceHolder getHolder();

    void increaseAnimalsHunger();

    void updateInfo();
}
