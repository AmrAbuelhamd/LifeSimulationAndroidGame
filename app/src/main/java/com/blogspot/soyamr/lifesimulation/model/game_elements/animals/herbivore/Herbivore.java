package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.FantasticColors;

import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(int x, int y, Model model, Type myType, GenderEnum genderEnum, List<Type> myFoodTypeList) {
        super(x, y, model, myType, genderEnum, myFoodTypeList);
    }

    @Override
    public int getMyColor() {
        if (genderEnum == GenderEnum.MALE)
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.mh100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.mh80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.mh60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.mh40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.mh20);
                default:
                    return FantasticColors.context.getColor(R.color.mh0);
            }
        else {
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.fh100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.fh80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.fh60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.fh40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.fh20);
                default:
                    return FantasticColors.context.getColor(R.color.fh0);
            }
        }

    }

}
