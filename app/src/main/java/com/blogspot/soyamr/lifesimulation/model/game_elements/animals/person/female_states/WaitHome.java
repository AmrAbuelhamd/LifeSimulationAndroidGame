package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class WaitHome implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = (FemalePerson) person;
        if(p.homeSweetHome.isStockEmpty() && p.granary != null){
            p.currentState = p.goToGranary;
            return;
        }
        if (p.hunger < Person.SEARCH_FOOD_THRESHOLD) {
            while (p.hunger != 100) {
                if (p.homeSweetHome.getFood() != null) {
                    p.reduceHunger();
                } else {
                    p.currentState = p.searchFood;
                    break;
                }
            }
        } else if (!p.homeSweetHome.isStockFull()) {
            p.currentState = p.searchFood;
        }
    }
}
