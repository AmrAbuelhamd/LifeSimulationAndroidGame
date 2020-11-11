package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataGenerator {

    private final Cell[][] cells;
    private final List<Animal> animals;
    private final List<Plant> plants;

    private final Model model;

    public DataGenerator(Model model) {
        this.model = model;
        cells = new Cell[Utils.Const.M][Utils.Const.N];
        animals = new CopyOnWriteArrayList<>();
        plants = new CopyOnWriteArrayList<>();
    }

    public void addRandomPlant() {
        Plant plant = new Plant(model);
        plants.add(plant);
    }

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
        for (int i = 0; i < 5; i++) {
            FemaleAnimal animal = new FemaleAnimal(model);
            tempAnimals.add(animal);
        }
        //create male male ANIMALS
        for (int i = 0; i < 50; i++) {
            MaleAnimal animal = new MaleAnimal(model);
            tempAnimals.add(animal);
        }
        animals.addAll(tempAnimals);
        return animals;
    }

    public Cell[][] generateSells() {
        //create cells
        for (int i = 0; i < Utils.Const.M; ++i) {
            for (int j = 0; j < Utils.Const.N; ++j) {
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }

}
