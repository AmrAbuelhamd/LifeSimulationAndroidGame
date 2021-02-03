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

    int totalForEach = 10;


    public Generator(Model model) {
        this.model = model;
        cells = new Cell[Const.M][Const.N];
        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    public void addRandomPlant() {
        Plant plant;
        plant = new Carrot.Builder()
                .setModel(model)
                .build();
        plants.add(plant);
        plant = new Oat.Builder()
                .setModel(model)
                .build();
        plants.add(plant);
        plant = new Apple.Builder()
                .setModel(model)
                .build();
        plants.add(plant);
    }

    public List<Plant> generatePlants() {
        int r;
        for (Integer[] i : ind) {
            if (Utils.getRandom(0, 100) < 15) {
                r = Utils.getRandom(0, 3);
                if (r == 0)
                    plants.add(
                            new Carrot.Builder()
                                    .setCoordinates(i[1], i[0])
                                    .setModel(model)
                                    .build()
                    );
                else if (r == 1)
                    plants.add(

                            new Apple.Builder()
                                    .setCoordinates(i[1], i[0])
                                    .setModel(model)
                                    .build()
                    );
                else
                    plants.add(
                            new Oat.Builder()
                                    .setCoordinates(i[1], i[0])
                                    .setModel(model)
                                    .build()
                    );
            }
        }
        return plants;

//        for (int i = 0; i < 1; i++) {
//            addRandomPlant();
//        }
//        return plants;
    }

    public List<Animal> generateAnimals() {
        List<Animal> tempAnimals = new ArrayList<>();

        for (int i = 0; i < totalForEach; i++) {
            tempAnimals.addAll(List.of(
                    new Fox.Builder()
                            .setGender(GenderEnum.MALE)
                            .setModel(model)
                            .build()
                    ,
                    new Fox.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setModel(model)
                            .build()
            ));
        }

        for (int i = 0; i < totalForEach; i++) {//20
            tempAnimals.addAll(List.of(
                    new Lion.Builder()
                            .setGender(GenderEnum.MALE)
                            .setModel(model)
                            .build(),
                    new Lion.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setModel(model)
                            .build()));
        }
        for (int i = 0; i < totalForEach; i++) {//20
            tempAnimals.addAll(List.of(
                    new Wolf.Builder()
                            .setGender(GenderEnum.MALE)
                            .setModel(model)
                            .build(),
                    new Wolf.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setModel(model)
                            .build()
            ));
        }
        //Herb
        for (int i = 0; i < totalForEach * 3; i++) {//20
            tempAnimals.addAll(List.of(
                    new Deer.Builder()
                            .setGender(GenderEnum.MALE)
                            .setModel(model)
                            .build(),
                    new Deer.Builder()
                            .setGender(GenderEnum.FEMALE)
                            .setModel(model)
                            .build()));
        }
        for (int i = 0; i < totalForEach * 3; i++) {//20
            tempAnimals.addAll(List.of(
                    new Mouse.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.MALE)
                            .build(),
                    new Mouse.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build()));
        }

        for (int i = 0; i < totalForEach * 4; i++) {//20
            tempAnimals.addAll(List.of(
                    new Rabbit.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.MALE)
                            .build(),
                    new Rabbit.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build()));
        }
        //omni
        for (int i = 0; i < totalForEach; i++) {//20
            tempAnimals.addAll(List.of(
                    new Bear.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.MALE)
                            .build(),
                    new Bear.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build()));

        }
        for (int i = 0; i < totalForEach * 3*15; i++) {//20
            tempAnimals.addAll(List.of(
                    new Pig.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.MALE)
                            .build(),

                    new Pig.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build()));

        }
        for (int i = 0; i < totalForEach * 2; i++) {//20
            tempAnimals.addAll(List.of(
                    new Raccoon.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build(),
                    new Raccoon.Builder()
                            .setModel(model)
                            .setGender(GenderEnum.FEMALE)
                            .build()));
        }
        //person
        for (int i = 0; i < totalForEach * 10; i++) {//20
            tempAnimals.addAll(List.of(
                    new MalePerson.Builder()
                            .setModel(model)
                            .isFirstGeneration(true)
                            .build(),
                    new FemalePerson.Builder()
                            .setModel(model)
                            .isFirstGeneration(true)
                            .build()));
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
