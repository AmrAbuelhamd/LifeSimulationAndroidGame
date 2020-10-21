package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.Utils;

public class MaleAnimal extends Animal {


    private FemaleAnimal myLove;


    MaleAnimal(Model model) {
        super(model);
        setInitialColor();
    }

    public MaleAnimal(int x, int y, Model model) {
        super(x, y, model);
        setInitialColor();
    }

    void setInitialColor() {
        paint.setColor(Model.context.getColor(R.color.m80));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    public void update() {
        if (!myTurn && !inRelation) {
            moveRandomly();
        } else {
            if (inRelation) {
                if (arrived()) {
                    doCeremony();
                    moveRandomly();
                } else {
                    moveToward(myLove.x, myLove.y);
                }
            } else if (!iDoNotWant && hunger > SEARCH_PARTNER_THRESHOLD) {
                boolean found = searchForPartner();
                if (!found)
                    moveRandomly();
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

    private void doCeremony() {
        model.weHaveChild(x, y);//lidia if two random animals met on the same cell they can't just have children because they were on the sam eon
        myLove.brokeUp();
        myLove = null;
        inRelation = false;
        setIdoNotWant();
    }

    private boolean arrived() {
        boolean arrivedX = false;
        boolean arrivedY = false;
        if (myLove.x == x)
            arrivedX = true;
        if (myLove.y == y)
            arrivedY = true;
        return arrivedX && arrivedY;
    }

    boolean worthSearchingForWomen = true;
    int lastX;
    int lastY;

    private boolean doesItWorthSearching() {
        if (!worthSearchingForWomen) {
            int distance = (int) Math.sqrt((x - lastX) * (x - lastX) + (y - lastY) * (y - lastY));
            return distance >= SEARCH_WOMEN_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH;
        } else {
            return true;
        }
    }

    @Override
    public void increaseHunger() {
        if (hunger == 0)
            model.deleteMePlease(this);
        else
            hunger -= 10;
        changeColor();

    }

    private void changeColor() {
        switch (hunger) {
            case 100:
                paint.setColor(Model.context.getColor(R.color.m100));
                break;
            case 80:
                paint.setColor(Model.context.getColor(R.color.m80));
                break;
            case 60:
                paint.setColor(Model.context.getColor(R.color.m60));
                break;
            case 40:
                paint.setColor(Model.context.getColor(R.color.m40));
                break;
            case 20:
                paint.setColor(Model.context.getColor(R.color.m20));
                break;
            case 0:
                paint.setColor(Model.context.getColor(R.color.m0));
                break;
        }
    }

    @Override
    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }

    private boolean searchForPartner() {
        if (!doesItWorthSearching()) {
            return false;
        }
        FemaleAnimal singleFemaleAnimal = (FemaleAnimal) Utils.searchAroundAnimal(ANIMAL_WOMEN_VISION_RANG,
                x, y, model, Utils.Const.SearchFor.FEMALE_ANIMAL);
        if (singleFemaleAnimal == null) {
            worthSearchingForWomen = false;
            lastX = x;
            lastY = y;
            return false;
        } else {
            inRelation = true;
            myLove = singleFemaleAnimal;
            moveToward(myLove.x, myLove.y);
            worthSearchingForWomen = true;
            return true;
        }
    }
}
