package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.List;

public class Deer extends Herbivore {

    public Deer(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.DEER, genderEnum,
                List.of(Type.APPLE));
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Deer(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Deer(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -13784;
        else if (hunger > 30)
            return -10929;
        else
            return -8062;
    }
}
