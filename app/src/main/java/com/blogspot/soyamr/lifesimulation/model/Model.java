package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Model {
    public final List<Cell> cells;
    public final List<Animal> animals;
    public final List<Animal> femaleAnimals;
    public final Map<String, Plant> plants;
    private FamousAnimal famousAnimal;
    private int queueTracker = 0;
    static Context context;

    public Model(Context context) {
        this.context = context;
        cells = new CopyOnWriteArrayList<>();
        animals = new CopyOnWriteArrayList<>();
        femaleAnimals = new CopyOnWriteArrayList<>();
        plants = new ConcurrentHashMap<>();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(1500);
        paint.setAntiAlias(true);


//        addSells();
        addAnimals();
        addPlants();
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
        //CREATE FEMALE ANIMALS
        List<Animal> tempAnimals = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            FemaleAnimal animal = new FemaleAnimal(this);
            tempAnimals.add(animal);
        }
        femaleAnimals.addAll((tempAnimals));
        //create male ANIMALS
        for (int i = 0; i < 50; i++) {
            MaleAnimal animal = new MaleAnimal(this);
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

    public void increaseAnimalsHunger() {
        animals.forEach(Animal::increaseHunger);
    }

    public void updateLogInfo() {
        Log.i("number of animals: ", " " + animals.size());
        Log.i("number of plants: ", " " + plants.size());
        Log.i("----------------", " ------------------------");
    }
    final int addingNewPlantThreshold = 20;
    int anpth = 0;

    public void update() {
//        if (queueTracker > animals.size())
//            queueTracker = 0;
//        queueTracker += QUEUE;
//        final int start = queueTracker - QUEUE;
//        final int end = queueTracker;
//        int ctr = 0;
//        for (Animal animal : animals) {
//            animal.myTurn = ctr >= start && ctr <= end;
//            ++ctr;
//            animal.update();
//        }
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
        if (famousAnimal != null)////lidia if needed i can easily add list of this class, to show multiple animal status at a time.
            famousAnimal.draw(canvas);

        canvas.drawText("animals population: " + animals.size(), 0, -2000, paint);
        canvas.drawText("plants population: " + plants.size(), 0, -500, paint);
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
