package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
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
        final Rect result = new Rect();

        @Override
        public void update(Person p) {
            if (p.hunger < Person.SEARCH_FOOD_THRESHOLD) {
                int i = 0;
                while (p.hunger != 100 && ++i < 3) {
                    if (p.homeSweetHome.getFood() != null) {
                        p.reduceHunger();
                    } else {
                        Granary granary;
                        if(p.granary==null) {
                            granary = p.model.searchForNearGranary(createBigRectOfPersonsVision(p.getX(), p.getY()));
                            if (granary != null) {
                                p.granary = granary;
                                currentMiniState = goToGranary;
                            }
                        }else{
                            currentMiniState = goToGranary;
                        }
                        break;
                    }
                }
            }
        }

        private Rect createBigRectOfPersonsVision(int x, int y) {
            result.set(Math.max(x - Person.GRANARY_VISION * GameObject.width, 0),
                    Math.max(0, y - Person.GRANARY_VISION * GameObject.height),
                    Math.min(Const.FIELD_WIDTH, x + Person.GRANARY_VISION * GameObject.width),
                    Math.min(Const.FIELD_HEIGHT, y + Person.GRANARY_VISION * GameObject.height)
            );
            return result;
        }

        @Override
        public String getStateName() {
            return "Wait Home";
        }
    };

    @Override
    public void update(Person person) {
        if (++ctr < childhoodThreshold) {
            currentMiniState.update(person);
        } else {
            person.currentState = person.getNotSetState();
        }
    }
    State currentMiniState = waitHome;

    @Override
    public String getStateName() {
        return ctr + " ChildHood+" + currentMiniState.getStateName();
    }
}
