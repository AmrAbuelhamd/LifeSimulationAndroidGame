package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public interface StateAnimal {
    void update(Animal animal);

    String getStateName();
}
