package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.List;

public class HomeSweetHome extends GameObject {

    final int stockSize = 10;
    List<GameObject> foodList = new ArrayList<>();
    Model model;
    public HomeSweetHome(int x, int y, Model model) {
        super(Type.HOME, GenderEnum.BOTH);
        this.x = x;
        this.y = y;
        this.model = model;
        model.putMeHerePlease(x, y, this);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    @Override
    public void makeMeFamous() {
    }

    public boolean addFood(GameObject food) {
        if (!isStockFull()) {
            foodList.add(food);
            return true;
        }
        return false;
    }

    public boolean isStockFull() {
        return foodList.size() >= stockSize;
    }

    public GameObject getFood() {
        if (foodList.isEmpty())
            return null;
        return foodList.remove(0);
    }


    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {

    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {

    }

//    @Override
//    public void draw(Canvas canvas) {
//        canvas.drawLine(x, y, x + width, y + height, paint);
//        canvas.drawLine(x + width, y, x, y + height, paint);
//    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            canvas.drawBitmap(model.gameBitmaps.homeImg, x, y, null);
    }

    @Override
    public int getMyColor() {
        return Color.RED;
    }

    public String getStockSize() {
        return foodList.size() + "";
    }

    public boolean isStockEmpty() {
        return foodList.isEmpty();
    }

    public Rect getRect() {
        rect.set(x, y, x + width, y + height);
        return rect;
    }
}
