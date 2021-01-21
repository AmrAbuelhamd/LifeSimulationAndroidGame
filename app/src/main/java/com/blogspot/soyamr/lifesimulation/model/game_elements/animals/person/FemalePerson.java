package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Omnivore;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Raccoon;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.WaitHome;

import java.util.List;

public class FemalePerson extends Person implements HusbandCallbacks {

    private final String tag = "FFFemalePerson";
    public State noteSet = new NotSet();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();

    public static final class Builder extends Person.Builder<FemalePerson, FemalePerson.Builder> {
        protected FemalePerson createObject() {
            return new FemalePerson();
        }
        protected FemalePerson.Builder thisObject() {
            return this;
        }
        public Builder isFirstGeneration(Boolean firstGeneration) {
            if (firstGeneration)
                object.currentState = object.noteSet;
            else
                object.currentState = object.childhoodState;
            return thisObject;
        }
    }

//    public FemalePerson(int x, int y, Model model, GenderEnum genderEnum, boolean firstGeneration) {
//        super(x, y, model, genderEnum, List.of(Type.APPLE, Type.CARROT, Type.OAT));
//        if (firstGeneration)
//            currentState = noteSet;
//        else
//            currentState = childhoodState;
//    }


    @Override
    public void addChild() {
//        Person child;
//        if (Utils.getRandom(0, 2) == 0) {
//            child = new FemalePerson(x, y, model, GenderEnum.FEMALE, false);
//        } else
//            child = new MalePerson(x, y, model, GenderEnum.MALE, false);
//
//        child.setHome(homeSweetHome);
//        child.setGranary(granary);
//        model.addChild(child);
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
        if (isHome() && !iDoNotWant) {
            addChild();
            setIdoNotWant();
            currentState = searchFood;
            return true;
        }
        return false;
    }

    @Override
    public Rect getRect() {
        rect.set(x, y, x + width, y + height);
        return rect;
    }

    @Override
    public void setWifeHome(HomeSweetHome home) {
        homeSweetHome = home;
        setHomeRect();
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        super.drawAdditionalInfo(canvas);
        if (isMarried) {
            homeSweetHome.drawAdditionalInfo(canvas);
        }
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
