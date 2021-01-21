package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;

public interface StateAnimal {
    void update(Animal animal);

    String getStateName();
}
