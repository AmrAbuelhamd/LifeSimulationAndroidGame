package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Dimensions;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Granary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

import java.util.Random;

public class GoingHome implements State {
    boolean addedFood;

    @Override
    public void update(Person person) {
        if (person.goHome()) {
            if (person.nearestFood != null) {
                addedFood = person.homeSweetHome.addFood(person.nearestFood);
                if (addedFood) {
                    person.nearestFood = null;
                } else {
                    person.currentState = iHaveExtraFoodDecideNextState(person);
                    return;
                }
            }
            person.currentState = person.getWaitHomeState();
        }
    }

    private State iHaveExtraFoodDecideNextState(Person person) {
        if (person.granary == null) {
            Granary granary = person.model.searchForNearGranary(createBigRectOfPersonsVision(person.getX(),person.getY()));
            if (granary == null) {
                int newX = 0;
                int newY = 0;//i will build new one here
                boolean found = false;
                for (int i = 1; i < 50 && !found; i++) {
                    int r = Utils.getRandom(0,Person.moveDirection.length);
                    int[] dir = Person.moveDirection[r];
                    newX = person.getX() + dir[0] * Dimensions.granaryWidth * i;
                    newY = person.getY() + dir[1] * Dimensions.granaryHeight * i;

                    if (newX < 0) {
                        newX = 0;
                    } else if (newX > Const.FIELD_WIDTH - Dimensions.granaryWidth) {
                        newX = Const.FIELD_WIDTH - Dimensions.granaryWidth;
                    }
                    if (newY < 0) {
                        newY = 0;
                    } else if (newY > Const.FIELD_HEIGHT - Dimensions.granaryHeight) {
                        newY = Const.FIELD_HEIGHT - Dimensions.granaryHeight;
                    }

                    if (!person.model.checkIfNoHomesHere(newX, newY, Dimensions.granaryWidth,
                            Dimensions.granaryHeight)) {
                        found = true;
                    }
                }

                if (found) {
                    person.buildGranary(newX, newY);
                    return person.goingToBuildGranary;
                } else {
                    throw new RuntimeException("no place for granary");
//                    return person.getWaitHomeState();//if not found this is a problem, BIG PROBLEM
                }
            } else {
                person.granary = granary;
                return person.goToGranary;
            }
        } else {
            return person.goToGranary;
        }
    }


    private Rect createBigRectOfPersonsVision(int x, int y) {
        Rect result = new Rect();
        result.set(Math.max(x - Person.GRANARY_VISION * GameObject.width, 0),
                Math.max(0, y - Person.GRANARY_VISION * GameObject.height),
                Math.min(Const.FIELD_WIDTH, x + Person.GRANARY_VISION * GameObject.width),
                Math.min(Const.FIELD_HEIGHT, y + Person.GRANARY_VISION * GameObject.height)
        );
        return result;
    }

    @Override
    public String getStateName() {
        return "GoingHome";
    }
}
