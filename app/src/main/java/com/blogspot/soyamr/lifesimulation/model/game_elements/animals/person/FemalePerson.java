package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Rect;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.WaitHome;

import java.util.List;

public class FemalePerson extends Person implements HusbandCallbacks {

    private final String tag = "FFFemalePerson";
    public State noteSet = new NotSet();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();

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
//        Log.i(tag, "yraaaa new baby");
    }


    public HusbandCallbacks wannaBeInRelationShip() {
        if (hunger > SEARCH_FOOD_THRESHOLD) {
            isMarried = true;
            currentState = goingHome;
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
        if (isHome() && !genderOperator.iDoNotWant) {
            addChild();
            genderOperator.setIdoNotWant();
            currentState = searchFood;
            return true;
        }
        return false;
    }

    @Override
    public Rect getRect() {
        return new Rect(x, y, x + width, y + height);
    }

    @Override
    public void setWifeHome(HomeSweetHome home) {
        homeSweetHome = home;
        setHomeRect();
    }

    @Override
    public State getOneDirectionState() {
        return oneDirection;
    }

    @Override
    public State getWaitHomeState() {
        return waitHome;
    }

    @Override
    public State getNotSetState() {
        return noteSet;
    }
}
