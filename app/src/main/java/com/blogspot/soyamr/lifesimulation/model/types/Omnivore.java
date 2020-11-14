package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.FantasticColors;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Omnivore implements AnimalSpecie<Species> {
    public static final Omnivore instance = new Omnivore();

    public static Omnivore getInstance() {
        return instance;
    }

    private Omnivore() {
    }

    @Override
    public Species getType() {
        return Species.OMNIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.BOTH;
    }

    @Override
    public boolean isSuitableFood(GameObject current) {
        return true;
    }

    @Override
    public boolean isSuitableGroom(Species groomType) {
        return groomType == getType();
    }

    @Override
    public int getMyColor(int hungerLevel, Species type) {
        if (type == Species.MALE_ANIMAL)
            switch (hungerLevel) {
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
            switch (hungerLevel) {
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
