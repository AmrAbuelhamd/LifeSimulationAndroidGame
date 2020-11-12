package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Utils;

public class FemaleAnimal extends Animal {


    FemaleAnimal(Model model) {
        super(model);
        model.putMeHerePlease(x, y, this);
        setInitialColor();
    }

    public FemaleAnimal(int x, int y, Model model) {
        super(x, y, model);
        model.putMeHerePlease(x, y, this);
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
        if(inRelation){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);
            canvas.drawRect(rect,paint);
            setInitialColor();
        }
    }

    @Override
    public void update() {
        model.iAmLeavingThisCell(x, y,this);
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
    protected void changeColor() {
        paint.setColor(model.getMeColor(FantasticColors.TYPE.female, hunger));
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
        if (Utils.getRandom(0, 2) == 0) {
            model.addChild(new FemaleAnimal(x, y,model));
        } else {
            model.addChild(new MaleAnimal(x, y, model));
        }
    }
}
