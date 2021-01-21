package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class NotSet implements StateAnimal {
    @Override
    public void update(Animal animal) {
//        if (animal.hunger < Animal.SEARCH_FOOD_THRESHOLD) {
//            animal.currentState = animal.searchFood;
//        }if (genderOperator.inRelation) {
//            return genderOperator.takeRequiredActions();
//        }else if (animal.hunger > Animal.SEARCH_PARTNER_THRESHOLD && !animal.genderOperator.iDoNotWant) {
//            animal.currentState = animal.searchPartner;
//        } else {
//            animal.moveRandomly();
//        }
    }

    @Override
    public String getStateName() {
        return "Not Set";
    }
}
