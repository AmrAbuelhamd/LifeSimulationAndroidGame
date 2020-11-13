package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Omnivore implements AnimalSpecie<Species> {
    public static final Omnivore instance = new Omnivore();

    public static Omnivore getInstance() {
        return instance;
    }

    private Omnivore() {
    }

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
        return true;
    }

    @Override
    public boolean isSuitableGroom(Species groomType) {
        return groomType == getType();
    }
}
