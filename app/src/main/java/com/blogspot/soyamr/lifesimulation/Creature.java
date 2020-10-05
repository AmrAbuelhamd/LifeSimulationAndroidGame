package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Creature implements GameObject {
    int x;
    int y;
    Paint paint = new Paint();
    Rect rect;
    int key;
    static String[] operator = {"+", "-"};

    Creature(int creatureNo) {
        x = getRandom(0, CONST.N) * width;
        y = getRandom(0, CONST.M) * height;
        rect = new Rect(x, y, x + width, y + height);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        key = creatureNo;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
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
        rect = new Rect(x, y, x + width, y + height);
    }


    static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    static int getRandomSign() {
        int rdm = getRandom(0, 2);
        return Integer.parseInt(operator[rdm] + "" + "1");
    }

    @Override
    public int getKey() {
        return key;
    }
}
