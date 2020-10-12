package com.blogspot.soyamr.lifesimulation;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Math.sqrt;


public class Creature extends GameObject {
    //creature can see this depth, i.e. 100 cell in all direction
    static final int CREATURE_SEARCH_RANG = 50;
    static Map<String, Plant> plants;
    static final String[] operator = {"+", "-"};
    static final int[][] moves = new int[][]{
            {1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
    };
    static final Paint paint;

    int life = 100;

    static {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    Creature() {
        x = getRandom(0, CONST.N) * width;
        y = getRandom(0, CONST.M) * height;

        rect.set(x, y, x + width, y + height);
    }


    static int getRandomSign() {
        int rdm = getRandom(0, 2);
        return Integer.parseInt(operator[rdm] + "" + "1");
    }

    public void update() {
        if (needFood())
            return;
        // Calculate the new position of the game character.
        this.x = x + (getRandomSign() * width) * getRandom(0, 2);
        this.y = y + (getRandomSign() * height) * getRandom(0, 2);

        reachedScreenEdge();
        rect.set(x, y, x + width, y + height);

    }

    private void reachedScreenEdge() {
        // When the game's character touches the edge of the screen, then stop it.
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > CONST.SCREEN_WIDTH - width) {
            this.x = CONST.SCREEN_WIDTH - width;
        }
        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > CONST.SCREEN_HEIGHT - height) {
            this.y = CONST.SCREEN_HEIGHT - height;
        }
    }

    private boolean needFood() {
        if (life > 50)
            return false;

        //search clockwise direction in @CREATURE_SEARCH_RANG depth
        Plant nearestPlant = null;
        for (int i = 0; i < CREATURE_SEARCH_RANG; i++) {
            int width = Creature.width * (i + 1);
            int height = Creature.height * (i + 1);
            for (int j = 0; j < moves.length; j++) {
                int nextCellX = x + moves[j][0] * width;
                int nextCellY = y + moves[j][1] * height;
                String plantKey = nextCellX + " " + nextCellY;
                //once found any plant, this means it's the closes no need to search further.
                if (plants.containsKey(plantKey)) {
                    nearestPlant = plants.get(plantKey);
                    i = CREATURE_SEARCH_RANG;
                    break;
                }
            }
        }
        if (nearestPlant == null) {
            return false;
        }
        //move the creature towards the plant
        // four cases
        if (Objects.requireNonNull(nearestPlant).x < x)
            x += width;
        else if (nearestPlant.x > x)
            x += width;

        if (Objects.requireNonNull(nearestPlant).y < y)
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
