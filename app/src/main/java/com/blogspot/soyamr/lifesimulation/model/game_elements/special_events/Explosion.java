package com.blogspot.soyamr.lifesimulation.model.game_elements.special_events;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Explosion extends GameObject {
    final Model model;
    Bitmap[] image;
    private int num = 0;
    private boolean finish = false;


    public Explosion(Model model, Bitmap[] image, int x, int y) {
        super(null, null);
        this.x = x;
        this.y = y;
        this.image = image;
        this.model = model;
        model.removeObjectsInThisArea(x, y, x + width, y + height);
    }

    public void update() {
        ++this.num;
        if (num % 5 == 0)
            model.removeObjectsInThisArea(x, y, x + width, y + height);

        if (this.num >= 26) {
            this.finish = true;
        }
    }

    public void draw(Canvas canvas) {
        if (!finish) {
            if (num == 0)
                ++num;
            canvas.drawBitmap(image[num], this.x, this.y, null);
        }
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
    public int getMyColor() {
        return 0;
    }

    public boolean isFinish() {
        return finish;
    }
}
