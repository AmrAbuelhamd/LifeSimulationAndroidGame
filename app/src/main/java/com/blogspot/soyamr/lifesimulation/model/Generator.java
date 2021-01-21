package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.OpenSimplexNoise;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GroundType;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
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
        Plant plant = new Carrot.Builder()
                .setCoordinates(-1, -1)
                .setGender(GenderEnum.BOTH)
                .setImage(model.gameBitmaps.carrotImg)
                .setType(Type.CARROT)
                .setModel(model)
                .build();
        plants.add(plant);
        plant = new Oat.Builder()
                .setCoordinates(-1, -1)
                .setGender(GenderEnum.BOTH)
                .setImage(model.gameBitmaps.oatImg)
                .setType(Type.OAT)
                .setModel(model)
                .build();
        plants.add(plant);
        plant = new Apple.Builder()
                .setCoordinates(-1, -1)
                .setGender(GenderEnum.BOTH)
                .setImage(model.gameBitmaps.appleImg)
                .setType(Type.APPLE)
                .setModel(model)
                .build();
        plants.add(plant);
    }

    public List<Plant> generatePlants() {
        int r;
        for (Integer[] i : ind) {
            r = Utils.getRandom(0, 15);
            if (r == 0)
                plants.add(
                        new Carrot.Builder()
                                .setCoordinates(i[1], i[0])
                                .setGender(GenderEnum.BOTH)
                                .setImage(model.gameBitmaps.carrotImg)
                                .setType(Type.CARROT)
                                .setModel(model)
                                .build()
                );
            else if (r == 1)
                plants.add(

                        new Apple.Builder()
                                .setCoordinates(i[1], i[0])
                                .setGender(GenderEnum.BOTH)
                                .setImage(model.gameBitmaps.appleImg)
                                .setType(Type.CARROT)
                                .setModel(model)
                                .build()
                );
            else
                plants.add(

                        new Oat.Builder()
                                .setCoordinates(i[1], i[0])
                                .setGender(GenderEnum.BOTH)
                                .setImage(model.gameBitmaps.oatImg)
                                .setType(Type.CARROT)
                                .setModel(model)
                                .build()
                );
        }
        return plants;
    }

    public List<Animal> generateAnimals() {
        int totalForEach = 100;
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Fox.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.FOX))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.foxImg)
                    .setType(Type.FOX)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Fox.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.FOX))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.foxImgF)
                    .setType(Type.FOX)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }

        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Lion.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.lionImg)
                    .setType(Type.LION)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Lion.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.lionImgF)
                    .setType(Type.LION)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Wolf.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.wolfImg)
                    .setType(Type.WOLF)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Wolf.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.wolfImgF)
                    .setType(Type.WOLF)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }
        //Herb
        for (int i = 0; i < 20; i++) {//20
            Animal animal = new Deer.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.deerImg)
                    .setType(Type.DEER)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Deer.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.deerImgF)
                    .setType(Type.DEER)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Mouse.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.mouseImg)
                    .setType(Type.MOUSE)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Mouse.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.mouseImgF)
                    .setType(Type.MOUSE)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }

        for (int i = 0; i < 20; i++) {//20
            Animal animal = new Rabbit.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.rabbitImg)
                    .setType(Type.RABBIT)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Rabbit.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.rabbitImgF)
                    .setType(Type.RABBIT)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }
        //omni
        for (int i = 0; i < totalForEach; i++) {//20
            Animal animal = new Bear.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.bearImg)
                    .setType(Type.BEAR)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new Bear.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.bearImgF)
                    .setType(Type.BEAR)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
        }
//        for (int i = 0; i < 20; i++) {//20
//            Animal animal = new Pig(-1, -1, model, GenderEnum.MALE);
//            tempAnimals.add(animal);
//            animal = new Pig(-1, -1, model, GenderEnum.FEMALE);
//            tempAnimals.add(animal);
//        }
//        for (int i = 0; i < totalForEach; i++) {//20
//            Animal animal = new Raccoon(-1, -1, model, GenderEnum.MALE);
//            tempAnimals.add(animal);
//            animal = new Raccoon(-1, -1, model, GenderEnum.FEMALE);
//            tempAnimals.add(animal);
//        }
        //person
        for (int i = 0; i < 100; i++) {//20
            Animal animal = new MalePerson.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.MALE)
                    .setImage(model.gameBitmaps.personImg)
                    .setType(Type.PERSON)
                    .setModel(model)
                    .build();
            tempAnimals.add(animal);
            animal = new MalePerson.Builder()
                    .setCoordinates(-1, -1)
                    .setFoodTypeList(List.of(Type.LION))
                    .setGender(GenderEnum.FEMALE)
                    .setImage(model.gameBitmaps.personImgF)
                    .setType(Type.PERSON)
                    .setModel(model)
                    .build();
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
