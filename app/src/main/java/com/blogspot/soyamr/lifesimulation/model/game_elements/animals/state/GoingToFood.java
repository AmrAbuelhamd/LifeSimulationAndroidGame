package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state;

import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

public class GoingToFood implements StateAnimal {
    @Override
    public void update(Animal animal) {
        if (animal.myFood.isAlive) {
            animal.moveToward(animal.myFood.getX(), animal.myFood.getY());
            if (animal.myFood.getX() == animal.getX() && animal.myFood.getY() == animal.getY()) {
                animal.myFoodMenu.remove(animal.myFood);
                animal.model.deleteMePlease(animal.myFood);
                animal.reduceHunger();
                animal.myFood = null;
                animal.currentState = animal.notSet;//todo make him eat agian around three times then make it not set
            }
        }else{
            animal.myFoodMenu.remove(animal.myFood);
            animal.myFood = null;
            animal.currentState = animal.searchFood;
        }
    }

    @Override
    public String getStateName() {
        return "going to food";
    }
}
