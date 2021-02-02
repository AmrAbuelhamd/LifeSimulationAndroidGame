package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Carnivore;

import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore() {

    }
    protected static abstract class Builder
            <T extends Herbivore, B extends Builder<T, B>> extends Animal.Builder<T, B> {
    }
}
