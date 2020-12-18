package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;

public class MalePerson extends Person {
    int boo = 1;

    public MalePerson(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, genderEnum);
        HomeSweetHome homeSweetHome = new HomeSweetHome(this.x + GameObject.width, this.y + GameObject.height, model);
        model.addHome(homeSweetHome);

    }

    @Override
    public void update() {
        if (boo == 1) {
            boo++;
        }
    }


    @Override
    public void addChild() {
        throw new RuntimeException("man should give birth");
    }
}
