package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class NotSet implements State {
    @Override
    public void update(Person person) {
        MalePerson p = (MalePerson) person;//toask is this good practice
        p.homeSweetHome = null;
        p.wifeCallbacks = null;
        if (person.hunger < Person.SEARCH_FOOD_THRESHOLD) {
            p.currentState = p.searchFood;
        } else if (person.hunger > Person.SEARCH_PARTNER_THRESHOLD && !person.genderOperator.iDoNotWant) {
            p.currentState = p.searchPartner;
        } else {
            p.moveRandomly();
        }
    }
}
