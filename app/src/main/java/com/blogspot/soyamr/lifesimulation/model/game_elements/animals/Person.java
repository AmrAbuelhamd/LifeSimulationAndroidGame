package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Raccoon;

import java.util.List;

public class Person extends Animal {
    public Person(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.PERSON, genderEnum,
                List.of(Type.APPLE, Type.APPLE, Type.RABBIT, Type.PIG));
    }

    @Override
    protected int getMyColor() {
        return -1;
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Person(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Person(x, y, model, GenderEnum.FEMALE));
    }
}
