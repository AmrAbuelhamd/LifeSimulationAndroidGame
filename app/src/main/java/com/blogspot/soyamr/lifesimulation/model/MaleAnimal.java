package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Utils;

public class MaleAnimal extends Animal {
    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    private FemaleAnimal myLove;


    MaleAnimal(Model model) {
        super(model);
    }

    public MaleAnimal(int x, int y, Model model) {
        super(x, y, model);
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

    @Override
    Paint getPaint() {
        return paint;
    }
}
