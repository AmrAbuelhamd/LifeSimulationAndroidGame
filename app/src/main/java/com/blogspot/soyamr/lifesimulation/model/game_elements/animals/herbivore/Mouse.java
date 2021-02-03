package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Mouse extends Herbivore {

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Mouse.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Mouse.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }


    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -4342339;
        else if (hunger > 30)
            return -2039584;
        else
            return -1118482;
    }

    public static final class Builder extends Herbivore.Builder<Mouse, Mouse.Builder> {
        protected Mouse createObject() {
            return new Mouse();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.MOUSE);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
