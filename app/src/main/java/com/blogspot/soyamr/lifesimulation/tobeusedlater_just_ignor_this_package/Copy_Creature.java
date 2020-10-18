package com.blogspot.soyamr.lifesimulation.tobeusedlater_just_ignor_this_package;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.GameObject;

import java.util.Random;


public abstract class Copy_Creature extends GameObject {
    int x;
    int y;
    Paint paint = new Paint();
    Rect rect;
//    private long lastDrawNanoTime = -1;
int width,height;
    static String[] operator = {"+", "-"};

    Copy_Creature() {
        x = getRandom(0, Const.N) * width;
        y = getRandom(0, Const.M) * height;
        rect = new Rect(x, y, x + width, y + height);
        paint.setStyle(Paint.Style.FILL);
//        paint.setARGB(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        paint.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public void update() {

        // Current time in nanoseconds
//        long now = System.nanoTime();AmrAbuelhamd

//        // Never once did draw.
//        if (lastDrawNanoTime == -1) {
//            lastDrawNanoTime = now;
//        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
//        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);
//        Log.i("mrTest", "deltaTime: " + deltaTime);
//        if (deltaTime < 1310799552)
//            return;
        // Distance moves
//        float distance = VELOCITY * deltaTime;

//        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);

        // Calculate the new position of the game character.
        this.x = x + (getRandomSign() * width) * getRandom(0, 2);
        this.y = y + (getRandomSign() * height) * getRandom(0, 2);

        // When the game's character touches the edge of the screen, then change direction

        if (this.x < 0) {
            this.x = 0;
//            this.movingVectorX = -this.movingVectorX;
        } else if (this.x > Const.SCREEN_WIDTH - width) {
            this.x = Const.SCREEN_WIDTH - width;
//            this.movingVectorX = -this.movingVectorX;
        }

        if (this.y < 0) {
            this.y = 0;
//            this.movingVectorY = -this.movingVectorY;
        } else if (this.y > Const.SCREEN_HEIGHT - height) {
            this.y = Const.SCREEN_HEIGHT - height;
//            this.movingVectorY = -this.movingVectorY;
        }
        rect = new Rect(x, y, x + width, y + height);
    }

static Random rand=new Random();
    static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    static int getRandomSign() {
        int rdm = getRandom(0, 2);
        return Integer.parseInt(operator[rdm] + "" + "1");
    }


    public int getKey() {
        return 0;
    }
}