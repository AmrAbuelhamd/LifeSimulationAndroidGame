package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Rabbit extends Herbivore {

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Rabbit.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Rabbit.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -749647;
        else if (hunger > 30)
            return -476208;
        else
            return -203540;
    }

    public static final class Builder extends Herbivore.Builder<Rabbit, Rabbit.Builder> {
        protected Rabbit createObject() {
            return new Rabbit();
        }


        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.RABBIT);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
