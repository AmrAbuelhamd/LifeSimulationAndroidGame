package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public interface AnimalSpecie<T> {
    T getType();
    T getFoodType();

    boolean isSuitableFood(GameObject current);
}
