package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model {
    public final List<Cell> cells;//toAsk how about  List<GameObject> cells; but i can't access the cell specific methods if done so..
    public final List<Animal> animals;
    public final Map<String, Plant> plants;
    private FamousAnimal famousAnimal;

    public void setFamousAnimal(Animal animal) {
        this.famousAnimal = new FamousAnimal(animal);
    }

    public Model() {
        cells = new CopyOnWriteArrayList<>();
        animals = new CopyOnWriteArrayList<>();
        plants = new ConcurrentHashMap<>();

        addSells();
        addAnimals();
        addPlants();
    }

    public Plant getPlant(String key) {
        return plants.get(key);
    }

    public void removePlant(String key) {
        plants.remove(key);
    }

    public Map<String, Plant> getPlants() {
        return Collections.unmodifiableMap(plants);
    }

    public void increaseAnimalsHunger() {
        animals.forEach(Animal::increaseHunger);
    }

    public void addPlants() {
        //create plants
        for (int i = 0; i < 1000; i++) {
            addRandomPlant();
        }
    }

    public void addOnePlant() {
        List<Plant> valuesList = new ArrayList<>(plants.values());
        int randomIndex = Utils.getRandom(0, valuesList.size());
        Plant randomPlant = valuesList.get(randomIndex);
        Plant plant = new Plant(randomPlant);
        plants.put(plant.getKey(), plant);
    }

    public void addRandomPlant() {
        Plant plant = new Plant();
        plants.put(plant.getKey(), plant);
    }

    private void addAnimals() {
        //create ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Animal animal = new Animal(this);
            tempAnimals.add(animal);
        }
        animals.addAll(tempAnimals);
    }

    private void addSells() {
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

    public void updateInfo() {
        Log.i("number of animals: ", " " + animals.size());
        Log.i("number of plants: ", " " + plants.size());
        Log.i("----------------", " ------------------------");
    }

    public void deleteMePlease(Animal animal) {
        animals.remove(animal);

    }

    public void update() {
        animals.forEach(Animal::update);
        if (famousAnimal != null)
            famousAnimal.update();
    }

    public void draw(Canvas canvas) {
        cells.forEach(cell -> cell.draw(canvas));
        animals.forEach(animal -> animal.draw(canvas));
        plants.forEach((s, plant) -> plant.draw(canvas));
        if (famousAnimal != null)
            famousAnimal.draw(canvas);
    }

    public Animal getAnimal(String key) {
        return animals.stream()
                .filter(s -> key.equals(s.getKey())).findFirst().orElse(null);
    }
}
