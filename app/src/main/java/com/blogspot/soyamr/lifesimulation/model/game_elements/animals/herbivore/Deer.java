package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.GameBitmaps;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Lion;

import java.util.List;

public class Deer extends Herbivore {

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(
                    new Deer.Builder()
                            .setGender(GenderEnum.MALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build()
            );
        else
            model.addChild(
                    new Deer.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setCoordinates(x, y)
                            .setModel(model)
                            .build());
    }


    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -13784;
        else if (hunger > 30)
            return -10929;
        else
            return -8062;
    }

    public static final class Builder extends Herbivore.Builder<Deer, Deer.Builder> {
        protected Deer createObject() {
            return new Deer();
        }

        public Builder setGender(GenderEnum genderEnum) {
            object.genderEnum = genderEnum;
            setImage(object.type.getImage(genderEnum));
            return this;
        }

        protected Builder thisObject() {
            setType(Type.DEER);
            setFoodTypeList(object.type.getFoodList());
            return this;
        }
    }
}
