package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public class SearchFood implements StateAnimal {
    @Override
    public void update(Animal animal) {
        if (animal.myFoodMenu.isEmpty())
            animal.myFoodMenu = Utils.searchAroundAnimal(
                    Animal.ANIMAL_FOOD_VISION_RANG,
                    animal.getX(), animal.getY(),
                    animal.model, animal.myFoodTypeList, GenderEnum.BOTH);
        if (animal.myFoodMenu.isEmpty()) {
            animal.currentState = animal.oneDirection;
            return;
        }
        //make sure that food that i kept in my list still available before going towards it
        //if not delete it
        GameObject target = animal.getNextTarget();

        if (target == null) {
//            return Animal.NextMove.MOVE_RANDOMLY;
            animal.currentState = animal.oneDirection;
            return;
        }

        //move the ANIMAL towards the food
        animal.myFood = target;
        animal.currentState = animal.goingToFood;
    }

    @Override
    public String getStateName() {
        return "Search Food";
    }
}
