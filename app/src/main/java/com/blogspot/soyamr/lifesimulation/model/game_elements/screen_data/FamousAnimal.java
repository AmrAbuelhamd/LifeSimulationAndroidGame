package com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class FamousAnimal {
    GameObject gameObject;

    public FamousAnimal(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void draw(Canvas canvas) {
        gameObject.drawAdditionalInfo(canvas);
    }

    public void update(float mScaleFactor) {
        gameObject.updateAdditionalInfoLocation(mScaleFactor);
    }

}
