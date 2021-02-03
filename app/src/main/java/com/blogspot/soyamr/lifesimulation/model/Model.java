package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Cell;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Granary;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GroundType;
import com.blogspot.soyamr.lifesimulation.model.game_elements.HomeSweetHome;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Apple;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Carrot;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Oat;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.FamousAnimal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data.OnScreenInfo;
import com.blogspot.soyamr.lifesimulation.model.game_elements.special_events.Explosion;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Model {
    public final Cell[][] cells;
    public final List<Animal> animals;
    public final List<Plant> plants;
    public final List<Granary> granaries;
    public final Generator generator;
    public final GameBitmaps gameBitmaps;
    final int addingNewPlantThreshold = 20;
    private final String tag = "model";
    private final OnScreenInfo onScreenInfo;
    private final List<Explosion> explosionList;
    private final List<HomeSweetHome> homeList;
    int anpth = 0;
    int disasterThreshold = 30;
    int dth = 0;
    Bitmap[] bitmap;
    int deleteGhostAnimalThreshold = 1000;
    int dgath = 0;
    List<GameObject> objectsToRemove = new ArrayList<>(400);
    List<Animal> animalsToAdd = new ArrayList<>(400);
    int deleteDeadTH = 1500;
    int ddth = 0;
    Rect rect = new Rect();
    private FamousAnimal famousAnimal;

    public Model(Context context) {
        gameBitmaps = new GameBitmaps(context);
        explosionList = new ArrayList<>();
        generator = new Generator(this);
        onScreenInfo = new OnScreenInfo();
        homeList = new ArrayList<>();
        granaries = new ArrayList<>();
        cells = generator.generateSells();
        animals = generator.generateAnimals();
        plants = generator.generatePlants();
        bitmap = gameBitmaps.createPhotos();
    }


    public void addOnePlant() {
        Plant randomPlant = plants.get(Utils.getRandom(0, plants.size()));
        int newX = 0;
        int newY = 0;
        boolean found = false;
        for (int[] dir : Animal.moveDirection) {
            newX = randomPlant.getX() + GameObject.height * dir[0];
            newY = randomPlant.getY() + GameObject.width * dir[1];
            if (
                    Type.CARROT.
                            getMeFromHere(this, Utils.getRowIdx(newY), Utils.getColIdx(newX),
                                    GenderEnum.BOTH).isEmpty()
                            && Type.APPLE.
                            getMeFromHere(this, Utils.getRowIdx(newY), Utils.getColIdx(newX),
                                    GenderEnum.BOTH).isEmpty()
                            && Type.OAT.
                            getMeFromHere(this, Utils.getRowIdx(newY), Utils.getColIdx(newX),
                                    GenderEnum.BOTH).isEmpty()
                            && Type.HOME.
                            getMeFromHere(this, Utils.getRowIdx(newY), Utils.getColIdx(newX),
                                    GenderEnum.BOTH).isEmpty()
                            && Type.GRANARY.
                            getMeFromHere(this, Utils.getRowIdx(newY), Utils.getColIdx(newX),
                                    GenderEnum.BOTH).isEmpty()
            ) {
                found = true;
                break;
            }
        }
        if (found) {
            Plant plant;
            switch (randomPlant.type) {
                case CARROT:
                    plant = new Carrot.Builder()
                            .setCoordinates(newX, newY)
                            .setModel(this)
                            .build();
                    break;
                case OAT:
                    plant = new Oat.Builder()
                            .setCoordinates(newX, newY)
                            .setModel(this)
                            .build();
                    break;
                default:
                    plant = new Apple.Builder()
                            .setCoordinates(newX, newY)
                            .setModel(this)
                            .build();

            }
            plants.add(plant);
        }
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
        onScreenInfo.update(animals.size(),  plants.size(), clipBoundsCanvas, mScaleFactor);
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
//            Log.i("model:::", "granary number is " + granaries.size());
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
            Explosion explosion = new Explosion(this, bitmap,
                    Math.max(0, Utils.getRandom(0, Const.N) * Const.CELL_WIDTH - bitmap[1].getWidth()),
                    Math.max(0, Utils.getRandom(0, Const.M) * Const.CELL_HEIGHT - bitmap[1].getHeight()));
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
                    if (deadObject instanceof Animal) {
                        animals.remove(deadObject);
                        deadObject.isAlive = false;
                    } else if (deadObject instanceof Plant) {
                        plants.remove(deadObject);
                        deadObject.isAlive = false;
                    }
                });
                cells[i][j].clear();
            }
        }
    }

    public void draw(Canvas canvas) {

        animalsToAdd.clear();

        for (int i = 0; i < Const.M; ++i) {
            for (int j = 0; j < Const.N; ++j) {
                cells[i][j].draw(canvas);
            }
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

        HomeSweetHome[] homeSweetHomes = homeList.toArray(new HomeSweetHome[0]);
        for (HomeSweetHome h : homeSweetHomes) {
            h.draw(canvas);
        }

        Granary[] granaries1 = granaries.toArray(new Granary[0]);
        for (Granary g : granaries1) {
            g.draw(canvas);
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
        if (j >= Const.N)
            j = Const.N - 1;
        if (i >= Const.M)
            i = Const.M - 1;
        return cells[i][j].getObjectResidingHere();
    }

    public void iAmLeavingThisCell(int x, int y, GameObject gameObject) {
        cells[Utils.getRowIdx(y)][Utils.getColIdx(x)].removeMeFromHere(gameObject);
    }

    public void addHome(HomeSweetHome homeSweetHome) {
        homeList.add(homeSweetHome);
    }

    public void hideMe(GameObject nearestFood) {
        nearestFood.isAlive = false;//hiding it from map.
        objectsToRemove.add(nearestFood);
        iAmLeavingThisCell(nearestFood.getX(), nearestFood.getY(), nearestFood);
    }

    public Granary searchForNearGranary(Rect rect) {
        Granary[] granaries1 = granaries.toArray(new Granary[0]);
        for (Granary g : granaries1) {
            if (g.intersects(rect))
                return g;
        }
        return null;
    }

    public boolean checkIfNoHomesHere(int newX, int newY, int granaryWidth, int granaryHeight) {
        rect.set(newX, newY, newX + granaryWidth, newY + granaryHeight);
        HomeSweetHome[] homeSweetHomes = homeList.toArray(new HomeSweetHome[0]);
        for (HomeSweetHome h : homeSweetHomes) {
            if (Rect.intersects(h.getRect(), rect))
                return true;
        }

        return false;
    }

    public void addGranary(Granary granary) {
        granaries.add(granary);
    }

    public boolean noGranaryHere(int newX, int newY, int width, int height) {
        rect.set(newX, newY, newX + width, newY + height);
        Granary[] granaries1 = granaries.toArray(new Granary[0]);
        for (Granary g : granaries1) {
            if (g.intersects(rect))
                return true;
        }

        return false;
    }

    public GroundType getNextCellType(int x, int y) {
        return cells[Utils.getRowIdx(y)][Utils.getColIdx(x)].getType();

    }
}
