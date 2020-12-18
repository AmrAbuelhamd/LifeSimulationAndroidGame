package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

public abstract class Person extends Animal {
    public Person(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.PERSON, genderEnum,
                List.of(Type.APPLE, Type.RABBIT, Type.PIG,Type.CARROT)
//                List.of(Type.RABBIT, Type.FOX, Type.WOLF, Type.PIG
//                        , Type.MOUSE, Type.BEAR, Type.DEER,
//                        Type.LION, Type.RACCOON,Type.APPLE,Type.CARROT,Type.OAT)
        );
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

}
