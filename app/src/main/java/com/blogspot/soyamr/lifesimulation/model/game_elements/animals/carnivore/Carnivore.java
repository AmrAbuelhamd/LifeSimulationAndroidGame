package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.FantasticColors;

import java.util.List;

public abstract class Carnivore extends Animal {


    public Carnivore(int x, int y, Model model, Type myType, GenderEnum genderEnum, List<Type> myFoodTypeList) {
        super(x, y, model, myType, genderEnum, myFoodTypeList);
    }


    @Override
    public int getMyColor() {
        if (genderEnum == GenderEnum.MALE)
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.mc100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.mc80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.mc60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.mc40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.mc20);
                default:
                    return FantasticColors.context.getColor(R.color.mc0);
            }
        else {
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.fc100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.fc80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.fc60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.fc40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.fc20);
                default:
                    return FantasticColors.context.getColor(R.color.fc0);
            }
        }
    }

}
