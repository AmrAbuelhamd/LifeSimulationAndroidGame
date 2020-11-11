package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.List;

public class Model {
    public final Cell[][] cells;
    public final List<Animal> animals;
    public final List<Plant> plants;
    private final String tag = "model";
    private FamousAnimal famousAnimal;
    private final FantasticColors fantasticColors;
    private OnScreenInfo onScreenInfo;

    public Model(Context context) {
        Log.i(tag, "ececuted one time");
        fantasticColors = new FantasticColors(context);
        DataGenerator dataGenerator = new DataGenerator(this);
        onScreenInfo = new OnScreenInfo();

        cells = dataGenerator.generateSells();
        animals = dataGenerator.generateAnimals();
        plants = dataGenerator.generatePlants();

    }

    public void addOnePlant() {
        int randomIndex = Utils.getRandom(0, plants.size());
        Plant randomPlant = plants.get(randomIndex);
        Plant plant = new Plant(randomPlant, this);
        plants.add(plant);
    }

    public void setFamousAnimal(Animal animal) {
        if (animal == null)
            famousAnimal = null;
        else
            this.famousAnimal = new FamousAnimal(animal);
    }

    public void deleteMePlease(Animal animal) {
//        iAmLeavingThisCell(animal.getX(), animal.getY(), animal);
        animals.remove(animal);
    }

    public void removePlant(Plant plant) {
        cells[Utils.getRowIndex(plant.y)][Utils.getColumnIndex(plant.x)].removeMeFromHere(plant);
        plants.remove(plant);
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
        onScreenInfo.update(animals.size(), plants.size());
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


    public void draw(Canvas canvas) {
        animals.forEach(animal -> animal.draw(canvas));
        plants.forEach(plant -> plant.draw(canvas));
        if (famousAnimal != null)
            famousAnimal.draw(canvas);

        onScreenInfo.draw(canvas);
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

    public void addChild(Animal animal) {
        animals.add(animal);
    }

    public void putMeHerePlease(int x, int y, GameObject gameObject) {
        Log.i(tag, "put object here i: " + Utils.getRowIndex(y) + " j " + Utils.getColumnIndex(x));
        cells[Utils.getRowIndex(y)][Utils.getColumnIndex(x)].putMeHerePlease(gameObject);
    }

    public List<GameObject> getObjectResidingHere(int i, int j) {
        //Log.i(tag,  "get object from cell i "+ i + " j " + j);
        return cells[i][j].getObjectResidingHere();
    }

    public void iAmLeavingThisCell(int x, int y, Animal animal) {

        Log.i(tag, "animal is leaving i: " + Utils.getRowIndex(y) + " j " + Utils.getColumnIndex(x));
        Log.i(tag, "animal is leaving x: " +x + " y " + y);
        cells[Utils.getRowIndex(y)][Utils.getColumnIndex(x)].removeMeFromHere(animal);
    }
}
