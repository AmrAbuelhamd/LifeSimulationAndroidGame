package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;

public class FemalePerson extends Person implements HusbandCallbacks {
    public FemalePerson(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, genderEnum);
    }


    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new FemalePerson(x, y, model, GenderEnum.FEMALE));
        else
            model.addChild(new MalePerson(x, y, model, GenderEnum.MALE));
    }


    HusbandCallbacks wannaBeInRelationShip() {

        return this;
    }

    @Override
    public boolean wannaMakeLove() {
        return false;
    }

    @Override
    public void setWifeHome(HomeSweetHome home) {
        homeSweetHome = home;
    }
}
