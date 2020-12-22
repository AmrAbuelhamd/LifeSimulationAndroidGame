package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

import java.util.List;

public class SearchFood implements State {
    @Override
    public void update(Person person) {
        List<GameObject> foodList = Utils.searchAroundAnimal(Person.ANIMAL_FOOD_VISION_RANG
                , person.getX(), person.getY(), person.model, person.myFoodTypeList, GenderEnum.BOTH);
        if (foodList.isEmpty()) {
            person.currentState = person.getOneDirectionState();
            return;
        }
        person.nearestFood = foodList.get(0);
        person.currentState = person.goingToFood;
    }
}
