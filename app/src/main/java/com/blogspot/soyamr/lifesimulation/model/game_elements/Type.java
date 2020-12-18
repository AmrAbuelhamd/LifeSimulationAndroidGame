package com.blogspot.soyamr.lifesimulation.model.game_elements;

import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
game
animal plant
-
animal
3 types *9
plant *3




 */


public enum Type {
    //PLANTS
    CARROT,
    OAT,
    APPLE,
    //HERBIVORE
    RABBIT,
    MOUSE,
    DEER,
    //CARNIVORE
    FOX,
    WOLF,
    LION,
    //OMNIVORE
    BEAR,
    PIG,
    RACCOON,
    //special types
    PERSON,
    HOME;


    public List<GameObject> getMeFromHere(Model model, int i, int j, GenderEnum genderEnum) {
        List<GameObject> result = new ArrayList<>();
        List<GameObject> objectSOnCell = model.getObjectResidingHere(i, j);

        for (GameObject currentObject : objectSOnCell)
            if (currentObject.isAlive && currentObject.type == this &&
                    (genderEnum == GenderEnum.BOTH || genderEnum == currentObject.genderEnum)) {
                result.add(currentObject);
            }

        return result;
    }


}
