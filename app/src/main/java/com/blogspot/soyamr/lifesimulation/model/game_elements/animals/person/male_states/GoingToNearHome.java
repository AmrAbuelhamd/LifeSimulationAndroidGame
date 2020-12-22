package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingToNearHome implements State {
    @Override
    public void update(Person person) {
        MalePerson p = (MalePerson) person;
        person.moveToward(p.homeSweetHome.getX(), p.homeSweetHome.getY());
        if (p.getX() == p.homeSweetHome.getX() && p.getY() == p.homeSweetHome.getY()) {
            p.showMyHome();
            p.currentState = p.waitHome;
        }
    }
}
