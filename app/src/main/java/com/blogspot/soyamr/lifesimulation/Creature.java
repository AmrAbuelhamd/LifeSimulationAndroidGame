package com.blogspot.soyamr.lifesimulation;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Map;


public class Creature extends GameObject {
    //creature can see this depth, i.e. 100 cell in all direction
    private static final int CREATURE_SEARCH_RANG = 30;
    static Map<String, Plant> plants;
    private static final int[][] moveDirection = new int[][]{
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
    };
    private static final Paint paint;

    int life = 100;

    static {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    Creature() {
        x = getRandom(0, Const.N) * width;
        y = getRandom(0, Const.M) * height;

        rect.set(x, y, x + width, y + height);
    }


    public void update() {
        if (needFood())
            return;

        // Calculate the new position of the game character.
        int randomIndex = getRandom(0, 8);
        this.x = x + width * moveDirection[randomIndex][0];
        this.y = y + height * moveDirection[randomIndex][1];

        reachedScreenEdge();
        rect.set(x, y, x + width, y + height);
    }

    private void reachedScreenEdge() {
        // When the game's character touches the edge of the screen, then stop it.
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > Const.SCREEN_WIDTH - width) {
            this.x = Const.SCREEN_WIDTH - width;
        }
        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > Const.SCREEN_HEIGHT - height) {
            this.y = Const.SCREEN_HEIGHT - height;
        }
    }

    private boolean needFood() {
        if (life > 90)
            return false;

        //search clockwise direction in @CREATURE_SEARCH_RANG depth
        Plant nearestPlant = Algorithms.searchAroundAnimal(CREATURE_SEARCH_RANG, this, plants);
        if (nearestPlant == null) {
            return false;
        }

        //move the creature towards the plant
        // four cases
        if (nearestPlant.x < x)
            x -= width;
        else if (nearestPlant.x > x)
            x += width;

        if (nearestPlant.y < y)
            y -= height;
        else if (nearestPlant.y > y)
            y += height;

        reachedScreenEdge();
        rect.set(x, y, x + width, y + height);
        return true;
    }


    @Override
    Paint getPaint() {
        return paint;
    }

    public boolean reduceLife() {
        if (life == 0)
            return true;
        else
            life -= 10;
        return false;
    }

    public void increaseLife() {
        if (life != 100)
            life += 10;
    }
}
