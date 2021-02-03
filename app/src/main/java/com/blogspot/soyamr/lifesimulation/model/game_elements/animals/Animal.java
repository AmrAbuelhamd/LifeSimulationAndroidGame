package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;


import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GroundType;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.AnimalDataManger;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.GoingToFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.InMarriageProcess;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.NotSet;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.OneDirection;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.SearchFood;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.SearchPartner;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.state.StateAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public abstract class Animal extends GameObject {

    private final String tag = "Animal";
    public List<Type> myFoodTypeList;
    public int moveToOneDirectionCTR = 0;
    public int hunger = 100;
    public List<GameObject> myFoodMenu = new ArrayList<>();
    public Model model;
    public int increaseHungerCTR = 0;
    public int movingToOneDirectionThreshold = 30;
    public int[] direction = new int[2];
    public StateAnimal searchFood = new SearchFood();
    public StateAnimal searchPartner = new SearchPartner();
    public StateAnimal inMarriageProcess = new InMarriageProcess();
    public StateAnimal oneDirection = new OneDirection();
    public StateAnimal goingToFood = new GoingToFood();
    public StateAnimal notSet = new NotSet();
    public GameObject myFood;
    public StateAnimal currentState = notSet;
    public int resetIdontWantCTR = 0;
    public boolean iDoNotWant;
    public boolean inRelation = false;
    public Animal myLove;
    public List<GameObject> myCrushes;
    int increasingHungerThreshold = 50;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme
    int deleteFarThreshold = 50;
    int deleteFarPreysCTR = 0;
    AnimalDataManger animalDataManger;
    GroundType nextCellType;
    int resetIDoNotWantThreshold = 300;

    //todo [important]  pattern builder
    public Animal() {
        myCrushes = new ArrayList<>();
        direction = moveDirection[Utils.getRandom(0, moveDirection.length)];
    }

    void setInitialData(int x, int y, Model model, List<Type> myFoodType) {
        this.x = x;
        this.y = y;
        this.myFoodTypeList = myFoodType;
        myFoodMenu = new ArrayList<>();
        this.model = model;
        model.putMeHerePlease(x, y, this);

        setIdoNotWant();
        setRect();

        changeColor();

        setThresholds();
    }

    void setThresholds() {
        increasingHungerThreshold = Utils.getRandom(300, 500);//300 500
        resetIDoNotWantThreshold = Utils.getRandom(300, 500);//300 500
        movingToOneDirectionThreshold = Utils.getRandom(40, 61);
        deleteFarThreshold = Utils.getRandom(40, 61);
    }

    public void update() {
        if (isAlive) {
            boolean isDead = updateHunger();
            if (isDead) {
                model.deleteMePlease(this);
                return;
            }
            model.iAmLeavingThisCell(x, y, this);

            currentState.update(this);

            updateIDoNotWant();
            reachedScreenEdge();
            gonnaStepOnDangerousGround();
            model.putMeHerePlease(x, y, this);
            setRect();
        }
    }

    public void updateIDoNotWant() {
        if (iDoNotWant) {
            if (resetIdontWantCTR < resetIDoNotWantThreshold) {
                ++resetIdontWantCTR;
            } else {
                iDoNotWant = false;
                resetIdontWantCTR = 0;
            }
        }
    }

    protected void setRect() {
        rect.set(x, y, x + GameObject.width,
                y + GameObject.height);
    }

    protected void gonnaStepOnDangerousGround() {
        //when game's character is entering volcano or water
        nextCellType = model.getNextCellType(x, y);
        while (nextCellType == GroundType.VOLCANO || nextCellType == GroundType.WATER) {
            moveRandomly();
            reachedScreenEdge();
            nextCellType = model.getNextCellType(x, y);
        }
        if (nextCellType == GroundType.SAND || nextCellType == GroundType.SNOW) {
            ++increaseHungerCTR;
        }
    }

    public Animal getNextTarget2() {
        ListIterator<GameObject> iter = myCrushes.listIterator();
        while (iter.hasNext()) {
            Animal current = (Animal) iter.next();
            if (current != null && current.isAlive && current.wannaBeInRelationship(type)) {
                return current;
            } else {
                iter.remove();
            }
        }
        return null;
    }

    public boolean updateHunger() {
        if (increaseHungerCTR < increasingHungerThreshold) {
            ++increaseHungerCTR;
        } else {
            boolean isDead = increaseHunger();
            increaseHungerCTR = 0;
            return isDead;
        }
        return false;
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


    public GameObject getNextTarget() {
        if (deleteFarPreysCTR < deleteFarThreshold) {
            ++deleteFarPreysCTR;
        } else {
            deleteFarPreysCTR = 0;
            deleteVeryFarPreys();
        }
        if (!myFoodMenu.isEmpty())
            return myFoodMenu.get(0);
        return null;
    }

    public void moveToOneDirection() {
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

    void moveToFood(int targetX, int targetY) {
        moveToward(targetX, targetY);
        if (targetX == x && targetY == y) {
            myFoodMenu.remove(myFood);
            model.deleteMePlease(myFood);
            reduceHunger();
            myFood = null;
        }
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
            return true;
        }
        hunger -= 10;
        changeColor();
        return false;
    }

    public boolean wannaBeInRelationship(Type groomType) {
        if (genderEnum == GenderEnum.MALE) {
            throw new RuntimeException("i am man bro");
        }
        if (!iDoNotWant && !inRelation && hunger > SEARCH_FOOD_THRESHOLD) {
            if (type == groomType) {
                inRelation = true;
                currentState = inMarriageProcess;
                return true;
            }
        }
        return false;
    }

    protected void changeColor() {
        paint.setColor(getMyColor());
    }

    public void reduceHunger() {
        if (hunger != 100)
            hunger += 10;
        changeColor();
    }


    public void doCeremony() {
        if (genderEnum == GenderEnum.MALE) {
            throw new RuntimeException("i am a man, can't add child");
        }
        marriage();
        brokeUp();
        currentState = notSet;
    }

    public void brokeUp() {
        inRelation = false;
        setIdoNotWant();
    }

    public void setIdoNotWant() {
        iDoNotWant = true;
        resetIdontWantCTR = 0;
    }

    public void marriage() {
        addChild();
    }

    public abstract void addChild();

    @Override
    public void makeMeFamous() {
        model.setFamousAnimal(this);
        animalDataManger = new AnimalDataManger(this);
    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {
        if (animalDataManger != null)
            animalDataManger.update(mScaleFactor);
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        if (animalDataManger != null)
            animalDataManger.draw(canvas);
    }

    protected static abstract class Builder
            <T extends Animal, B extends Builder<T, B>> extends GameObject.Builder<T, B> {
        public B setCoordinates(int x, int y) {
            object.x = x;
            object.y = y;
            return thisObject;
        }

        public B setModel(Model model) {
            object.model = model;
            return thisObject;
        }

        public B setFoodTypeList(List<Type> myFoodTypeList) {
            object.myFoodTypeList = myFoodTypeList;
            return thisObject;
        }

        @Override
        public T build() {
            super.build();
            object.model.putMeHerePlease(object.getX(), object.getY(), object);
//            object.setIdoNotWant();
            object.setRect();
//            object.setThresholds();
            return object;
        }
    }
}
