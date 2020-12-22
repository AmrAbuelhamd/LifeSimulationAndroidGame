package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingToFood implements State {
    @Override
    public void update(Person person) {
        if (!person.nearestFood.isAlive) {
            person.nearestFood = null;
            person.currentState = person.searchFood;
            return;
        }
        person.moveToward(person.nearestFood.getX(), person.nearestFood.getY());
        if (person.getX() == person.nearestFood.getX() && person.getY() == person.nearestFood.getY()) {
            if (person.isMarried) {
                person.model.hideMe(person.nearestFood);
                person.currentState = person.goingHome;
            } else {
                person.reduceHunger();
                person.model.deleteMePlease(person.nearestFood);
                person.nearestFood = null;
                person.moveRandomly();
                person.currentState = person.getNotSetState();
            }
        }
    }
}
