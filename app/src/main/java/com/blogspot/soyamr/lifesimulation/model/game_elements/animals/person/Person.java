package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

public abstract class Person extends Animal {
    public HomeSweetHome homeSweetHome;
    Paint homePaint = new Paint();
    Rect homeRect = new Rect();
    public State currentState;

    public Person(int x, int y, Model model, GenderEnum genderEnum, List<Type> foodList) {
        super(x, y, model, Type.PERSON, genderEnum, foodList );
    }

    void afterUpdate() {
        genderOperator.updateIDoNotWant();
        reachedScreenEdge();
        model.putMeHerePlease(x, y, this);
        genderOperator.setRect();
    }

    boolean checkBeforeUpdate() {
        if (isAlive) {
            boolean isDead = updateHunger();
            if (isDead) {
                model.deleteMePlease(this);
                return false;
            }
            model.iAmLeavingThisCell(x, y, this);
            return true;
        }
        return false;
    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -11684180;
        else if (hunger > 30)
            return -8336444;
        else
            return -5054501;
    }

    public boolean goHome() {
        moveToward(homeSweetHome.getX(), homeSweetHome.getY());
        return isHome();
    }

    public boolean isHome() {
        return x == homeSweetHome.getX() && y == homeSweetHome.getY();
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        super.drawAdditionalInfo(canvas);
        if (homeSweetHome != null) {
            canvas.drawRect(homeRect, homePaint);
            canvas.drawText("stock " + homeSweetHome.getStockSize(), (float) homeSweetHome.getX(),
                    (float) homeSweetHome.getY() - height * 2, paint);
        }
    }

    public void eatSomething() {
        homeSweetHome.getFood();
        reduceHunger();
    }
}
