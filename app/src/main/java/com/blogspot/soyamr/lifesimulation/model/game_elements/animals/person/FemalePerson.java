package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.provider.ContactsContract;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.GoHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.NoteSet;

import java.util.List;

public class FemalePerson extends Person implements HusbandCallbacks {

    State noteSet = new NoteSet();
    State goHome = new GoHome();

    public FemalePerson(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, genderEnum, List.of(Type.APPLE, Type.CARROT));
        currentState = noteSet;
    }


    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new FemalePerson(x, y, model, GenderEnum.FEMALE));
        else
            model.addChild(new MalePerson(x, y, model, GenderEnum.MALE));
    }


    public HusbandCallbacks wannaBeInRelationShip() {
        if (hunger > SEARCH_FOOD_THRESHOLD) {
            return this;
        } else
            return null;
    }

    @Override
    public void update() {
        if (!checkBeforeUpdate())
            return;

        currentState.update(this);

        afterUpdate();
    }

    @Override
    public boolean wannaMakeLove() {
        if (isHome()) {
            addChild();
            return true;
        }
        return false;
    }

    @Override
    public void setWifeHome(HomeSweetHome home) {
        homeSweetHome = home;
    }
}
