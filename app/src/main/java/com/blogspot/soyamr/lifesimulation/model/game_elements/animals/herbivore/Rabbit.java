package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Fox;

import java.util.List;

public class Rabbit extends Herbivore{

    public Rabbit(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.RABBIT, genderEnum,
                List.of(Type.CARROT));
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Rabbit(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Rabbit(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -749647;
        else if (hunger > 30)
            return -476208;
        else
            return -203540;
    }
}
