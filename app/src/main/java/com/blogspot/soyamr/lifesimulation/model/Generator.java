package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.OpenSimplexNoise;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GroundType;
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

    private static final double FEATURE_SIZE = 120;
    private final Cell[][] cells;
    private final List<Animal> animals;
    private final List<Plant> plants;
    private final Model model;
    ArrayList<Integer[]> ind = new ArrayList<>();

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
        int r;
        for (Integer[] i : ind) {
            r = Utils.getRandom(0, 15);
            if (r == 0)
                plants.add(new Oat(i[1], i[0], model));
            else if (r == 1)
                plants.add(new Apple(i[1], i[0], model));
            else
                plants.add(new Carrot(i[1], i[0], model));
        }
        return plants;
    }

    public List<Animal> generateAnimals() {
        int totalForEach = 100;
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

        OpenSimplexNoise noise = new OpenSimplexNoise(Utils.getRandom(0, 5000));
        for (int y = 0; y < Const.M; y++) {
            for (int x = 0; x < Const.N; x++) {
                cells[y][x] = new Cell(y, x);
                double value = (int) (noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 2) * 3);

                switch ((int) value) {
                    case (2):
                        //volcano
                        cells[y][x].paint.setColor(Color.parseColor("#D5303E"));
                        cells[y][x].groundType = GroundType.VOLCANO;
                        break;
                    case (1): {
                        //snow
                        cells[y][x].paint.setColor(Color.parseColor("#fffafa"));
                        cells[y][x].groundType = GroundType.SNOW;
                        break;
                    }
                    case (0): {
                        //grass
                        ind.add(new Integer[]{y * Const.CELL_HEIGHT, x * Const.CELL_WIDTH});
                        cells[y][x].paint.setColor(Color.parseColor("#5b8c5a"));
                        cells[y][x].groundType = GroundType.GRASS;
                        break;
                    }
                    case (-1): {
                        //sand
                        cells[y][x].paint.setColor(Color.parseColor("#edc9af"));
                        cells[y][x].groundType = GroundType.SAND;
                        break;
                    }
                    case (-2): {
                        //blu/water
                        cells[y][x].paint.setColor(Color.parseColor("#1ca3ec"));
                        cells[y][x].groundType = GroundType.WATER;
                        break;
                    }
                }
            }
        }

        return cells;
    }

}
