package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Herbivore implements AnimalSpecie<Species> {
    public static final Herbivore instance = new Herbivore();

    public static Herbivore getInstance() {
        return instance;
    }

    private Herbivore() {
    }

    @Override
    public Species getType() {
        return Species.HERBIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.PLANT;
    }

    @Override
    public boolean isSuitableFood(GameObject current) {
        return true;
    }
}
