package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.GoingToNearHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.SearchPartner;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.WaitHome;

import java.util.List;

public class MalePerson extends Person {

    public final int HOUSE_VISION = ANIMAL_FOOD_VISION_RANG * 2;
    public HusbandCallbacks wifeCallbacks;
    public State goingToNearHome = new GoingToNearHome();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();
    public State noteSet = new NotSet();
    public State searchPartner = new SearchPartner();
    Paint womenColor = new Paint();

    public MalePerson(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, genderEnum, List.of(Type.RABBIT, Type.PIG));
        currentState = noteSet;

        womenColor.setColor(Color.YELLOW);
        womenColor.setStyle(Paint.Style.FILL);
//        womenColor.setStrokeWidth(150);
    }


    @Override
    public void update() {
        if (!checkBeforeUpdate())
            return;

        currentState.update(this);

        afterUpdate();
    }

    @Override
    public void addChild() {
        throw new RuntimeException("man should give birth");
    }

    public void buildHome(int x, int y) {
        homeSweetHome = new HomeSweetHome(x, y, model);
        wifeCallbacks.setWifeHome(homeSweetHome);
        setHomeRect();
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        super.drawAdditionalInfo(canvas);
        if (isMarried) {
            canvas.drawRect(wifeCallbacks.getRect(), womenColor);
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

    public void showMyHome() {
        model.addHome(homeSweetHome);
    }
}