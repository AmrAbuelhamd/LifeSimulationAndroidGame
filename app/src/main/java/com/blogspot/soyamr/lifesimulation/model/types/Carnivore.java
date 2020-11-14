package com.blogspot.soyamr.lifesimulation.model.types;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.model.FantasticColors;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Carnivore implements AnimalSpecie<Species> {
    public static final Carnivore instance = new Carnivore();

    private Carnivore() {
    }

    public static Carnivore getInstance() {
        return instance;
    }

    @Override
    public Species getType() {
        return Species.CARNIVORE;
    }

    @Override
    public Species getFoodType() {
        return Species.HERBIVORE;
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
            switch (hungerLevel) {
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
