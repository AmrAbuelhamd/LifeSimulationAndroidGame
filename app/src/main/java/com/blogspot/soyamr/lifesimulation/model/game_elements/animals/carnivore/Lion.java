package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.List;

public class Lion  extends Carnivore{
    public Lion(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.LION, genderEnum,
                List.of(Type.DEER));
    }
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Lion(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Lion(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -8227049;
        else if (hunger > 30)
            return -6382300;
        else
            return -5262293;
    }
}
