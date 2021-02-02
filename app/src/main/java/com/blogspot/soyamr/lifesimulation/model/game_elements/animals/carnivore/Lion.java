package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Lion extends Carnivore {
    public Lion() {
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Lion.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Lion.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }


    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -8227049;
        else if (hunger > 30)
            return -6382300;
        else
            return -5262293;
    }

    public static final class Builder extends Carnivore.Builder<Lion, Lion.Builder> {
        protected Lion createObject() {
            return new Lion();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.LION);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
