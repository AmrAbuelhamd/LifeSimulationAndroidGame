package com.blogspot.soyamr.lifesimulation.model;

import android.view.Display;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataGenerator {

    private final List<Cell> cells;
    private final List<Animal> animals;
    private final List<Animal> femaleAnimals;
    private final Map<String, Plant> plants;

    public List<Cell> getCells() {
        return cells;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Animal> getFemaleAnimals() {
        return femaleAnimals;
    }

    public Map<String, Plant> getPlants() {
        return plants;
    }

    private final Model model;

    public DataGenerator(Model model) {
        this.model = model;
        cells = new CopyOnWriteArrayList<>();
        animals = new CopyOnWriteArrayList<>();
        femaleAnimals = new CopyOnWriteArrayList<>();
        plants = new ConcurrentHashMap<>();
        generatePlants();
        generateAnimals();
    }

    public void addRandomPlant() {
        Plant plant = new Plant();
        plants.put(plant.getKey(), plant);
    }

    public void generatePlants() {
        //create plants
        for (int i = 0; i < 1000; i++) {
            addRandomPlant();
        }
    }

    private void generateAnimals() {
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            FemaleAnimal animal = new FemaleAnimal(model);
            tempAnimals.add(animal);
        }
        femaleAnimals.addAll((tempAnimals));
        //create male ANIMALS
        for (int i = 0; i < 50; i++) {
            MaleAnimal animal = new MaleAnimal(model);
            tempAnimals.add(animal);
        }
        animals.addAll(tempAnimals);
    }

    private void generateSells() {
        //create cells
        List<Cell> tempCells = new ArrayList<>();
        for (int i = 0; i < Utils.Const.M; i++) {
            for (int j = 0; j < Utils.Const.N; j++) {
                Cell cell = new Cell(i, j);
                tempCells.add(cell);
            }
        }
        cells.addAll(tempCells);
    }

}
