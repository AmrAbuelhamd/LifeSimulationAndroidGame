package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class WaitHome implements State {
    @Override
    public void update(Person person) {
        MalePerson p = (MalePerson) person;
        if (p.homeSweetHome.isStockEmpty() && p.granary != null) {
            p.currentState = p.goToGranary;
            return;
        }
        if (p.hunger < Person.SEARCH_PARTNER_THRESHOLD) {
            while (p.hunger != 100) {
                if (p.homeSweetHome.getFood() != null) {
                    p.reduceHunger();
                } else {
                    p.currentState = p.searchFood;
                    break;
                }
            }
        } else if (!p.homeSweetHome.isStockFull()) {
            p.currentState = p.searchFood;
        } else if (!p.iDoNotWant) {//note that if i don't have anything to do, i will just wait home and keep asking for sex
            if (p.wifeCallbacks.wannaMakeLove()) {//if she agrees then she will add child directly, no need to take actions
                p.setIdoNotWant();
                p.currentState = p.searchFood;
            }
        }
    }
    @Override
    public String getStateName() {
        return "Wait Home";
    }
}
