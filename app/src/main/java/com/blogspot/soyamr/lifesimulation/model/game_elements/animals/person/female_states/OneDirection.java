package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class OneDirection implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = ((FemalePerson) person);
        if (person.mtodth < person.movingToOneDirectionThreshold) {
            ++person.mtodth;
            person.moveToOneDirection();
        } else {
            person.mtodth = 0;
            int rand = Utils.getRandom(0, Person.moveDirection.length);
            person.direction = Person.moveDirection[rand];
            person.moveRandomly();
            if (p.isMarried) {
                p.currentState = p.goingHome;
                return;
            }
            if (p.hunger < Person.SEARCH_FOOD_THRESHOLD) {
                p.currentState = p.searchFood;
            }
        }
    }

    @Override
    public String getStateName() {
        return "One Direction";
    }
}
