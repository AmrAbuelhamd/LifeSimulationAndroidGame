package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.ANIMAL_WOMEN_VISION_RANG;

public class SearchPartner implements StateAnimal {
    @Override
    public void update(Animal animal) {//this state happens only for men

        if (animal.myCrushes.isEmpty())
            animal.myCrushes = Utils.searchAroundAnimal(ANIMAL_WOMEN_VISION_RANG, animal.getX(), animal.getY(),
                    animal.model, List.of(animal.type),
                    GenderEnum.FEMALE);
        if (animal.myCrushes.isEmpty()) {
            animal.currentState = animal.oneDirection;
            return;
        }
        //make sure that crushes that i kept in my list still available
        //if not delete it
        Animal target = animal.getNextTarget2();

        if (target == null) {
//            return Animal.NextMove.MOVE_RANDOMLY;
            animal.currentState = animal.oneDirection;
            return;
        }

        //here this means that she accepted
        animal.inRelation = true;
//        animal.paint.setStyle(Paint.Style.STROKE);
//        animal.paint.setStrokeWidth(10);
        animal.myLove = target;
        animal.myCrushes.remove(animal.myLove);

        animal.currentState = animal.inMarriageProcess;
    }

    @Override
    public String getStateName() {
        return "Search Partner";
    }
}
