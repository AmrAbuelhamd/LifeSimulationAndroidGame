package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public class NotSet implements StateAnimal {
    @Override
    public void update(Animal animal) {
        if (!animal.iDoNotWant && animal.hunger > Animal.SEARCH_PARTNER_THRESHOLD) {
            if (animal.genderEnum == GenderEnum.FEMALE) {
                animal.moveRandomly();
                return;
            }
            animal.currentState = animal.searchPartner;
        } else if (animal.hunger > Animal.SEARCH_FOOD_THRESHOLD) {
            animal.moveRandomly();
        } else {//he is hungry
            animal.currentState = animal.searchFood;
        }


    }

    @Override
    public String getStateName() {
        return "Not Set";
    }
}
