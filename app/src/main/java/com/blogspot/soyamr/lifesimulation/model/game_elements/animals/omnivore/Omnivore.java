package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.FantasticColors;

import java.util.List;

public abstract class Omnivore extends Animal {

    public Omnivore(int x, int y, Model model, Type myType, GenderEnum genderEnum, List<Type> myFoodTypeList) {
        super(x, y, model, myType, genderEnum, myFoodTypeList);
    }

    @Override
    public int getMyColor() {
        if (genderEnum == GenderEnum.MALE)
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.mo100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.mo80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.mo60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.mo40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.mo20);
                default:
                    return FantasticColors.context.getColor(R.color.mo0);
            }
        else {
            switch (hunger) {
                case 100:
                case 90:
                    return FantasticColors.context.getColor(R.color.fo100);
                case 80:
                case 70:
                    return FantasticColors.context.getColor(R.color.fo80);
                case 60:
                case 50:
                    return FantasticColors.context.getColor(R.color.fo60);
                case 40:
                case 30:
                    return FantasticColors.context.getColor(R.color.fo40);
                case 20:
                case 10:
                    return FantasticColors.context.getColor(R.color.fo20);
                default:
                    return FantasticColors.context.getColor(R.color.fo0);
            }
        }

    }
}
