package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.model.FantasticColors;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.types.AnimalSpecie;
import com.blogspot.soyamr.lifesimulation.model.types.Species;

public class FemaleAnimal<T extends AnimalSpecie<Species>> extends Animal {

    T mySpecie;

    public FemaleAnimal(int x, int y, Model model, T myType) {
        super(x, y, model, myType.getFoodType());
        setInitialData(myType);
    }

    void setInitialData(T myType) {
        this.mySpecie = myType;
        setInitialColor();
    }

    private void setInitialColor() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
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
    public void update() {
        model.iAmLeavingThisCell(x, y, this);
        if (!myTurn && !inRelation) {
            moveRandomly();
        } else {
            if (inRelation) {
                waitLoveToArrive();
                return;
            } else if (hunger < SEARCH_FOOD_THRESHOLD) {
                boolean found = foundFood();
                if (!found)
                    moveRandomly();
            } else {
                moveRandomly();
            }
        }
        super.update();
    }

    @Override
    protected boolean isSuitableFood(GameObject current) {
        return mySpecie.isSuitableFood(current);
    }


    @Override
    protected void changeColor() {
        paint.setColor(model.getMeColor(FantasticColors.TYPE.female, hunger));
    }

    @Override
    protected Species getMyType() {
        return mySpecie.getType();
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

    public void marriage() {
        model.addChild(model.generator.createRandomAnimal(x,y));
    }
}
