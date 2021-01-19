package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class GoToGranary implements State {
    @Override
    public void update(Person p) {
        p.moveToward(p.granary.getX(), p.granary.getY());
        p.rect.set(p.getX(), p.getY(), p.getX() + Person.width, p.getY() + Person.height);
        if (p.granary.intersects(p.rect)) {
            if (p.nearestFood != null) {
                p.granary.addFood(p.nearestFood);
                p.nearestFood = null;
                p.currentState = p.goingHome;
            } else {//hungry
                if (p.granary.getFood() == null) {
                    p.currentState = p.searchFood;
                } else {
                    int ctr = 0;
                    while (p.hunger != 100 && ++ctr <= 3) {
                        if (p.granary.getFood() != null) {
                            p.reduceHunger();
                        } else {
                            p.currentState = p.searchFood;
                            return;
                        }
                    }
                    p.currentState = p.goingHome;
                }
            }
        }
    }
    @Override
    public String getStateName() {
        return "GoingToGranary";
    }
}
