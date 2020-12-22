package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.State;

import java.util.List;

public class SearchFood implements State {
    @Override
    public void update(Person person) {
        FemalePerson p = (FemalePerson) person;
        List<GameObject> foodList = Utils.searchAroundAnimal(Person.ANIMAL_FOOD_VISION_RANG
                , p.getX(), p.getY(), p.model, p.myFoodTypeList, GenderEnum.BOTH);
        if (foodList.isEmpty()) {
            p.currentState = p.oneDirection;
            return;
        }
        p.nearestFood = foodList.get(0);
        p.currentState = p.goingToFood;
    }
}
