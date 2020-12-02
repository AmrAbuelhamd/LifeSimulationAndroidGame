package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;

import java.util.List;

public class Bear extends Omnivore{
    public Bear(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.BEAR, genderEnum,
                List.of(Type.OAT, Type.DEER));
    }


    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Bear(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Bear(x, y, model, GenderEnum.FEMALE));
    }
}
