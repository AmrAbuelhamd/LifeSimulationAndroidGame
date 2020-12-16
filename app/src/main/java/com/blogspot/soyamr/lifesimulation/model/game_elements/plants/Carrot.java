package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Carrot extends Plant {
    public Carrot(int x, int y, Model model) {
        super(x, y, model, Type.CARROT);
    }


    @Override
    public int getMyColor() {
        return -1683200;
    }
}
