package com.blogspot.soyamr.lifesimulation.model;

import com.blogspot.soyamr.lifesimulation.Const;
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
        for (int i = 0; i < 50; i++) {
            addRandomPlant();
        }
        return plants;
    }

    public List<Animal> generateAnimals() {
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FemaleAnimal<Herbivore> animal = new FemaleAnimal<>(model, new Herbivore());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 100; i++) {
            FemaleAnimal<Omnivore> animal = new FemaleAnimal<>(model, new Omnivore());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 50; i++) {
            FemaleAnimal<Carnivore> animal = new FemaleAnimal<>(model, new Carnivore());
            tempAnimals.add(animal);
        }
        femaleAnimals.addAll(tempAnimals);
        //create male male ANIMALS
        for (int i = 0; i < 10; i++) {
            MaleAnimal<Carnivore> animal = new MaleAnimal<>(model, new Carnivore());
            tempAnimals.add(animal);
        }
        for (int i = 0; i < 100; i++) {
            MaleAnimal<Herbivore> animal = new MaleAnimal<>(model, new Herbivore());
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
