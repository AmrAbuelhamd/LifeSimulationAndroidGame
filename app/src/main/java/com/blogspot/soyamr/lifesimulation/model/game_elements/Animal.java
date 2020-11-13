package com.blogspot.soyamr.lifesimulation.model.game_elements;


import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.types.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public abstract class Animal extends GameObject {
    List<GameObject> myFoodMenu;
    int hunger = 50;
    final Model model;
    public boolean inRelation = false;
    boolean iDoNotWant;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme
    private String tag = "Animal";

    public Species myFoodType;

    Animal(Model model, Species myFoodType) {
        this.myFoodType = myFoodType;
        x = Utils.getRandom(0, Const.N) * width;
        y = Utils.getRandom(0, Const.M) * height;
        myFoodMenu = new ArrayList<>();

        rect.set(x, y, x + width, y + height);
        this.model = model;
        setIdoNotWant();
    }

    Animal(int x, int y, Model model, Species myFoodType) {
        this.myFoodType = myFoodType;
        this.x = x;
        this.y = y;
        myFoodMenu = new ArrayList<>();
        rect.set(x, y, x + width, y + height);
        this.model = model;
        setIdoNotWant();
    }

    final int reSetIDoNotWantVariable = 90;
    int rsidwv = 0;

    protected void setIdoNotWant() {
        iDoNotWant = true;
        rsidwv = 0;
    }

    final int increasingHungerThreshold = 100;
    int ihth = 0;

    //search for food or move randomly
    public void update() {
        //cleanMyPreysList();
        boolean isDead = updateHunger();
        if (isDead)
            return;
        updateIDoNotWant();
        reachedScreenEdge();
        checkIfAnimalOnSameCellWithTarget();
        model.putMeHerePlease(x, y, this);
        rect.set(x, y, x + width, y + height);

    }

    private boolean updateHunger() {
        if (ihth < increasingHungerThreshold) {//todo add this to another method, but be sure that
            ++ihth;                         //if dead no need to add ghost animal on cell and disappear
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

    private void updateIDoNotWant() {
        if (iDoNotWant == true) {
            if (rsidwv < reSetIDoNotWantVariable) {
                ++rsidwv;
            } else {
                iDoNotWant = false;
                rsidwv = 0;
            }
        }
    }

    private void deleteVeryFarPreys() {
        ListIterator<GameObject> iter = myFoodMenu.listIterator();
        while (iter.hasNext()) {
            GameObject current = iter.next();
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

    protected void moveRandomly() {
        // Calculate the new position of the game character.
        int randomIndex = Utils.getRandom(0, 8);
        this.x = x + width * moveDirection[randomIndex][0];
        this.y = y + height * moveDirection[randomIndex][1];
    }

    private void checkIfAnimalOnSameCellWithTarget() {
        if (hunger < SEARCH_FOOD_THRESHOLD) {
            GameObject prey = Utils.search(myFoodType, model,
                    Utils.getRowIdx(y),
                    Utils.getColIdx(x));
            if (prey != null) {
                if (myFoodMenu.remove(prey)) {
                    model.removeObjectFromMap(prey);
                    reduceHunger();
                }
            }
        }
    }


    protected boolean worthSearching() {
        if (mtodth < movingToOneDirectionThreshold) {
            ++mtodth;
            moveThere();
            return false;
        }
        return true;
    }

    public int mtodth = 50;
    int movingToOneDirectionThreshold = 30;
    int[] direction = new int[2];

    boolean foundFood() {
        if (!worthSearching()) {
            return true;
        }
        if (myFoodMenu.isEmpty())
            myFoodMenu = Utils.searchAroundAnimal(ANIMAL_FOOD_VISION_RANG, x, y, model, myFoodType);
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
        moveToward(target.x, target.y);

        return true;
    }

    private GameObject getNextTarget() {
        deleteVeryFarPreys();
        ListIterator<GameObject> iter = myFoodMenu.listIterator();
        while (iter.hasNext()) {
            GameObject current = iter.next();
            if (Utils.search(myFoodType, model,
                    Utils.getRowIdx(current.y),
                    Utils.getColIdx(current.x)
            ) != null) {
                return current;
            } else {
                iter.remove();
            }
        }
        return null;
    }

    protected abstract boolean isSuitableFood(GameObject current);

    void moveToOneDirectionSetUp() {
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

    void moveToward(int targetX, int targetY) {
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

    protected abstract void changeColor();

    protected abstract Species getMyType();

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }
}
