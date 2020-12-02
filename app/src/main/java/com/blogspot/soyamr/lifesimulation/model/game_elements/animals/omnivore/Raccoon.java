package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;

import java.util.List;

public class Raccoon extends Omnivore {

    public Raccoon(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.RACCOON, genderEnum,
                List.of(Type.RABBIT, Type.OAT));
    }
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Raccoon(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Raccoon(x, y, model, GenderEnum.FEMALE));
    }
}
