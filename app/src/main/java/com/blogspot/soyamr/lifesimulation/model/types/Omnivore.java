package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Omnivore implements AnimalSpecie<Species> {
    @Override
    public Species getType() {
        return Species.OMNIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.BOTH;
    }

    @Override
    public boolean isSuitableFood(GameObject current) {
        if (current instanceof Animal) {
            return ((Animal) current).myFoodType != Species.BOTH;
        }
        return true;
    }
}
