package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.List;
import java.util.Random;

public class Model {
    public final Cell[][] cells;
    public final List<Animal> animals;
    public final List<Plant> plants;
    private final String tag = "model";
    private FamousAnimal famousAnimal;
    private final FantasticColors fantasticColors;
    private OnScreenInfo onScreenInfo;

    public Model(Context context) {
        fantasticColors = new FantasticColors(context);
        DataGenerator dataGenerator = new DataGenerator(this);
        onScreenInfo = new OnScreenInfo();

        cells = dataGenerator.generateSells();
        animals = dataGenerator.generateAnimals();
        plants = dataGenerator.generatePlants();

    }

    public void addOnePlant() {
        Plant randomPlant = plants.get(Utils.getRandom(0, plants.size()));
        Plant plant = new Plant(randomPlant, this);
        plants.add(plant);

    }

    Plant getRandomSetElement() {
        int size = plants.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for (Plant obj : plants) {
            if (i == item)
                return obj;
            i++;
        }
        return null;
    }

    public void setFamousAnimal(Animal animal) {
        if (animal == null)
            famousAnimal = null;
        else
            this.famousAnimal = new FamousAnimal(animal);
    }

    public void deleteMePlease(Animal animal) {
//        iAmLeavingThisCell(animal.getX(), animal.getY(), animal);
        //todo add explosion effect which will be class that will work for a couple of seconds
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

    public void update(Rect clipBoundsCanvas, float mScaleFactor) {
        onScreenInfo.update(animals.size(), plants.size(), clipBoundsCanvas, mScaleFactor);
        if (!plants.isEmpty())
            if (anpth < addingNewPlantThreshold) {
                ++anpth;
            } else {
                addOnePlant();
                anpth = 0;
            }
        animals.forEach(Animal::update);
        if (famousAnimal != null)
            famousAnimal.update(mScaleFactor);
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
        cells[Utils.getRowIndex(y)][Utils.getColumnIndex(x)].putMeHerePlease(gameObject);
    }

    public List<GameObject> getObjectResidingHere(int i, int j) {
        return cells[i][j].getObjectResidingHere();
    }

    public void iAmLeavingThisCell(int x, int y, Animal animal) {
        cells[Utils.getRowIndex(y)][Utils.getColumnIndex(x)].removeMeFromHere(animal);
    }
}
