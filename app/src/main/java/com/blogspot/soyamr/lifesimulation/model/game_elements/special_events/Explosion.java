package com.blogspot.soyamr.lifesimulation.model.game_elements.special_events;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class Explosion {
    final Model model;
    Bitmap[] image;
    private int num = 0;
    private boolean finish = false;
    int x,y;

    public Explosion(Model model, Bitmap[] image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.model = model;
        model.removeObjectsInThisArea(x, y, x + image[1].getWidth(), y + image[1].getHeight());
    }

    public void update() {
        ++this.num;
        if (num % 5 == 0)
            model.removeObjectsInThisArea(x, y, x + image[1].getWidth(), y + image[1].getHeight());

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

    public boolean isFinish() {
        return finish;
    }
}
