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
    final List<Animal> femaleAnimals;
    private final List<Plant> plants;

    private final Model model;

    public Generator(Model model) {
        this.model = model;
        cells = new Cell[Const.M][Const.N];
        animals = new CopyOnWriteArrayList<>();
        femaleAnimals = new CopyOnWriteArrayList<>();
        plants = new CopyOnWriteArrayList<>();
    }

    public void addRandomPlant() {
        Plant plant = new Plant(model);
        plants.add(plant);
    }

    public List<Plant> generatePlants() {
        //create plants
        for (int i = 0; i < 1000; i++) {
            addRandomPlant();
        }
        return plants;
    }

    Animal createFemaleHerbivore() {
        return new FemaleAnimal<>(model, Herbivore.getInstance());
    }

    Animal createFemaleOmnivore() {
        return new FemaleAnimal<>(model, Omnivore.getInstance());
    }

    Animal createFemaleCarnivore() {
        return new FemaleAnimal<>(model, Carnivore.getInstance());
    }

    Animal createMaleHerbivore() {
        return new MaleAnimal<>(model, Herbivore.getInstance());
    }

    Animal createMaleOmnivore() {
        return new MaleAnimal<>(model, Omnivore.getInstance());
    }

    Animal createMaleCarnivore() {
        return new MaleAnimal<>(model, Carnivore.getInstance());
    }

    Animal createRandomFemaleAnimal() {
        switch (Utils.getRandom(0, 3)) {
            case 0:
                return createFemaleCarnivore();
            case 1:
                return createFemaleHerbivore();
            default:
                return createFemaleOmnivore();
        }
    }

    Animal createRandomMaleAnimal() {
        switch (Utils.getRandom(0, 3)) {
            case 0:
                return createMaleCarnivore();
            case 1:
                return createMaleHerbivore();
            default:
                return createMaleOmnivore();
        }
    }

    public Animal createRandomAnimal() {
        if (Utils.getRandom(0, 2) == 0) {
            return createRandomFemaleAnimal();
        }
        return createRandomMaleAnimal();
    }

    public List<Animal> generateAnimals() {
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            FemaleAnimal<Herbivore> animal = new FemaleAnimal<>(model, Herbivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 1; i++) {
            FemaleAnimal<Omnivore> animal = new FemaleAnimal<>(model, Omnivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 10; i++) {
            FemaleAnimal<Carnivore> animal = new FemaleAnimal<>(model, Carnivore.getInstance());
            tempAnimals.add(animal);
        }
        femaleAnimals.addAll(tempAnimals);
        //create male male ANIMALS
        for (int i = 0; i < 10; i++) {
            MaleAnimal<Carnivore> animal = new MaleAnimal<>(model, Carnivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 1000; i++) {
            MaleAnimal<Herbivore> animal = new MaleAnimal<>(model, Herbivore.getInstance());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 1; i++) {
            MaleAnimal<Omnivore> animal = new MaleAnimal<>(model, Omnivore.getInstance());
            tempAnimals.add(animal);
        }
        animals.addAll(tempAnimals);
        return animals;
    }

    public Cell[][] generateSells() {
        //create cells
        for (int i = 0; i < Const.M; ++i) {
            for (int j = 0; j < Const.N; ++j) {
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }

}
