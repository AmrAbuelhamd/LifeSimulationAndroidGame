package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public class InMarriageProcess implements StateAnimal {

    @Override
    public void update(Animal animal) {
        if (animal.genderEnum == GenderEnum.MALE) {
            if (!animal.myLove.isAlive) {
                animal.currentState = animal.notSet;
                animal.currentState = animal.notSet;
                animal.myLove = null;
                return;
            }
            int x = animal.myLove.getX();
            int y = animal.myLove.getY();
            animal.moveToward(x, y);
            if (animal.getX() == x && animal.getY() == y) {
                animal.myLove.doCeremony();
                animal.currentState = animal.notSet;
            }
        } else if (animal.genderEnum == GenderEnum.FEMALE) {
            //todo add reference to him to know if he is still alive or not
            waitLoveToArrive();
        }
    }

    private void waitLoveToArrive() {
    }

    @Override
    public String getStateName() {
        return "in marriage process";
    }
}
