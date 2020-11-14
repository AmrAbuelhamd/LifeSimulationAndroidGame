package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.FantasticColors;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Herbivore implements AnimalSpecie<Species> {
    public static final Herbivore instance = new Herbivore();

    public static Herbivore getInstance() {
        return instance;
    }

    private Herbivore() {
    }

    @Override
    public Species getType() {
        return Species.HERBIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.PLANT;
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
            switch (hungerLevel) {
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
