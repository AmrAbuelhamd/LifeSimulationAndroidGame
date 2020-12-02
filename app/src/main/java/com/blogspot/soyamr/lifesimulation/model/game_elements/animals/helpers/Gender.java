package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public abstract class Gender {
    public abstract void doCeremony();
    Animal animal;
    public void setIdoNotWant() {
        iDoNotWant = true;
        rsidwv = 0;
    }
    final int reSetIDoNotWantVariable = 150;
    public int rsidwv = 0;
    public boolean iDoNotWant;
    public boolean inRelation = false;

    public Gender(Animal animal) {
        this.animal = animal;
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
}
