package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Utils;


public abstract class Animal extends GameObject {
    int hunger = 100;
    final Model model;
    boolean inRelation = false;
    boolean iDoNotWant;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme

    Animal(Model model) {
        x = Utils.getRandom(0, Utils.Const.N) * width;
        y = Utils.getRandom(0, Utils.Const.M) * height;

        rect.set(x, y, x + width, y + height);
        this.model = model;
        setIdoNotWant();
    }

    Animal(int x, int y, Model model) {
        this.x = x;
        this.y = y;

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
        if (ihth < increasingHungerThreshold) {//toask is it better to keep this here or inside increase hunger itself?
            ++ihth;
        } else {
            increaseHunger();
            ihth = 0;
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
        rect.set(x, y, x + width, y + height);
    }

    protected void moveRandomly() {
        // Calculate the new position of the game character.
        int randomIndex = Utils.getRandom(0, 8);
        this.x = x + width * moveDirection[randomIndex][0];
        this.y = y + height * moveDirection[randomIndex][1];
    }

    private void checkIfAnimalOnSameCellWithPlant() {
        Plant plant = model.getPlant(getKey());
        if (plant != null) {
            model.removePlant(getKey());
            reduceHunger();
        }
    }

    boolean worthSearchingForFood = true;
    int lastX;
    int lastY;

    private boolean doesItWorthSearching() {
        if (!worthSearchingForFood) {
            int distance = (x - lastX) * (x - lastX) + (y - lastY) * (y - lastY);
            return distance >= (SEARCH_FOOD_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH)
                    * (SEARCH_FOOD_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH);
        } else {
            return true;
        }
    }

    boolean needFood() {

        //if last time animal didn't find food around it, then no need to search again if he didn't
        //move 10 cells aways from last time searched and didn't find anything.
        if (!doesItWorthSearching()) {
            return false;
        }

        //search clockwise direction in @ANIMAL_SEARCH_RANG depth
        Plant nearestPlant = (Plant) Utils.searchAroundAnimal(ANIMAL_FOOD_VISION_RANG, x, y, model, Utils.Const.SearchFor.PLANT);
        if (nearestPlant == null) {
            worthSearchingForFood = false;
            lastX = x;
            lastY = y;
            return false;
        }
        worthSearchingForFood = true;

        //move the ANIMAL towards the plant
        moveToward(nearestPlant.x, nearestPlant.y);

        return true;
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


    public void increaseHunger() {
        if (hunger == 0)
            model.deleteMePlease(this);
        else
            hunger -= 10;
        changeColor();
    }

    protected abstract void changeColor();

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }
}
