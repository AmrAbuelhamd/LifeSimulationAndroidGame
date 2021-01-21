package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.List;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.ANIMAL_WOMEN_VISION_RANG;

public class SearchPartner implements StateAnimal {
    @Override
    public void update(Animal animal) {//this state happens only for men
        Animal.NextMove result;

        if (animal.genderOperator.myCrushes.isEmpty())
            animal.genderOperator.myCrushes = Utils.searchAroundAnimal(ANIMAL_WOMEN_VISION_RANG, animal.getX(), animal.getY(),
                    animal.model, List.of(animal.type),
                    GenderEnum.FEMALE);
        if (animal.genderOperator.myCrushes.isEmpty()) {
            animal.currentState = animal.oneDirection;
            return;
        }
        //make sure that crushes that i kept in my list still available
        //if not delete it
        Animal target = animal.genderOperator.getNextTarget2();

        if (target == null) {
//            return Animal.NextMove.MOVE_RANDOMLY;
            animal.currentState = animal.oneDirection;
            return;
        }

        //here this means that she accepted
        animal.genderOperator.inRelation = true;
//        animal.paint.setStyle(Paint.Style.STROKE);
//        animal.paint.setStrokeWidth(10);
        animal.genderOperator.myLove = target;
        animal.genderOperator.myCrushes.remove(animal.genderOperator.myLove);

        animal.currentState = animal.inMarriageProcess;
    }

    @Override
    public String getStateName() {
        return "Search Partner";
    }
}
