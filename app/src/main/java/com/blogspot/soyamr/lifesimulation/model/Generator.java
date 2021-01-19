package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Fox;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Lion;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Wolf;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Deer;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Mouse;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Bear;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Pig;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Raccoon;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.FemalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Apple;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Carrot;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Oat;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    private final Cell[][] cells;
    private final List<Animal> animals;
    private final List<Plant> plants;

    private final Model model;

    public Generator(Model model) {
        this.model = model;
        cells = new Cell[Const.M][Const.N];
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addRandomPlant() {
        Plant plant = new Carrot(-1, -1, model);
        plants.add(plant);
        plant = new Oat(-1, -1, model);
        plants.add(plant);
        plant = new Apple(-1, -1, model);
        plants.add(plant);
    }

    public List<Plant> generatePlants() {
        //create plants
        for (int i = 0; i < 5000; i++) {//500
            addRandomPlant();
        }
        return plants;
    }

    public List<Animal> generateAnimals() {
        int totalForEach = 2;
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Fox(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Fox(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }

        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Lion(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Lion(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Wolf(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Wolf(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //Herb
        for (int i = 0; i < 20; i++) {//20
            Animal animal = new Deer(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Deer(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Mouse(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Mouse(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }

        for (int i = 0; i < 20; i++) {//20
            Animal animal = new Rabbit(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Rabbit(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //omni
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Bear(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Bear(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 20; i++) {//20
            Animal animal = new Pig(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Pig(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Raccoon(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Raccoon(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //person
        for (int i = 0; i < 100; i++) {//20
            Animal animal = new MalePerson(-1, -1, model, GenderEnum.MALE, true);
            tempAnimals.add(animal);
            animal = new FemalePerson(-1, -1, model, GenderEnum.FEMALE, true);
            tempAnimals.add(animal);
        }


        animals.addAll(tempAnimals);
        return animals;
    }

    public Cell[][] generateSells() {
        //create cells
        for (int i = 0; i < Const.M; ++i) {
            for (int j = 0; j < Const.N; ++j) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return cells;
    }

}
