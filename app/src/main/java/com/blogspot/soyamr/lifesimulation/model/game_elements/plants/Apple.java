package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Apple extends Plant {
    public Apple(int x, int y, Model model) {
        super(x, y, model, Type.APPLE);
    }
}
