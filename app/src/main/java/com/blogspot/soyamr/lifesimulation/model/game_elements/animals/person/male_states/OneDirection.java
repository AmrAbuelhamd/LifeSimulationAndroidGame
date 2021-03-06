package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class OneDirection implements State {
    int[] prev = Person.moveDirection[0];

    @Override
    public void update(Person person) {
        MalePerson p = ((MalePerson) person);
        if (person.moveToOneDirectionCTR < person.movingToOneDirectionThreshold) {
            ++person.moveToOneDirectionCTR;
            person.moveToOneDirection();
        } else {
            person.moveToOneDirectionCTR = 0;
            int rand;
            do {
                rand = Utils.getRandom(0, Person.moveDirection.length);
            } while (Person.moveDirection[rand] == prev);
            prev = Person.moveDirection[rand];
            person.direction = prev;
            person.moveRandomly();
            if (p.isMarried) {
                if(p.hunger<60){
                    p.currentState = p.goingHome;
                }else {
                    p.currentState = p.searchFood;
                }
                return;
            }
            if (p.hunger < Person.SEARCH_FOOD_THRESHOLD) {
                p.currentState = p.searchFood;
            } else {
                p.currentState = p.searchPartner;
            }
        }
    }
    @Override
    public String getStateName() {
        return "One Direction";
    }
}
