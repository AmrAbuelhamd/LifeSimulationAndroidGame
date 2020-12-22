package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingToFood implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = (FemalePerson) person;
        if (!p.nearestFood.isAlive) {
            p.nearestFood = null;
            p.currentState = p.searchFood;
            return;
        }
        p.moveToward(p.nearestFood.getX(), p.nearestFood.getY());
        if (p.getX() == p.nearestFood.getX() && p.getY() == p.nearestFood.getY()) {
            if (p.isMarried) {
                p.model.hideMe(p.nearestFood);
                p.currentState = p.goingHome;
            } else {
                p.reduceHunger();
                p.model.deleteMePlease(p.nearestFood);
                p.nearestFood = null;
                p.moveRandomly();
                p.currentState = p.noteSet;
            }
        }
    }
}
