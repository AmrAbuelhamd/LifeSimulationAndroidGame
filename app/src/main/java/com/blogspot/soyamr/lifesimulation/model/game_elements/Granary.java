package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Granary extends GameObject {

    final int stockSize = 100;
    List<GameObject> foodList = new ArrayList<>();
    int width;
    int height;
    Bitmap image;
    Paint textAndRectPaint;
    Model model;
    int xDraw, yDraw;

    public Granary(int x, int y, Bitmap image, Model model) {
        super(Type.GRANARY, GenderEnum.BOTH);
        this.model = model;
        this.x = x;
        this.y = y;
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        //this thing will be queried from inside the model itself, since it will take more than cell
        isAlive = false;
        textAndRectPaint = new Paint();
        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(30);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(getMyColor());
        xDraw = getX() - 40;
        yDraw = getY() - 20;
        rect.set(x, y, x + image.getWidth(), y + image.getHeight());
        model.putMeHerePlease(this.x, this.y, this);
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
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        canvas.drawText("stock size: " + foodList.size(), xDraw, yDraw, textAndRectPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        if (isAlive)
            canvas.drawBitmap(image, x, y, null);
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
}
