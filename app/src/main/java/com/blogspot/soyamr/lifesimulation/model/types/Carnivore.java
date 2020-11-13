package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Carnivore implements AnimalSpecie<Species> {
    public static final Carnivore instance = new Carnivore();

    public static Carnivore getInstance() {
        return instance;
    }

    private Carnivore() {
    }

    @Override
    public Species getType() {
        return Species.CARNIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.HERBIVORE;
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
