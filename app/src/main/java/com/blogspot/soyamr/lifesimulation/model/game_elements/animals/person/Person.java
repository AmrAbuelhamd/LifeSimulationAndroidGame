package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Granary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.ChildHood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoToGranary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingToBuildGranary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.GoingToFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.state.SearchFood;

public abstract class Person extends Animal {
    public static final int GRANARY_VISION = ANIMAL_FOOD_VISION_RANG * 2;
    static Paint foodPaintRect = new Paint();

    static {

        foodPaintRect.setStyle(Paint.Style.STROKE);
        foodPaintRect.setStrokeWidth(60);
        foodPaintRect.setColor(Color.BLACK);
    }

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

    public Person() {
        homePaint.setColor(Color.YELLOW);
        homePaint.setStrokeWidth(3F);
        homePaint.setTextSize(20F);
    }

    void setHomeRect() {
        homeRect.set(homeSweetHome.getX() - width / 2, homeSweetHome.getY() - height / 2,
                homeSweetHome.getX() + width + width / 2, homeSweetHome.getY() + height + height / 2);

    }

    @Override
    public void update() {
        if (!checkBeforeUpdate())
            return;
        currentState.update(this);

        afterUpdate();
    }

    void afterUpdate() {
        updateIDoNotWant();
        reachedScreenEdge();
        gonnaStepOnDangerousGround();
        model.putMeHerePlease(x, y, this);
        setRect();
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
    public void updateAdditionalInfoLocation(float mScaleFactor) {
        super.updateAdditionalInfoLocation(mScaleFactor);
        if (homeSweetHome != null)
            homeSweetHome.updateAdditionalInfoLocation(mScaleFactor);
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        super.drawAdditionalInfo(canvas);
        if (nearestFood != null) {
            rect.set(nearestFood.getX() - GameObject.width,
                    nearestFood.getY() - GameObject.height,
                    nearestFood.getX() + GameObject.width * 2,
                    nearestFood.getY() + GameObject.height * 2);
            canvas.drawRect(rect, foodPaintRect);

            canvas.drawBitmap(GameBitmaps.danger,
                    nearestFood.getX() - 40,
                    nearestFood.getY() - GameBitmaps.danger.getHeight(),
                    null);
        }
    }

    public void eatSomething() {
        homeSweetHome.getFood();
        reduceHunger();
    }

    public void buildGranary(int newX, int newY) {

        granary = new Granary.Builder()
                .setCoordinates(newX, newY)
                .setModel(model)
                .build();
        model.addGranary(granary);
    }

    public abstract State getOneDirectionState();

    public abstract State getWaitHomeState();

    public abstract State getNotSetState();

    protected static abstract class Builder
            <T extends Person, B extends Builder<T, B>> extends Animal.Builder<T, B> {
        protected B setGranary(Granary granary) {
            object.granary = granary;
            return thisObject;
        }

        protected B setHome(HomeSweetHome homeSweetHome) {
            object.homeSweetHome = homeSweetHome;
            return thisObject;
        }

        public B isFirstGeneration(Boolean firstGeneration) {
            if (firstGeneration)
                object.currentState = object.getNotSetState();
            else
                object.currentState = object.childhoodState;
            return thisObject;
        }
    }
}
