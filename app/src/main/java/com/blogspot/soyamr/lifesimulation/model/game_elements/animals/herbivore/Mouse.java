package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Fox;

import java.util.List;

public class Mouse extends Herbivore{

    public Mouse(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.MOUSE, genderEnum,
                List.of(Type.OAT));
    }
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Mouse(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Mouse(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -4342339;
        else if (hunger > 30)
            return -2039584;
        else
            return -1118482;
    }
}
