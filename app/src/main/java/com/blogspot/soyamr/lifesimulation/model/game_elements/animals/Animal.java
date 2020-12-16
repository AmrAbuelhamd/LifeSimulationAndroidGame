package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;


import android.graphics.Canvas;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.AnimalDataManger;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.Female;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.Gender;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.Male;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public abstract class Animal extends GameObject {

    final int increasingHungerThreshold = 100;
    private final String tag = "Animal";
    public List<Type> myFoodTypeList;
    public int mtodth = 50;
    public int hunger = 100;
    public List<GameObject> myFoodMenu;
    public Model model;
    public Gender genderOperator;
    public int ihth = 0;
    public int movingToOneDirectionThreshold = 30;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme
    int[] direction = new int[2];
    int deleteFarThreshold = 50;
    int dfth = 0;
    AnimalDataManger animalDataManger;

    //todo [important]  pattern builder
    public Animal(int x, int y, Model model, Type myType, GenderEnum genderEnum,
                  List<Type> myFoodTypeList) {
        super(myType, genderEnum);
//        this.genderOperator = genderOperator;
        if (x == -1 && y == -1) {
            setInitialData(Utils.getRandom(0, Const.N) * width,
                    Utils.getRandom(0, Const.M) * height, model, myFoodTypeList);
        } else {
            setInitialData(x, y, model, myFoodTypeList);
        }
    }

    void setInitialData(int x, int y, Model model, List<Type> myFoodType) {
        this.x = x;
        this.y = y;
        this.myFoodTypeList = myFoodType;
        myFoodMenu = new LinkedList<>();
        this.model = model;
        model.putMeHerePlease(x, y, this);
        if (genderEnum == GenderEnum.FEMALE)
            genderOperator = new Female(this);
        else
            genderOperator = new Male(this);
        genderOperator.setIdoNotWant();
        genderOperator.setRect();

        changeColor();
    }

    //search for food or move randomly
    //todo [impo] now it should do three things
    // check love();check food(); moveBB();
    // then in each one i take decesion on direction [to love, random, one direction, to food]
    // according to that enum i will move.

    public void update() {
        model.iAmLeavingThisCell(x, y, this);
        if (!myTurn && !genderOperator.inRelation) {
            moveRandomly();
        } else {
            if (genderOperator.inRelation) {
                genderOperator.takeRequiredActions();
            } else if (!genderOperator.iDoNotWant && hunger > SEARCH_PARTNER_THRESHOLD) {
                boolean found = genderOperator.searchForPartner();
                if (!found)
                    moveRandomly();
            } else if (hunger < SEARCH_FOOD_THRESHOLD) {
                boolean found = foundFood();
                if (!found)
                    moveRandomly();
            } else {
                moveRandomly();
            }
        }//use flag
        boolean isDead = updateHunger();
        if (isDead)
            return;
        genderOperator.updateIDoNotWant();
        reachedScreenEdge();
        checkIfAnimalOnSameCellWithTarget();
        model.putMeHerePlease(x, y, this);
        genderOperator.setRect();
    }

    private boolean updateHunger() {
        if (ihth < increasingHungerThreshold) {
            ++ihth;
        } else {
            boolean isDead = increaseHunger();
            ihth = 0;
            return isDead;
        }
        return false;
    }

    private void cleanMyPreysList() {
        if (hunger > SEARCH_FOOD_THRESHOLD) {
            deleteVeryFarPreys();
        }
    }

    //cause game to be slow
    private void deleteVeryFarPreys() {
        ListIterator<GameObject> iter = myFoodMenu.listIterator();
        while (iter.hasNext()) {
            GameObject current = iter.next();
            if (!current.isAlive) {
                iter.remove();
                continue;
            }
            current.distance = Utils.getManhattanDistance(x, y,
                    current.getX(), current.getY());
            if (current.distance > SEARCH_FOOD_THRESHOLD * width) {
                iter.remove();
            }
        }
        myFoodMenu.sort((lhs, rhs) -> {
            // -1 - less than, 1 - greater than, 0 - equal, for ascending
            return Integer.compare(lhs.distance, rhs.distance);
        });
    }

    public void moveRandomly() {
        // Calculate the new position of the game character.
        int randomIndex = Utils.getRandom(0, 8);
        this.x = x + width * moveDirection[randomIndex][0];
        this.y = y + height * moveDirection[randomIndex][1];
    }
    //todo[impo] move this to cell, she checks everytime animal added and resisidences more than one
    // ask each animal type do you eat the others, if yes then remove them from cell and from animal
    // foodlist and remove them from cell
    private void checkIfAnimalOnSameCellWithTarget() {
        GameObject prey = null;
        for (Type preyType : myFoodTypeList) {
            prey = preyType.getMeFromHere(model, Utils.getRowIdx(y), Utils.getColIdx(x), GenderEnum.BOTH);
            if (prey != null)
                break;
        }
        if (hunger < SEARCH_FOOD_THRESHOLD) {
            if (prey != null) {
                if (myFoodMenu.remove(prey)) {
                    model.removeObjectFromMap(prey);
                    reduceHunger();
                }
            }
        } else if (prey instanceof Plant) {
            myFoodMenu.remove(prey);
            model.removeObjectFromMap(prey);
            reduceHunger();
        }
    }

    public boolean worthSearching() {
        if (mtodth < movingToOneDirectionThreshold) {
            ++mtodth;
            moveThere();
            return false;
        }
        return true;
    }

    boolean foundFood() {
        if (!worthSearching()) {
            return true;
        }
        if (myFoodMenu.isEmpty())
            myFoodMenu = Utils.searchAroundAnimal(ANIMAL_FOOD_VISION_RANG, x, y, model, myFoodTypeList, GenderEnum.BOTH);
        if (myFoodMenu.isEmpty()) {
            moveToOneDirectionSetUp();
            return true;
        }
        //make sure that food that i kept in my list still available before going towards it
        //if not delete it
        GameObject target = getNextTarget();

        if (target == null)
            return false;

        //move the ANIMAL towards the plant
        moveToward(target.getX(), target.getY());

        return true;
    }

    private GameObject getNextTarget() {
        if (dfth < 50) {
            ++dfth;
        } else {
            dfth = 0;
            deleteVeryFarPreys();
        }
        if (!myFoodMenu.isEmpty())
            return myFoodMenu.get(0);
        return null;
    }

    public void moveToOneDirectionSetUp() {
        mtodth = 0;
        int rand = Utils.getRandom(0, moveDirection.length);
        direction = moveDirection[rand];
        moveThere();
    }

    private void moveThere() {
        x += width * direction[0];
        y += height * direction[1];
        boolean flag = false;
        if (this.x < 0) {
            this.x = 0;
            flag = true;
        } else if (this.x > Const.FIELD_WIDTH - width) {
            this.x = Const.FIELD_WIDTH - width;
            flag = true;
        }
        if (this.y < 0) {
            this.y = 0;
            flag = true;
        } else if (this.y > Const.FIELD_HEIGHT - height) {
            this.y = Const.FIELD_HEIGHT - height;
            flag = true;
        }
        if (flag)
            direction = moveDirection[Utils.getRandom(0, moveDirection.length)];
    }

    public void moveToward(int targetX, int targetY) {
        // four cases
        if (targetX < x)
            x -= width;
        else if (targetX > x)
            x += width;

        if (targetY < y)
            y -= height;
        else if (targetY > y)
            y += height;

    }

    public boolean increaseHunger() {
        if (hunger == 0) {
            model.deleteMePlease(this);
            return true;
        } else
            hunger -= 10;
        changeColor();
        return false;
    }

    public boolean wannaBeInRelationship(Type type) {
        return genderOperator.wannaBeInRelationShip(type);
    }

    protected void changeColor() {
        paint.setColor(getMyColor());
    }

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }

    @Override
    public void draw(Canvas canvas) {
        genderOperator.draw(canvas);
    }

    public void doCermony() {
        genderOperator.doCeremony();
    }

    public abstract void addChild();

    public void writeMyWill() {
        isAlive = false;
        // genderOperator.takeRequiredActions();
    }

    @Override
    public void makeMeFamous() {
        model.setFamousAnimal(this);
        animalDataManger = new AnimalDataManger(this);
    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {
        animalDataManger.update(mScaleFactor);
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        animalDataManger.draw(canvas);
    }

    //add gender
}