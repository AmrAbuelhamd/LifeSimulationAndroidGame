package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;


public class Animal extends GameObject {
    //animal can see this depth, i.e. 100 cell in all direction
    int hunger = 100;
    private static final int ANIMAL_SEARCH_RANG = 50;
    private final Model model;

    private static final Paint paint;


    static {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    Animal(Model model) {
        x = Utils.getRandom(0, Const.N) * width;
        y = Utils.getRandom(0, Const.M) * height;

        rect.set(x, y, x + width, y + height);
        this.model = model;
    }


    public void update() {
        if (!needFood()) {

            // Calculate the new position of the game character.
            int randomIndex = Utils.getRandom(0, 8);
            this.x = x + width * moveDirection[randomIndex][0];
            this.y = y + height * moveDirection[randomIndex][1];
        }
        reachedScreenEdge();
        checkIfAnimalOnSameCellWithPlant();
        rect.set(x, y, x + width, y + height);

    }

    void checkIfAnimalOnSameCellWithPlant() {
        if (model.plantsContain(getKey())) {
            model.removePlant(getKey());
            reduceHunger();
        }
    }

    boolean worthSearching = true;
    int lastX;
    int lastY;

    private boolean needFood() {
        if (hunger > 80)
            return false;

        //lidia [[FOR BONUS]] i added optimisation mechanism, {without it the animals becomes very slow.}
        //if last time animal didn't find food around it, then no need to search again if he didn't
        //move 10 cells aways from last time searched and didn't find anything.
        if (!worthSearching) {
            int distance = (int) Math.sqrt((x - lastX) * (x - lastX) + (y - lastY) * (y - lastY));
            if (distance < 10 * Const.CELL_WIDTH) {
                return false;
            }
        }

        //search clockwise direction in @ANIMAL_SEARCH_RANG depth
        Plant nearestPlant = Utils.searchAroundAnimal(ANIMAL_SEARCH_RANG, this, model.getPlants());
        if (nearestPlant == null) {
            worthSearching = false;
            lastX = x;
            lastY = y;
            return false;
        }
        worthSearching = true;
        //move the ANIMAL towards the plant
        // four cases
        if (nearestPlant.x < x)
            x -= width;
        else if (nearestPlant.x > x)
            x += width;

        if (nearestPlant.y < y)
            y -= height;
        else if (nearestPlant.y > y)
            y += height;

        return true;
    }


    @Override
    Paint getPaint() {
        return paint;
    }


    public void increaseHunger() {
        if (hunger == 0)
            model.deleteMePlease(this);
        else
            hunger -= 10;
    }

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
    }
}
