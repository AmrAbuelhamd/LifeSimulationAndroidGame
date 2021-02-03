package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Granary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

public class ChildHood implements State {
    int childhoodThreshold = 2400;
    int ctr = 0;
    State goHome = new State() {
        @Override
        public void update(Person person) {
            if (person.goHome()) {
                currentMiniState = waitHome;
            }
        }

        @Override
        public String getStateName() {
            return "Go Home";
        }
    };

    State goToGranary = new State() {
        @Override
        public void update(Person p) {
            p.moveToward(p.granary.getX(), p.granary.getY());
            p.rect.set(p.getX(), p.getY(), p.getX() + Person.width, p.getY() + Person.height);
            if (p.granary.intersects(p.rect)) {
                int ctr = 0;
                while (p.hunger != 100 && ++ctr <= 3) {
                    if (p.granary.getFood() != null) {
                        p.reduceHunger();
                    } else {
                        currentMiniState = goHome;
                        return;
                    }
                }
                currentMiniState = goHome;
            }
        }

        @Override
        public String getStateName() {
            return "GoToGranary";
        }
    };
    State waitHome = new State() {
        @Override
        public void update(Person p) {
            if (p.hunger < Person.SEARCH_FOOD_THRESHOLD) {
                int i = 0;
                while (p.hunger != 100 && ++i < 3) {
                    if (p.homeSweetHome.getFood() != null) {
                        p.reduceHunger();
                    } else {
                        Granary granary;
                        if (p.granary == null) {
                            granary = p.model.searchForNearGranary(
                                    Utils.surroundThisPointWithRect(p.getX(), p.getY(),
                                            Person.GRANARY_VISION * GameObject.width,
                                            Person.GRANARY_VISION * GameObject.height)
                            );
                            if (granary != null) {
                                p.granary = granary;
                                currentMiniState = goToGranary;
                            }
                        } else {
                            currentMiniState = goToGranary;
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public String getStateName() {
            return "Wait Home";
        }
    };
    State currentMiniState = waitHome;

    @Override
    public void update(Person person) {
        if (++ctr < childhoodThreshold) {
            currentMiniState.update(person);
        } else {
            person.currentState = person.getNotSetState();
        }
    }

    @Override
    public String getStateName() {
        return ctr + " ChildHood+" + currentMiniState.getStateName();
    }
}
