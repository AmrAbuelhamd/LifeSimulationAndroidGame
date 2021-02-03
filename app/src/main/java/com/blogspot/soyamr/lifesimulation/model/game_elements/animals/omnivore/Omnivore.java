package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public abstract class Omnivore extends Animal {

    public Omnivore() {
    }

    protected static abstract class Builder
            <T extends Omnivore, B extends Builder<T, B>> extends Animal.Builder<T, B> {
    }

}
