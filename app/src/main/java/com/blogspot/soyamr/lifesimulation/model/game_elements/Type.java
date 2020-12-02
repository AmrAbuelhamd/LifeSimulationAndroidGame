package com.blogspot.soyamr.lifesimulation.model.game_elements;

import com.blogspot.soyamr.lifesimulation.model.Model;

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
    PERSON;


    public GameObject getMeFromHere(Model model, int i, int j, GenderEnum genderEnum) {
        GameObject result = null;
        List<GameObject> objectSOnCell = model.getObjectResidingHere(i, j);

        for (GameObject currentObject : objectSOnCell)
            if (currentObject.type == this && (genderEnum == GenderEnum.BOTH || genderEnum == currentObject.genderEnum)) {
                result = currentObject;
                break;
            }

        return result;
    }


}
