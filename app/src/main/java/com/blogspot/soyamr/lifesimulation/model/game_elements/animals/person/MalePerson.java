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

    Status nextMove = Status.NOT_SET;
    @Override
    public void update() {
        if (isAlive) {
            boolean isDead = updateHunger();
            if (isDead) {
//                isAlive = false;
                model.deleteMePlease(this);
                return;
            }
            model.iAmLeavingThisCell(x, y, this);
        }
    }

    void searchPartner(FemalePerson femalePerson) {
        HusbandCallbacks callbacks = femalePerson.wannaBeInRelationShip();
//        femalePerson.

        callbacks.setWifeHome(homeSweetHome);

    }

    @Override
    public void addChild() {
        throw new RuntimeException("man should give birth");
    }

    enum Status {
        NOT_SET, SEARCH_HOME, GO_NEAREST_HOME, WAIT_HOME, WANNA_EAT, ONE_DIRECTION
    }
}
