package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingHome implements State {
    @Override
    public void update(Person person) {
        if (person.goHome()) {
            if (person.nearestFood != null) {
                person.homeSweetHome.addFood(person.nearestFood);
                person.nearestFood = null;
            }
            person.currentState = person.getWaitHomeState();
        }
    }
}
