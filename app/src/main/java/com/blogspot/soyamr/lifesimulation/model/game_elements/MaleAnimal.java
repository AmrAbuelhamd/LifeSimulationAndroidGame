package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.FantasticColors;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.types.AnimalSpecie;
import com.blogspot.soyamr.lifesimulation.model.types.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MaleAnimal<T extends AnimalSpecie<Species>> extends Animal {

    T mySpecie;
    private FemaleAnimal myLove;
    private List<GameObject> myCrushes;

    public MaleAnimal(Model model, T myType) {
        super(model, myType.getFoodType());
        this.mySpecie = myType;
        model.putMeHerePlease(x, y, this);
        setInitialColor();
    }

    public MaleAnimal(int x, int y, Model model, T myType) {
        super(x, y, model, myType.getFoodType());
        mySpecie = myType;
        model.putMeHerePlease(x, y, this);
        setInitialColor();
    }

    void setInitialColor() {
        paint.setColor(model.getMeColor(FantasticColors.TYPE.male, hunger));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        myCrushes = new ArrayList<>();
    }

    @Override
    public void update() {
        model.iAmLeavingThisCell(x, y, this);
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
        }//use flag
        super.update();
    }

    private void doCeremony() {
        myLove.marriage();
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (inRelation) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.WHITE);
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
            return true;
        }
        if (myCrushes.isEmpty())
            myCrushes = Utils.searchAroundAnimal(ANIMAL_WOMEN_VISION_RANG, x, y, model, Species.FEMALE_ANIMAL);
        if (myCrushes.isEmpty()) {
            moveToOneDirectionSetUp();
            return true;
        }
        //make sure that crushes that i kept in my list still available
        //if not delete it
        FemaleAnimal target = getNextTarget();

        if (target == null)
            return false;
        //if true this means she already engaged
        //no broken hearts in my game :)
        inRelation = true;
        myLove = target;
        myCrushes.remove(myLove);
        moveToward(myLove.x, myLove.y);
        return true;
    }

    FemaleAnimal getNextTarget() {
        ListIterator<GameObject> iter = myCrushes.listIterator();
        while (iter.hasNext()) {
            FemaleAnimal current = (FemaleAnimal) iter.next();
            if (current.wannaBeInRelationship()) {
                return current;
            } else {
                iter.remove();
            }
        }
        return null;
    }

    @Override
    protected boolean isSuitableFood(GameObject current) {
        return mySpecie.isSuitableFood(current);
    }
}
