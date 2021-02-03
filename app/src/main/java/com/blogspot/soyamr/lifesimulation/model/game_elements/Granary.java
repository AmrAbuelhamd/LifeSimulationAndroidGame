package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.List;

public final class Granary extends GameObject {

    final int stockSize = 100;
    List<GameObject> foodList = new ArrayList<>();
    int width;
    int height;
    Paint textAndRectPaint;
    Model model;
    int xDraw, yDraw;
    int textSize = 50;

    public Granary() {
        //this thing will be queried from inside the model itself, since it will take more than cell
        isAlive = false;
        textAndRectPaint = new Paint();
        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(500);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(Color.BLACK);
    }

    public void showMePlease() {
        isAlive = true;
    }

    @Override
    public void makeMeFamous() {
        model.setFamousAnimal(this);
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
        textAndRectPaint.setTextSize(textSize / mScaleFactor);
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        canvas.drawText("stock size: " + foodList.size(), xDraw, yDraw, textAndRectPaint);
    }


    @Override
    public int getMyColor() {
        return Color.WHITE;
    }

    public String getStockSize() {
        return foodList.size() + "";
    }

    public boolean isStockEmpty() {
        return foodList.isEmpty();
    }

    public boolean intersects(Rect rectA) {
        return Rect.intersects(rect, rectA);
    }

    public static final class Builder extends GameObject.Builder<Granary, Granary.Builder> {
        protected Granary createObject() {
            return new Granary();
        }

        protected Granary.Builder thisObject() {
            setType(Type.GRANARY);
            setImage(GameBitmaps.granaryImg);
            object.width = object.image.getWidth();
            object.height = object.image.getHeight();
            return this;
        }

        public Builder setCoordinates(int x, int y) {
            object.x = x;
            object.y = y;

            object.xDraw = x - 40;
            object.yDraw = y - 20;
            return thisObject;
        }

        public Builder setModel(Model model) {
            object.model = model;
            model.putMeHerePlease(object.getX(), object.getY(), object);
            object.rect.set(object.getX(), object.getY(),
                    object.getX() + object.image.getWidth(),
                    object.getY() + object.image.getHeight());
            return thisObject;
        }
    }
}
