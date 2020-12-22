package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingHome implements State {
    @Override
    public void update(Person person) {
        MalePerson p = ((MalePerson) person);
        if (person.goHome()) {
            if (p.nearestFood != null) {
                p.homeSweetHome.addFood(p.nearestFood);
                p.nearestFood = null;
            }
            p.currentState = p.waitHome;
        }
    }
}
