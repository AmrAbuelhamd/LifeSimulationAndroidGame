package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public final class Fox extends Carnivore {

    private Fox() {
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Fox.Builder()
                    .setGender(GenderEnum.MALE)
                    .setCoordinates(x, y)
                    .setModel(model)
                    .build()
            );
        else
            model.addChild(
                    new Fox.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -688361;
        else if (hunger > 30)
            return -415707;
        else
            return -278483;
    }


    public static final class Builder extends Carnivore.Builder<Fox, Builder> {
        protected Fox createObject() {
            return new Fox();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.FOX);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
