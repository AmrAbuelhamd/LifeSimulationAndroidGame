package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingHome implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = ((FemalePerson) person);
        if (person.goHome()) {
            if (p.nearestFood != null) {
                p.homeSweetHome.addFood(p.nearestFood);
                p.nearestFood = null;
            }
            p.currentState = p.waitHome;
        }
    }
}
