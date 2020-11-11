package com.blogspot.soyamr.lifesimulation.model;


import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public abstract class Animal extends GameObject {
    List<GameObject> myFoodMenu = null;
    int hunger = 20;
    final Model model;
    boolean inRelation = false;
    boolean iDoNotWant;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme
    private String tag = "Animal";

    Animal(Model model) {
        x = Utils.getRandom(0, Utils.Const.N) * width;
        y = Utils.getRandom(0, Utils.Const.M) * height;
        myFoodMenu = new ArrayList<>();

        rect.set(x, y, x + width, y + height);
        this.model = model;
        setIdoNotWant();
    }

    Animal(int x, int y, Model model) {
        this.x = x;
        this.y = y;
        myFoodMenu = new ArrayList<>();
        rect.set(x, y, x + width, y + height);
        this.model = model;
        setIdoNotWant();
    }

    final int reSetIDoNotWantVariable = 90;
    int rsidwv = 0;

    protected void setIdoNotWant() {
        iDoNotWant = true;
        rsidwv = 0;
    }

    final int increasingHungerThreshold = 100;
    int ihth = 0;

    //search for food or move randomly
    public void update() {
        if (ihth < increasingHungerThreshold) {//todo add this to another method, but be sure that
            ++ihth;                         //if dead no need to add ghost animal on cell and disappear
        } else {
            boolean isDead = increaseHunger();
            ihth = 0;
            if (isDead)
                return;
        }
        if (iDoNotWant == true) {
            if (rsidwv < reSetIDoNotWantVariable) {
                ++rsidwv;
            } else {
                iDoNotWant = false;
                rsidwv = 0;
            }
        }

        reachedScreenEdge();
        checkIfAnimalOnSameCellWithPlant();
        model.putMeHerePlease(x, y, this);
        rect.set(x, y, x + width, y + height);

    }

    protected void moveRandomly() {
        // Calculate the new position of the game character.
        int randomIndex = Utils.getRandom(0, 8);
        this.x = x + width * moveDirection[randomIndex][0];
        this.y = y + height * moveDirection[randomIndex][1];
    }

    private void checkIfAnimalOnSameCellWithPlant() {
        List<GameObject> gameObjects = model.getObjectResidingHere(Utils.getRowIndex(y), Utils.getColumnIndex(x));
        for (GameObject current : gameObjects) {
            if (current instanceof Plant) {
                model.removePlant((Plant) current);
                reduceHunger();
                myFoodMenu.removeIf(el -> el.x == x && el.y == y);
                break;
            }
        }
    }

    int lastX;
    int lastY;

//    private boolean doesItWorthSearching() {
//        if (!worthSearchingForFood) {
//            int distance = Math.abs(x - lastX) + Math.abs(y - lastY);
//            return distance >= (SEARCH_FOOD_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH);
//        } else {
//            return true;
//        }
//    }

    int moveToOneDirectionCTR = 50;
    int movingToOneDirectionThreshold = 30;
    int[] direction = new int[2];

    boolean needFood() {
        if (moveToOneDirectionCTR < movingToOneDirectionThreshold) {
            ++moveToOneDirectionCTR;
            moveThere();
            return true;
        }
        GameObject target = null;
        if (myFoodMenu.isEmpty())
            myFoodMenu = Utils.searchAroundAnimal(ANIMAL_FOOD_VISION_RANG, x, y, model, Utils.Const.SearchFor.PLANT);
        if (myFoodMenu.isEmpty()) {
            moveToOneDirectionCTR = 0;
            int rand = Utils.getRandom(0, moveDirection.length);
            direction = moveDirection[rand];
            moveThere();
            return true;
        }
        //make sure that food that i kept in my list still available before going towards it
        //if not delete it
        ListIterator<GameObject> iter = myFoodMenu.listIterator();
        while (iter.hasNext()) {
            GameObject current = iter.next();
            if (Utils.search(Utils.Const.SearchFor.PLANT, model,
                    Utils.getRowIndex(current.y), Utils.getColumnIndex(current.x)) != null) {
                target = current;
                break;
            } else {
                iter.remove();
            }
        }
        if (target == null)
            return false;

        //move the ANIMAL towards the plant
        moveToward(target.x, target.y);

        return true;
    }

    private void moveThere() {
//        Log.i(tag, "moving to one direction");
        x += width * direction[0];
        y += height * direction[1];
        boolean flag = false;
        if (this.x < 0) {
            this.x = 0;
            flag = true;
        } else if (this.x > Utils.Const.FIELD_WIDTH - width) {
            this.x = Utils.Const.FIELD_WIDTH - width;
            flag = true;
        }
        if (this.y < 0) {
            this.y = 0;
            flag = true;
        } else if (this.y > Utils.Const.FIELD_HEIGHT - height) {
            this.y = Utils.Const.FIELD_HEIGHT - height;
            flag = true;
        }
        if (flag)
            direction = moveDirection[Utils.getRandom(0, moveDirection.length)];
    }

    void moveToward(int targetX, int targetY) {

        // four cases
        if (targetX < x)
            x -= width;
        else if (targetX > x)
            x += width;

        if (targetY < y)
            y -= height;
        else if (targetY > y)
            y += height;

    }


    public boolean increaseHunger() {
        if (hunger == 0) {
            model.deleteMePlease(this);
            return true;
        } else
            hunger -= 10;
        changeColor();
        return false;
    }

    protected abstract void changeColor();

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }
}
