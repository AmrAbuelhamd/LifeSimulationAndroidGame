package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(int x, int y, Model model, Type myType, GenderEnum genderEnum, List<Type> myFoodTypeList) {
        super(x, y, model, myType, genderEnum, myFoodTypeList);
    }
}
