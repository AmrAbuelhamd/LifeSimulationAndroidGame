package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Person;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Carnivore;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Fox;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Lion;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Wolf;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Deer;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Herbivore;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Mouse;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Bear;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Pig;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore.Raccoon;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Apple;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Carrot;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Oat;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Generator {

    private final Cell[][] cells;
    private final List<Animal> animals;
    private final List<Plant> plants;

    private final Model model;

    public Generator(Model model) {
        this.model = model;
        cells = new Cell[Const.M][Const.N];
        animals = new CopyOnWriteArrayList<>();
        plants = new CopyOnWriteArrayList<>();
    }

    public void addRandomPlant() {
        Plant plant = new Carrot(-1, -1, model);
        plants.add(plant);
        plant = new Oat(-1, -1, model);
        plants.add(plant);
        plant = new Apple(-1, -1, model);
        plants.add(plant);
    }

//
//    Animal createFemaleHerbivore(int x, int y) {
//        return new FemaleAnimal<>(x, y, model, Herbivore.getInstance());
//    }
//
//    Animal createFemaleOmnivore(int x, int y) {
//        return new FemaleAnimal<>(x, y, model, Omnivore.getInstance());
//    }
//
//    Animal createFemaleCarnivore(int x, int y) {
//        return new FemaleAnimal<>(x, y, model, Carnivore.getInstance());
//    }
//
//    Animal createMaleHerbivore(int x, int y) {
//        return new MaleAnimal<>(x, y, model, Herbivore.getInstance());
//    }
//
//    Animal createMaleOmnivore(int x, int y) {
//        return new MaleAnimal<>(x, y, model, Omnivore.getInstance());
//    }
//
//    Animal createMaleCarnivore(int x, int y) {
//        return new MaleAnimal<>(x, y, model, Carnivore.getInstance());
//    }
//
//    Animal createRandomFemaleAnimal(int x, int y) {
//        switch (Utils.getRandom(0, 3)) {
//            case 0:
//                return createFemaleCarnivore(x, y);
//            case 1:
//                return createFemaleHerbivore(x, y);
//            default:
//                return createFemaleOmnivore(x, y);
//        }
//    }
//
//    Animal createRandomMaleAnimal(int x, int y) {
//        switch (Utils.getRandom(0, 3)) {
//            case 0:
//                return createMaleCarnivore(x, y);
//            case 1:
//                return createMaleHerbivore(x, y);
//            default:
//                return createMaleOmnivore(x, y);
//        }
//    }
//
//    public Animal createRandomAnimal(int x, int y) {
//        if (Utils.getRandom(0, 2) == 0) {
//            return createRandomFemaleAnimal(x, y);
//        }
//        return createRandomMaleAnimal(x, y);
//    }

    public List<Plant> generatePlants() {
        //create plants
        for (int i = 0; i < 50; i++) {
            addRandomPlant();
        }
        return plants;
    }

    public List<Animal> generateAnimals() {
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Fox(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Fox(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }

        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Lion(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Lion(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Wolf(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Wolf(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //Herb
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Deer(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Deer(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Mouse(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Mouse(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }

        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Rabbit(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Rabbit(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //omni
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Bear(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Bear(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Pig(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Pig(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Raccoon(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Raccoon(-1, -1, model, GenderEnum.FEMALE);
            tempAnimals.add(animal);
        }
        //person
        for (int i = 0; i < 20; i++) {//200
            Animal animal = new Person(-1, -1, model, GenderEnum.MALE);
            tempAnimals.add(animal);
            animal = new Person(-1, -1, model, GenderEnum.FEMALE);
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
