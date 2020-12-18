package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Apple;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Carrot;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Oat;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.FamousAnimal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.OnScreenInfo;
import com.blogspot.soyamr.lifesimulation.model.game_elements.special_events.Explosion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Model {
    public final Cell[][] cells;//todo impo use key-value try
    public final List<Animal> animals;
    //todo impo delete every 150 tiks -> just try
    public final List<Plant> plants;
    public final Generator generator;
    final int addingNewPlantThreshold = 20;
    private final String tag = "model";
    private final OnScreenInfo onScreenInfo;
    private final List<Explosion> explosionList;
    int anpth = 0;
    int disasterThreshold = 30;
    int dth = 0;
    Bitmap bitmap;
    int deleteGhostAnimalThreshold = 1000;
    int dgath = 0;
    List<GameObject> objectsToRemove = new ArrayList<>(400);
    List<Animal> animalsToAdd = new ArrayList<>(400);
    int deleteDeadTH = 150;
    int ddth = 0;
    private FamousAnimal famousAnimal;
    private List<HomeSweetHome> homeList;

    public Model(Context context) {
        explosionList = new ArrayList<>();
        generator = new Generator(this);
        onScreenInfo = new OnScreenInfo();
        homeList = new ArrayList<>();
        cells = generator.generateSells();
        animals = generator.generateAnimals();
        plants = generator.generatePlants();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);
    }

    public void addOnePlant() {
        Plant randomPlant = plants.get(Utils.getRandom(0, plants.size()));
        Plant plant;
        if (Utils.getRandom(0, 3) == 0)
            plant = new Carrot(randomPlant.getX(), randomPlant.getY(), this);
        else if (Utils.getRandom(0, 3) == 1)
            plant = new Apple(randomPlant.getX(), randomPlant.getY(), this);
        else
            plant = new Oat(randomPlant.getX(), randomPlant.getY(), this);

        plants.add(plant);
    }

    public void setFamousAnimal(GameObject animal) {
        if (animal == null)
            famousAnimal = null;
        else
            this.famousAnimal = new FamousAnimal(animal);
    }

    public void deleteMePlease(GameObject object) {
        iAmLeavingThisCell(object.getX(), object.getY(), object);
        object.isAlive = false;
        objectsToRemove.add(object);

    }

    public void updateLogInfo() {
        Log.i("number of animals: ", " " + animals.size());
        Log.i("number of plants: ", " " + plants.size());
        Log.i("----------------", " ------------------------");
    }

    public void update(Rect clipBoundsCanvas, float mScaleFactor) {
        updateGameParameters();
        onScreenInfo.update(animals.size(), 0, plants.size(), clipBoundsCanvas, mScaleFactor);
        animals.forEach(Animal::update);
        if (famousAnimal != null)
            famousAnimal.update(mScaleFactor);

        ListIterator<Explosion> iterator = explosionList.listIterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            if (explosion.isFinish()) {
                iterator.remove();
            }
        }
        explosionList.forEach(Explosion::update);
        animals.addAll(animalsToAdd);
//        showGhostAnimals();
    }

    private void updateGameParameters() {
        //create disaster
        if (dth < disasterThreshold) {
            ++dth;
        } else {
            // Create Explosion object.
            createExplosionObject();
            disasterThreshold = Utils.getRandom(10, 30);
            dth = 0;
        }
        //add new plant
        if (!plants.isEmpty()) {
            if (anpth < addingNewPlantThreshold) {
                ++anpth;
            } else {
                addOnePlant();
                anpth = 0;
            }
        }
        if (ddth < deleteDeadTH) {
            ++ddth;
        } else {
            objectsToRemove.forEach(deadObject -> {
                if (deadObject instanceof Animal)
                    animals.remove(deadObject);
                else if (deadObject instanceof Plant)
                    plants.remove(deadObject);
            });
            objectsToRemove.clear();
            ddth = 0;
        }
    }

    private void createExplosionObject() {
        if (bitmap != null) {
            Explosion explosion = new Explosion(this, bitmap.copy(bitmap.getConfig(), true),
                    Math.max(0, Utils.getRandom(0, Const.N) * Const.CELL_WIDTH - bitmap.getWidth() / 5),
                    Math.max(0, Utils.getRandom(0, Const.M) * Const.CELL_HEIGHT - bitmap.getHeight() / 5), 5, 5);
            this.explosionList.add(explosion);
        }
    }

    public void removeObjectsInThisArea(int x, int y, int xEnd, int yEnd) {
        int i = Utils.getRowIdx(y);
        int jStart = Utils.getColIdx(x);
        i = Math.max(i, 0);
        jStart = Math.max(jStart, 0);

        int iEnd = Utils.getRowIdx(yEnd);
        int jEnd = Utils.getColIdx(xEnd);

        iEnd = Math.min(iEnd, Const.M);
        jEnd = Math.min(jEnd, Const.N);

        for (; i < iEnd; ++i) {
            for (int j = jStart; j < jEnd; ++j) {
                List<GameObject> poorObjects = cells[i][j].getObjectResidingHere();
                poorObjects.forEach(deadObject -> {
                    deadObject.isAlive = false;
                    if (deadObject instanceof Animal)
                        animals.remove(deadObject);
                    else if (deadObject instanceof Plant)
                        plants.remove(deadObject);
                });
                cells[i][j].clear();
            }
        }
    }

    public void draw(Canvas canvas) {

        animalsToAdd.clear();

        HomeSweetHome[] homeSweetHomes = homeList.toArray(new HomeSweetHome[0]);
        for (HomeSweetHome h : homeSweetHomes) {
            h.draw(canvas);
        }
        Plant[] plants1 = plants.toArray(new Plant[0]);
        for (Plant plant : plants1) {
            if (plant != null)
                plant.draw(canvas);
        }
        Animal[] animals1 = animals.toArray(new Animal[0]);
        for (Animal animal : animals1) {
            if (animal != null)
                animal.draw(canvas);
        }
        Explosion[] explosions = explosionList.toArray(new Explosion[0]);
        for (Explosion explosion : explosions) {
            explosion.draw(canvas);
        }

        if (famousAnimal != null)
            famousAnimal.draw(canvas);

        onScreenInfo.draw(canvas);
    }

    public void addChild(Animal animal) {//should be added to concrete place on the map
        animalsToAdd.add(animal);
    }

    public void putMeHerePlease(int x, int y, GameObject gameObject) {
        cells[Utils.getRowIdx(y)][Utils.getColIdx(x)].putMeHerePlease(gameObject);
    }

    public List<GameObject> getObjectResidingHere(int i, int j) {
        return cells[i][j].getObjectResidingHere();
    }

    public void iAmLeavingThisCell(int x, int y, GameObject gameObject) {
        cells[Utils.getRowIdx(y)][Utils.getColIdx(x)].removeMeFromHere(gameObject);
    }

    public void addHome(HomeSweetHome homeSweetHome) {
        homeList.add(homeSweetHome);
    }
}
