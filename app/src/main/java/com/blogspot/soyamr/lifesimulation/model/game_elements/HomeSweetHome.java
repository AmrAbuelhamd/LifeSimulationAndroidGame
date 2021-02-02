package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.List;

public class HomeSweetHome extends GameObject {

    final int stockSize = 10;
    List<GameObject> foodList = new ArrayList<>();
    Model model;
    int xDraw, yDraw;
    Rect rect = new Rect();
    Paint textAndRectPaint;

    public HomeSweetHome() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(150);
        paint.setTextSize(100);

        rect = getRect();

        textAndRectPaint = new Paint();
        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(500);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(getMyColor());
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
        canvas.drawRect(rect.left, rect.top,
                rect.right, rect.bottom
                , paint);
        canvas.drawText("stock size: " + foodList.size(), xDraw, yDraw, textAndRectPaint);
    }

    //    @Override
//    public void draw(Canvas canvas) {
//        canvas.drawLine(x, y, x + width, y + height, paint);
//        canvas.drawLine(x + width, y, x, y + height, paint);
//    }

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
        rect.set(x, y, x + width * 2, y + height * 2);
        return rect;
    }

    public static final class Builder extends GameObject.Builder<HomeSweetHome, HomeSweetHome.Builder> {
        protected HomeSweetHome createObject() {
            return new HomeSweetHome();
        }

        protected Builder thisObject() {
            setImage(GameBitmaps.homeImg);
            return this;
        }

        public Builder setCoordinates(int x, int y) {
            object.x = x;
            object.y = y;

            object.xDraw = x - 400;
            object.yDraw = y - 200;
            return thisObject;
        }

        public Builder setModel(Model model) {
            object.model = model;
            model.putMeHerePlease(object.getX(), object.getY(), object);
            return thisObject;
        }

//        public Builder setBitmap(Bitmap bitmap) {
//            object.image = bitmap;
//            object.width = bitmap.getWidth();
//            object.height = bitmap.getHeight();
//            return thisObject;
//        }
    }
}
