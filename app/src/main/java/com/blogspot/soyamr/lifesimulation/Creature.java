package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Creature extends GameObject {

    static final String[] operator = {"+", "-"};

    static final Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    Creature(int creatureNo) {
        x = getRandom(0, CONST.N) * width;
        y = getRandom(0, CONST.M) * height;

        rect.set(x, y, x + width, y + height);
        key = creatureNo;
    }


    public void update() {
        // Calculate the new position of the game character.
        this.x = x + (getRandomSign() * width) * getRandom(0, 2);
        this.y = y + (getRandomSign() * height) * getRandom(0, 2);

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
        rect.set(x, y, x + width, y + height);

    }

    static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    static int getRandomSign() {
        int rdm = getRandom(0, 2);
        return Integer.parseInt(operator[rdm] + "" + "1");
    }

    @Override
    Paint getPaint() {
        return paint;
    }
}
