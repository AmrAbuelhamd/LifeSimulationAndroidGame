package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoingToBuildGranary implements State {
    @Override
    public void update(Person p) {
        p.moveToward(p.granary.getX(), p.granary.getY());
        p.rect.set(p.getX(), p.getY(), p.getX() + Person.width, p.getY() + Person.height);
        if (p.granary.intersects(p.rect)) {
            p.granary.isAlive = true;
            p.currentState = p.goToGranary;
        }
    }
    @Override
    public String getStateName() {
        return "GoingToBuildGranary";
    }
}
