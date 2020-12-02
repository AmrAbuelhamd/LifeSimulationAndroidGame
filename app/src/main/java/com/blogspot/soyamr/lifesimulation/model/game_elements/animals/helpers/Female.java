package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.SEARCH_FOOD_THRESHOLD;

public class Female extends Gender {
    @Override
    public void doCeremony() {
        marriage();
        brokeUp();
    }

    public Female(Animal animal) {
        super(animal);

    }

    @Override
    public boolean wannaBeInRelationShip(Type groomType) {
        if (!iDoNotWant && !inRelation && animal.hunger > SEARCH_FOOD_THRESHOLD) {
            if (animal.type == groomType) {
                inRelation = true;
                animal.paint.setStyle(Paint.Style.STROKE);
                animal.paint.setStrokeWidth(10);
                return true;
            }
        }
        return false;
    }

    public void brokeUp() {
        inRelation = false;
        animal.paint.setStyle(Paint.Style.FILL);
        setIdoNotWant();
    }

    public void marriage() {
        animal.addChild();
    }

    void waitLoveToArrive() {
    }

    @Override
    public void takeRequiredActions() {
        waitLoveToArrive();
    }

    @Override
    public boolean searchForPartner() {
        return false;
    }
}
