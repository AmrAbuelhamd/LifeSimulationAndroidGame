package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Model {
    public final List<Cell> cells;
    public final List<Animal> animals;
    public final List<Animal> femaleAnimals;
    public final Map<String, Plant> plants;
    private FamousAnimal famousAnimal;
    private final FantasticColors fantasticColors;
    private OnScreenInfo onScreenInfo;
    public Model(Context context) {
        fantasticColors = new FantasticColors(context);
        DataGenerator dataGenerator = new DataGenerator(this);
        onScreenInfo = new OnScreenInfo();

        cells = dataGenerator.getCells();
        animals = dataGenerator.getAnimals();
        femaleAnimals = dataGenerator.getFemaleAnimals();
        plants = dataGenerator.getPlants();

    }

    public void addOnePlant() {
        List<Plant> valuesList = new ArrayList<>(plants.values());
        int randomIndex = Utils.getRandom(0, valuesList.size());
        Plant randomPlant = valuesList.get(randomIndex);
        Plant plant = new Plant(randomPlant);
        plants.put(plant.getKey(), plant);
    }

    public void setFamousAnimal(Animal animal) {
        if (animal == null)
            famousAnimal = null;
        else
            this.famousAnimal = new FamousAnimal(animal);
    }

    public void deleteMePlease(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant(String key) {
        plants.remove(key);
    }

    public Map<String, Plant> getPlants() {
        return Collections.unmodifiableMap(plants);
    }

    public void updateLogInfo() {
        Log.i("number of animals: ", " " + animals.size());
        Log.i("number of plants: ", " " + plants.size());
        Log.i("----------------", " ------------------------");
    }

    public int getMeColor(FantasticColors.TYPE type, int level) {
        return fantasticColors.getColor(type, level);
    }

    final int addingNewPlantThreshold = 20;
    int anpth = 0;

    public void update() {
        onScreenInfo.update(animals.size(),plants.size());
        if (anpth < addingNewPlantThreshold) {
            ++anpth;
        } else {
            addOnePlant();
            anpth = 0;
        }
        animals.forEach(Animal::update);
        if (famousAnimal != null)
            famousAnimal.update();
    }

    final Paint paint = new Paint();

    public void draw(Canvas canvas) {
        cells.forEach(cell -> cell.draw(canvas));
        animals.forEach(animal -> animal.draw(canvas));
        plants.forEach((s, plant) -> plant.draw(canvas));
        if (famousAnimal != null)
            famousAnimal.draw(canvas);

        onScreenInfo.draw(canvas);
    }


    public Plant getPlant(String key) {
        return plants.get(key);
    }

    public Animal getAnimal(String key) {
        return animals.stream()
                .filter(s -> key.equals(s.getKey())).findFirst().orElse(null);
    }

    public Animal getSingleFemaleAnimal(String key) {
        return femaleAnimals.stream()
                .filter(s -> key.equals(s.getKey()) &&
                        ((FemaleAnimal) s).wannaBeInRelationship())
                .findFirst().orElse(null);
    }


    public void weHaveChild(int x, int y) {
        if (Utils.getRandom(0, 2) == 0) {
            FemaleAnimal animal = new FemaleAnimal(x, y, this);
            animals.add(animal);
            femaleAnimals.add(animal);
        } else {
            animals.add(new MaleAnimal(x, y, this));
        }
    }

    public void controlBirthPlease() {
        if (animals.size() > 300) {
            GameObject.SEARCH_PARTNER_THRESHOLD = Utils.Const.SEARCH_PARTNER_THRESHOLD_PROHIBIT;
            GameObject.SEARCH_FOOD_THRESHOLD = Utils.Const.SEARCH_FOOD_THRESHOLD_HIGH;
            ;
        } else if (animals.size() < 100) {
            GameObject.SEARCH_PARTNER_THRESHOLD = Utils.Const.SEARCH_PARTNER_THRESHOLD_NORMAL;
            GameObject.SEARCH_FOOD_THRESHOLD = Utils.Const.SEARCH_FOOD_THRESHOLD_NORMAL;
        }
        System.out.println("#################hi from birth control#################");
    }
}
