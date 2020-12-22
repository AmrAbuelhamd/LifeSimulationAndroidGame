package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public abstract class Gender {
    public int rsidwv = 0;
    public boolean iDoNotWant;
    public boolean inRelation = false;
    public Animal myLove;
    int reSetIDoNotWantVariable = 300;
    Animal animal;

    public Gender(Animal animal) {
        this.animal = animal;
        reSetIDoNotWantVariable = Utils.getRandom(250, 350);//550 750
    }

    public abstract void doCeremony();

    public void setIdoNotWant() {
        iDoNotWant = true;
        rsidwv = 0;//todo rename
    }

    public void updateIDoNotWant() {
        if (iDoNotWant) {
            if (rsidwv < reSetIDoNotWantVariable) {
                ++rsidwv;
            } else {
                iDoNotWant = false;
                rsidwv = 0;
            }
        }
    }


    public abstract Animal.NextMove takeRequiredActions();

    public abstract boolean wannaBeInRelationShip(Type type);

    public abstract Animal.NextMove searchForPartner();

    public abstract void draw(Canvas canvas);

    public abstract void setRect();
}
