package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public abstract class Gender {
    final int reSetIDoNotWantVariable = 300;
    public int rsidwv = 0;
    public boolean iDoNotWant;
    public boolean inRelation = false;
    Animal animal;
    public Gender(Animal animal) {
        this.animal = animal;
    }

    public abstract void doCeremony();

    public void setIdoNotWant() {
        iDoNotWant = true;
        rsidwv = 0;//todo rename
    }

    public void updateIDoNotWant() {
        if (iDoNotWant == true) {
            if (rsidwv < reSetIDoNotWantVariable) {
                ++rsidwv;
            } else {
                iDoNotWant = false;
                rsidwv = 0;
            }
        }
    }


    public abstract void takeRequiredActions();

    public abstract boolean wannaBeInRelationShip(Type type);

    public abstract boolean searchForPartner();

    public abstract void draw(Canvas canvas);

    public abstract void setRect();
}
