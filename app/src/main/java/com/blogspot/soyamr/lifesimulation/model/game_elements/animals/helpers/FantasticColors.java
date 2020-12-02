package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.content.Context;

import com.blogspot.soyamr.lifesimulation.R;

public class FantasticColors {
    public enum TYPE {
        female, male, plant
    }

    public static Context context = null;

    public FantasticColors(Context context) {
        this.context = context;
    }

    public int getColor(TYPE type, int level) {
        switch (type) {
            case female:
                return getFemaleColor(level);
            case male:
                return getMaleColor(level);
            case plant:
                return getPlantColor();
            default:
                return 0;//todo return strange color to know that something went wrong
        }
    }

    private int getPlantColor() {
        return context.getColor(R.color.plant);
    }

    private int getMaleColor(int level) {return 0;}

    private int getFemaleColor(int level) {return 0;}


}
