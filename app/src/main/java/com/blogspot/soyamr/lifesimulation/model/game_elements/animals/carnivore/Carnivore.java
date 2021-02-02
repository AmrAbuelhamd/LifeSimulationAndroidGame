package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public abstract class Carnivore extends Animal {
    public Carnivore() {
    }

    protected static abstract class Builder
            <T extends Carnivore, B extends Builder<T, B>> extends Animal.Builder<T, B> {
    }
}
