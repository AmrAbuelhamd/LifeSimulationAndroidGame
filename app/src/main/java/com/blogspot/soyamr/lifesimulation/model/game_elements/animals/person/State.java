package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;

public interface State {

    void update(Person person);
    String getStateName();
}
