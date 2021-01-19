package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Granary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.ChildHood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoToGranary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingToBuildGranary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingToFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.SearchFood;

import java.util.List;

public abstract class Person extends Animal {
    public static final int GRANARY_VISION = ANIMAL_FOOD_VISION_RANG * 2;
    public HomeSweetHome homeSweetHome;
    public State currentState;
    public GameObject nearestFood;
    public boolean isMarried = false;
    public Granary granary;
    public State searchFood = new SearchFood();
    public State goingToFood = new GoingToFood();
    public State goingHome = new GoingHome();
    public State goToGranary = new GoToGranary();
    public State goingToBuildGranary = new GoingToBuildGranary();
    public State childhoodState = new ChildHood();
    Paint homePaint = new Paint();
    Rect homeRect = new Rect();


    public Person(int x, int y, Model model, GenderEnum genderEnum, List<Type> foodList) {
        super(x, y, model, Type.PERSON, genderEnum, foodList);
        homePaint.setColor(Color.YELLOW);
        homePaint.setStrokeWidth(3F);
        homePaint.setTextSize(20F);
    }

    void setHomeRect() {
        homeRect.set(homeSweetHome.getX() - width / 2, homeSweetHome.getY() - height / 2,
                homeSweetHome.getX() + width + width / 2, homeSweetHome.getY() + height + height / 2);

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
            homePaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(homeRect, homePaint);
            homePaint.setStyle(Paint.Style.FILL);
            canvas.drawText("stock " + homeSweetHome.getStockSize(), (float) homeSweetHome.getX(),
                    (float) homeSweetHome.getY() - height * 2, homePaint);
        }
    }

    public void eatSomething() {
        homeSweetHome.getFood();
        reduceHunger();
    }

    public void buildGranary(int newX, int newY) {
        granary = new Granary(newX, newY, model.gameBitmaps.granaryImg, model);
        model.addGranary(granary);
    }

    public abstract State getOneDirectionState();

    public abstract State getWaitHomeState();

    public abstract State getNotSetState();

    protected void setHome(HomeSweetHome homeSweetHome) {
        this.homeSweetHome = homeSweetHome;
    }

    protected void setGranary(Granary granary) {
        this.granary = granary;
    }
}
