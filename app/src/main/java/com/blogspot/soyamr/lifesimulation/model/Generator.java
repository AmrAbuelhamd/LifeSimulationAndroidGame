package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.FemaleAnimal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.MaleAnimal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Plant;
import com.blogspot.soyamr.lifesimulation.model.types.Carnivore;
import com.blogspot.soyamr.lifesimulation.model.types.Herbivore;
import com.blogspot.soyamr.lifesimulation.model.types.Omnivore;

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
        Plant plant = new Plant(model);
        plants.add(plant);
    }


    Animal createFemaleHerbivore(int x, int y) {
        return new FemaleAnimal<>(x, y, model, Herbivore.getInstance());
    }

    Animal createFemaleOmnivore(int x, int y) {
        return new FemaleAnimal<>(x, y, model, Omnivore.getInstance());
    }

    Animal createFemaleCarnivore(int x, int y) {
        return new FemaleAnimal<>(x, y, model, Carnivore.getInstance());
    }

    Animal createMaleHerbivore(int x, int y) {
        return new MaleAnimal<>(x, y, model, Herbivore.getInstance());
    }

    Animal createMaleOmnivore(int x, int y) {
        return new MaleAnimal<>(x, y, model, Omnivore.getInstance());
    }

    Animal createMaleCarnivore(int x, int y) {
        return new MaleAnimal<>(x, y, model, Carnivore.getInstance());
    }

    Animal createRandomFemaleAnimal(int x, int y) {
        switch (Utils.getRandom(0, 3)) {
            case 0:
                return createFemaleCarnivore(x, y);
            case 1:
                return createFemaleHerbivore(x, y);
            default:
                return createFemaleOmnivore(x, y);
        }
    }

    Animal createRandomMaleAnimal(int x, int y) {
        switch (Utils.getRandom(0, 3)) {
            case 0:
                return createMaleCarnivore(x, y);
            case 1:
                return createMaleHerbivore(x, y);
            default:
                return createMaleOmnivore(x, y);
        }
    }

    public Animal createRandomAnimal(int x, int y) {
        if (Utils.getRandom(0, 2) == 0) {
            return createRandomFemaleAnimal(x, y);
        }
        return createRandomMaleAnimal(x, y);
    }

    public List<Plant> generatePlants() {
        //create plants
        for (int i = 0; i < 1000; i++) {
            addRandomPlant();
        }
        return plants;
    }
    public List<Animal> generateAnimals() {
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 200; i++) {//200
            FemaleAnimal<Herbivore> animal = new FemaleAnimal<>(-1, -1, model, Herbivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 100; i++) {//100
            FemaleAnimal<Carnivore> animal = new FemaleAnimal<>(-1, -1, model, Carnivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 10; i++) {//10
            FemaleAnimal<Omnivore> animal = new FemaleAnimal<>(-1, -1, model, Omnivore.getInstance());
            tempAnimals.add(animal);
        }
        //create Male ANIMALS
        for (int i = 0; i < 200; i++) {//200
            MaleAnimal<Herbivore> animal = new MaleAnimal<>(-1, -1, model, Herbivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 100; i++) {//100
            MaleAnimal<Carnivore> animal = new MaleAnimal<>(-1, -1, model, Carnivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 10; i++) {
            MaleAnimal<Omnivore> animal = new MaleAnimal<>(-1, -1, model, Omnivore.getInstance());
            tempAnimals.add(animal);
        }

        animals.addAll(tempAnimals);
        return animals;
    }

    public Cell[][] generateSells() {
        //create cells
        for (int i = 0; i < Const.M; ++i) {
            for (int j = 0; j < Const.N; ++j) {
                cells[i][j] = new Cell(i,j);
            }
        }
        return cells;
    }

}
