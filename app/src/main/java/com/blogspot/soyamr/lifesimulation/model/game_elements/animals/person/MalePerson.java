package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.GoingHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.GoingToFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.GoingToNearHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.SearchFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.SearchPartner;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.male_states.WaitHome;

import java.util.List;

public class MalePerson extends Person {

    public final int HOUSE_VISION = ANIMAL_FOOD_VISION_RANG * 2;
    public HomeSweetHome nearestHome;
    public GameObject nearestFood;
    public HusbandCallbacks wifeCallbacks;
    public boolean isMarried = false;
    public State goingToNearHome = new GoingToNearHome();
    public State oneDirection = new OneDirection();
    public State waitHome = new WaitHome();
    public State goingToFood = new GoingToFood();
    public State noteSet = new NotSet();
    public State searchFood = new SearchFood();
    public State searchPartner = new SearchPartner();
    public State goingHome = new GoingHome();


    public MalePerson(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, genderEnum, List.of(Type.RABBIT, Type.PIG));
        homePaint.setStyle(Paint.Style.STROKE);
        homePaint.setColor(Color.YELLOW);
        homePaint.setTextSize(100F);
        currentState = noteSet;
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
        homeRect.set(x - width / 2, y - height / 2,
                x + width + width / 2, y + height + height / 2);
    }

    public void showMyHome() {
        model.addHome(homeSweetHome);
        wifeCallbacks.setWifeHome(homeSweetHome);
    }
}
