package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
        paint.setColor(model.getMeColor(FantasticColors.TYPE.male, hunger));
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
        model.weHaveChild(x, y);
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
            int distance = Math.abs(x - lastX) + Math.abs(y - lastY);
            return distance >= (SEARCH_WOMEN_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH);
        } else {
            return true;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (inRelation) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);
            canvas.drawRect(rect, paint);
            setInitialColor();
        }
    }

    @Override
    protected void changeColor() {
        paint.setColor(model.getMeColor(FantasticColors.TYPE.male, hunger));
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
