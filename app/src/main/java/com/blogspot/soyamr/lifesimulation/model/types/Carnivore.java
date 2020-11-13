package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.model.game_elements.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Carnivore implements AnimalSpecie<Species> {
    @Override
    public Species getType() {
        return Species.CARNIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.ANIMAL;
    }

    @Override
    public boolean isSuitableFood(GameObject current) {
        return ((Animal) current).myFoodType == Species.PLANT;
    }
}
