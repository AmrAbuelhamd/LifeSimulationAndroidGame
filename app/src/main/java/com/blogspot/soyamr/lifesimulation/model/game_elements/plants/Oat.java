package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Oat extends Plant {
//    public Oat(int x, int y, Model model) {
//        super(x, y, model,Type.OAT);
//    }


    public Oat() {
//        model.putMeHerePlease(this.x, this.y, this);

    }

    public static final class Builder extends Plant.Builder<Oat, Oat.Builder> {
        protected Oat createObject() {
            return new Oat();
        }
        protected Oat.Builder thisObject() {
            setType(Type.OAT);
            setImage(object.type.getImage(object.genderEnum));
            return this;
        }
    }

    @Override
    public int getMyColor() {
       return -6190977;
    }

}
