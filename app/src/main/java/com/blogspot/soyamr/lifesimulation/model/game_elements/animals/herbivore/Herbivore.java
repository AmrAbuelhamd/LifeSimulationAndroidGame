package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public abstract class Herbivore extends Animal {

    public Herbivore() {

    }

    protected static abstract class Builder
            <T extends Herbivore, B extends Builder<T, B>> extends Animal.Builder<T, B> {
    }
}
