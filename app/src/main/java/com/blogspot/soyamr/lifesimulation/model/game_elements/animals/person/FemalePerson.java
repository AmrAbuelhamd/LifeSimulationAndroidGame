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

import java.util.List;

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
                    .setCoordinates(x, y)
                    .isFirstGeneration(false)
                    .setHome(homeSweetHome)
                    .setGranary(granary)
                    .build();
        } else
            child = new MalePerson.Builder()
                    .setModel(model)
                    .isFirstGeneration(false)
                    .setCoordinates(x, y)
                    .setHome(homeSweetHome)
                    .setGranary(granary)
                    .build();

        model.addChild(child);
    }

    public HusbandCallbacks wannaBeInRelationShip() {
        if (!isMarried && hunger > SEARCH_FOOD_THRESHOLD) {
            isMarried = true;
            currentState = goingHome;
            return this;
        } else
            return null;
    }

    @Override
    public boolean wannaMakeLove() {
        if (isHome() && !iDoNotWant && currentState != goingToBuildGranary) {
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

        protected Builder thisObject() {
            setType(Type.PERSON);
            object.genderEnum = GenderEnum.FEMALE;
            setImage(object.type.getImage(object.genderEnum));
            setFoodTypeList(List.of(Type.CARROT, Type.OAT, Type.APPLE));
            return this;
        }
    }
}
