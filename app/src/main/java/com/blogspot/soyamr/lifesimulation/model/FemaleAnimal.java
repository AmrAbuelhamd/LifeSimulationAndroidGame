package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.R;

public class FemaleAnimal extends Animal {


    FemaleAnimal(Model model) {
        super(model);
        setInitialColor();
    }

    public FemaleAnimal(int x, int y, Model model) {
        super(x, y, model);
        setInitialColor();
    }

    private void setInitialColor() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }


    @Override
    public void update() {
        if (!myTurn && !inRelation) {
            moveRandomly();
        } else {
            if (inRelation) {
                waitLoveToArrive();
                return;
            } else if (hunger < SEARCH_FOOD_THRESHOLD) {
                boolean found = needFood();
                if (!found)
                    moveRandomly();
            } else {
                moveRandomly();
            }
        }
        super.update();
    }

    @Override
    public void increaseHunger() {
        if (hunger == 0)
            model.deleteMePlease(this);
        else
            hunger -= 10;
        changeColor();
    }

    @Override
    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }

    private void changeColor() {
        switch (hunger) {
            case 100:
                paint.setColor(Model.context.getColor(R.color.f100));
                break;
            case 80:
                paint.setColor(Model.context.getColor(R.color.f80));
                break;
            case 60:
                paint.setColor(Model.context.getColor(R.color.f60));
                break;
            case 40:
                paint.setColor(Model.context.getColor(R.color.f40));
                break;
            case 20:
                paint.setColor(Model.context.getColor(R.color.f20));
                break;
            case 0:
                paint.setColor(Model.context.getColor(R.color.f0));
                break;
        }
    }

    void waitLoveToArrive() {
    }

    boolean wannaBeInRelationship() {
        if (!iDoNotWant && !inRelation && hunger > SEARCH_FOOD_THRESHOLD) {
            inRelation = true;
            return true;
        } else
            return false;
    }

    public void brokeUp() {
        inRelation = false;
        setIdoNotWant();
    }
}
