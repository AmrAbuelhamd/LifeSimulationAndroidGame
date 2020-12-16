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
                List.of(Type.APPLE, Type.RABBIT, Type.PIG,Type.CARROT));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -11684180;
        else if (hunger > 30)
            return -8336444;
        else
            return -5054501;
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Person(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Person(x, y, model, GenderEnum.FEMALE));
    }
}
