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
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Apple;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Carrot;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Oat;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.FamousAnimal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.OnScreenInfo;
import com.blogspot.soyamr.lifesimulation.model.game_elements.special_events.Explosion;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Model {
    public final Cell[][] cells;
    public final List<Animal> animals;
    public final List<Plant> plants;
    public final Generator generator;
    final int addingNewPlantThreshold = 20;
    private final String tag = "model";
    private final OnScreenInfo onScreenInfo;
    private final Context context;
    private final List<Explosion> explosionList;
    int anpth = 0;
    int disasterThreshold = 30;
    int dth = 0;
    Bitmap bitmap;
    int deleteGhostAnimalThreshold = 1000;
    int dgath = 0;
    private FamousAnimal famousAnimal;

    public Model(Context context) {
        explosionList = new LinkedList<>();
        generator = new Generator(this);
        onScreenInfo = new OnScreenInfo();
        this.context = context;
        cells = generator.generateSells();
        animals = generator.generateAnimals();
        plants = generator.generatePlants();
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

    public void deleteMePlease(Animal animal) {
        iAmLeavingThisCell(animal.getX(), animal.getY(), animal);
        //todo add explosion effect which will be class that will work for a couple of seconds
        animals.remove(animal);
    }

    public void removePlant(Plant plant) {
        iAmLeavingThisCell(plant.getX(), plant.getY(), plant);
        plant.isAlive = false;
        plants.remove(plant);
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
        explosionList.forEach(Explosion::update);
        if (famousAnimal != null)
            famousAnimal.update(mScaleFactor);

        ListIterator<Explosion> iterator = explosionList.listIterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            if (explosion.isFinish()) {
                iterator.remove();
            }
        }
//        showGhostAnimals();
    }

    private void updateGameParameters() {
        //delete ghost animals
//        if (dgath < deleteGhostAnimalThreshold) {
//            ++dgath;
//        } else {
//            deleteGhostAnimals();
//            dgath = 0;
//        }
        //create disaster
        if (dth < disasterThreshold) {
            ++dth;
        } else {
            // Create Explosion object.
            createExplosionObject();
            disasterThreshold = Utils.getRandom(20, 30);
            dth = 0;
        }
        //add new plant
        if (!plants.isEmpty())
            if (anpth < addingNewPlantThreshold) {
                ++anpth;
            } else {
                addOnePlant();
                anpth = 0;
            }
    }

    private void createExplosionObject() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);
        if (bitmap != null) {
            Explosion explosion = new Explosion(this, bitmap, Utils.getRandom(0, Const.N) * Const.CELL_WIDTH,
                    Utils.getRandom(0, Const.M) * Const.CELL_HEIGHT, 5, 5);
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
                poorObjects.forEach(ob -> {
                    ob.isAlive = false;
                    if (ob instanceof Animal) {
                        deleteMePlease((Animal) ob);
                    } else
                        removePlant((Plant) ob);
                });
            }
        }
    }

    private void deleteGhostAnimals() {
        for (int i = 0; i < Const.M; ++i) {
            for (int j = 0; j < Const.N; ++j) {
                cells[i][j].update();
            }
        }
    }


    public void draw(Canvas canvas) {
        animals.forEach(animal -> animal.draw(canvas));
        plants.forEach(plant -> plant.draw(canvas));
        explosionList.forEach(explosion -> explosion.draw(canvas));

        if (famousAnimal != null)
            famousAnimal.draw(canvas);

        onScreenInfo.draw(canvas);
    }

    public void controlBirthPlease() {
        if (animals.size() > 300) {
            GameObject.SEARCH_PARTNER_THRESHOLD = Const.SEARCH_PARTNER_THRESHOLD_PROHIBIT;
            GameObject.SEARCH_FOOD_THRESHOLD = Const.SEARCH_FOOD_THRESHOLD_HIGH;
            ;
        } else if (animals.size() < 100) {
            GameObject.SEARCH_PARTNER_THRESHOLD = Const.SEARCH_PARTNER_THRESHOLD_NORMAL;
            GameObject.SEARCH_FOOD_THRESHOLD = Const.SEARCH_FOOD_THRESHOLD_NORMAL;
        }
        System.out.println("#################hi from birth control#################");
    }

    public void addChild(Animal animal) {//should be added to concrete place on the map
        animals.add(animal);
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

    public void removeObjectFromMap(GameObject prey) {
        cells[Utils.getRowIdx(prey.getY())][Utils.getColIdx(prey.getX())].removeMeFromHere(prey);
        prey.isAlive = false;
        if (prey instanceof Animal) {
            animals.remove(prey);
        } else
            plants.remove((Plant) prey);
    }

    public void showGhostAnimals() {//need optimization
        if (animals.size() < 20) {
            Log.i(tag, "**** " + animals.size());
            //create cells
            for (int i = 0; i < Const.M; ++i) {
                for (int j = 0; j < Const.N; ++j) {
                    for (GameObject gameObjects : cells[i][j].getObjectResidingHere())
                        Log.i(tag, " " + gameObjects);
                }
            }
        }
    }
}
