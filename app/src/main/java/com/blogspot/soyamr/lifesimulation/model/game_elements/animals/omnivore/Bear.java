package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;


public class Bear extends Omnivore {
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Bear.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Bear.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -9614271;
        else if (hunger > 30)
            return -8825528;
        else
            return -7508381;
    }

    public static final class Builder extends Omnivore.Builder<Bear, Bear.Builder> {
        protected Bear createObject() {
            return new Bear();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.BEAR);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
