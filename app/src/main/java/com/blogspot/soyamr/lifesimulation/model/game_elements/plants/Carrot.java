package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Carrot extends Plant {

    @Override
    public int getMyColor() {
        return -1683200;
    }

    public static final class Builder extends Plant.Builder<Carrot, Carrot.Builder> {
        protected Carrot createObject() {
            return new Carrot();
        }

        protected Carrot.Builder thisObject() {
            setType(Type.CARROT);
            setImage(object.type.getImage(object.genderEnum));
            return this;
        }
    }

}
