package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.female_states.WaitHome;

public class FemalePerson extends Person implements HusbandCallbacks {

    private final String tag = "FFFemalePerson";
    public State noteSet = new NotSet();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();

    @Override
    public void addChild() {
        Person child;
        if (Utils.getRandom(0, 2) == 0) {
            child = new FemalePerson.Builder()
                    .setModel(model)
                    .isFirstGeneration(false)
                    .setHome(homeSweetHome)
                    .setGranary(granary)
                    .build();
        } else
            child = new MalePerson.Builder()
                    .setModel(model)
                    .isFirstGeneration(false)
                    .setHome(homeSweetHome)
                    .setGranary(granary)
                    .build();

        model.addChild(child);
//        Log.i(tag, "yraaaa new baby");
    }

//    public FemalePerson(int x, int y, Model model, GenderEnum genderEnum, boolean firstGeneration) {
//        super(x, y, model, genderEnum, List.of(Type.APPLE, Type.CARROT, Type.OAT));
//        if (firstGeneration)
//            currentState = noteSet;
//        else
//            currentState = childhoodState;
//    }

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

    public static final class Builder extends Person.Builder<FemalePerson, FemalePerson.Builder> {
        protected FemalePerson createObject() {
            return new FemalePerson();
        }

        public Builder isFirstGeneration(Boolean firstGeneration) {
            if (firstGeneration)
                object.currentState = object.noteSet;
            else
                object.currentState = object.childhoodState;
            return thisObject;
        }

        protected Builder thisObject() {
            setType(Type.PERSON);
            object.genderEnum = GenderEnum.FEMALE;
            setImage(object.type.getImage(object.genderEnum));
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
