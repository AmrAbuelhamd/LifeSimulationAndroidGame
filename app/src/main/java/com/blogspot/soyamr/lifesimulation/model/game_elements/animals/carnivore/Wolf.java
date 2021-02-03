package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Wolf extends Carnivore {
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Wolf.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Wolf.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }


    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -10453621;
        else if (hunger > 30)
            return -8875876;
        else
            return -7297874;
    }

    public static final class Builder extends Carnivore.Builder<Wolf, Wolf.Builder> {
        protected Wolf createObject() {
            return new Wolf();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.WOLF);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
