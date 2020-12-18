package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Canvas;
import android.graphics.Color;

import com.blogspot.soyamr.lifesimulation.model.Model;

public class HomeSweetHome extends GameObject {

    public HomeSweetHome(int x, int y, Model model) {
        super(Type.HOME, GenderEnum.BOTH);
        this.x = x;
        this.y = y;

        //model.putMeHerePlease(x, y, this);

    }

    @Override
    public void makeMeFamous() {

    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {

    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas) {
        float width = GameObject.width;
        float height = GameObject.height;
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        canvas.drawLine(x, y, x + width, y + height, paint);
        canvas.drawLine(x + width, y, x, y + height, paint);
    }

    @Override
    public int getMyColor() {
        return Color.RED;
    }
}
