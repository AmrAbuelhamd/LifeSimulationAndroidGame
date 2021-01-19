package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class NotSet implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = (FemalePerson) person;
        p.homeSweetHome = null;
        if (person.hunger < Person.SEARCH_FOOD_THRESHOLD) {
            p.currentState = p.searchFood;
        } else {
            p.moveRandomly();
        }
    }

    @Override
    public String getStateName() {
        return "Not Set";
    }
}
