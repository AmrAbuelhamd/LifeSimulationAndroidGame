package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.SEARCH_FOOD_THRESHOLD;

public class Female extends Gender {
    RectF rect = new RectF();
    Paint strokePaint = new Paint();
    public Female(Animal animal) {
        super(animal);

//        strokePaint.setStyle(Paint.Style.STROKE);
//        strokePaint.setColor(-3862174);
//        strokePaint.setStrokeWidth(2);
    }

    @Override
    public void doCeremony() {
        marriage();
        brokeUp();
        animal.currentState = animal.notSet;
    }

    @Override
    public boolean wannaBeInRelationShip(Type groomType) {
        if (!iDoNotWant && !inRelation && animal.hunger > SEARCH_FOOD_THRESHOLD) {
            if (animal.type == groomType) {
                inRelation = true;
                animal.currentState = animal.inMarriageProcess;
//                animal.paint.setStyle(Paint.Style.STROKE);
//                animal.paint.setStrokeWidth(10);
                return true;
            }
        }
        return false;
    }

    public void brokeUp() {
        inRelation = false;
//        animal.paint.setStyle(Paint.Style.FILL);
        setIdoNotWant();
    }

    public void marriage() {
        animal.addChild();
    }

    Animal.NextMove waitLoveToArrive() {
        return Animal.NextMove.NOTHING;
    }

    @Override
    public Animal.NextMove takeRequiredActions() {
        return waitLoveToArrive();
    }

    @Override
    public Animal.NextMove searchForPartner() {
        return Animal.NextMove.NOT_SET;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(rect, 4, 4, animal.paint);
//        canvas.drawRoundRect(animal.getX() - 5, animal.getY() - 5, animal.getX() + 5 + GameObject.width,
//                animal.getY() + GameObject.height + 5, 0, 0, strokePaint);
    }

    @Override
    public void setRect() {
        rect.set(animal.getX(), animal.getY(), animal.getX() + GameObject.width,
                animal.getY() + GameObject.height);
    }

    @Override
    public Animal getNextTarget2() {
        return null;
    }
}
