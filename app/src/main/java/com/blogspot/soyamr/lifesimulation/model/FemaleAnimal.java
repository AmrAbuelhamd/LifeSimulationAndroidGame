package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
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
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(inRelation){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.WHITE);
            canvas.drawRect(rect,paint);
            setInitialColor();
        }
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
    protected void changeColor() {
        switch (hunger) {
            case 100:
            case 90:
                paint.setColor(Model.context.getColor(R.color.f100));
                break;
            case 80:
            case 70:
                paint.setColor(Model.context.getColor(R.color.f80));
                break;
            case 60:
            case 50:
                paint.setColor(Model.context.getColor(R.color.f60));
                break;
            case 40:
            case 30:
                paint.setColor(Model.context.getColor(R.color.f40));
                break;
            case 20:
            case 10:
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
