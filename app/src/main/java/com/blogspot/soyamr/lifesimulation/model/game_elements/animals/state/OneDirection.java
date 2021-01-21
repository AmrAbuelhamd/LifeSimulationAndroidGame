package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public class OneDirection implements StateAnimal {
    @Override
    public void update(Animal animal) {
        if (animal.mtodth < animal.movingToOneDirectionThreshold) {
            ++animal.mtodth;
            animal.moveToOneDirection();
        } else {
            animal.mtodth = 0;
            int rand = Utils.getRandom(0, Animal.moveDirection.length);
            animal.direction = Animal.moveDirection[rand];
            animal.currentState = animal.notSet;
        }
    }

    @Override
    public String getStateName() {
        return "one direction";
    }
}
