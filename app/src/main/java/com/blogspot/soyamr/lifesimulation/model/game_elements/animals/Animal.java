package com.blogspot.soyamr.lifesimulation.model.game_elements.animals;


import android.graphics.Canvas;

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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public abstract class Animal extends GameObject {

    private final String tag = "Animal";
    public List<Type> myFoodTypeList;
    public int mtodth = 0;
    public int hunger = 100;
    public List<GameObject> myFoodMenu;
    public Model model;
    public Gender genderOperator;
    public int ihth = 0;
    public int movingToOneDirectionThreshold = 30;
    public NextMove nextMove;
    int increasingHungerThreshold = 50;
    boolean myTurn = true;//todo enable this variable agian when animals > 1000, and make small documentation on it in readme
    public int[] direction = new int[2];
    int deleteFarThreshold = 50;
    int dfth = 0;
    AnimalDataManger animalDataManger;
    private GameObject myFood;

    //todo [important]  pattern builder
    public Animal(int x, int y, Model model, Type myType, GenderEnum genderEnum,
                  List<Type> myFoodTypeList) {
        super(myType, genderEnum);
        direction = moveDirection[Utils.getRandom(0, moveDirection.length)];
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
        myFoodMenu = new ArrayList<>();
        this.model = model;
        model.putMeHerePlease(x, y, this);
        if (genderEnum == GenderEnum.FEMALE)
            genderOperator = new Female(this);
        else
            genderOperator = new Male(this);
        genderOperator.setIdoNotWant();
        genderOperator.setRect();

        changeColor();

        setThresholds();
    }

    private void setThresholds() {
        increasingHungerThreshold = Utils.getRandom(150, 250);//300 500
        movingToOneDirectionThreshold = Utils.getRandom(40, 61);
        deleteFarThreshold = Utils.getRandom(40, 61);
    }

    NextMove searchForLove() {
        if (genderOperator.inRelation) {
            return genderOperator.takeRequiredActions();
        } else if (!genderOperator.iDoNotWant && hunger > SEARCH_PARTNER_THRESHOLD) {
            nextMove = genderOperator.searchForPartner();
        }
        return NextMove.NOT_SET;
    }

    public void update() {
        if (isAlive) {
            boolean isDead = updateHunger();
            if (isDead) {
                model.deleteMePlease(this);
                return;
            }
            model.iAmLeavingThisCell(x, y, this);

            nextMove = searchForLove();
            if (nextMove == NextMove.NOT_SET)
                nextMove = searchForFood();
            nextStep();

            genderOperator.updateIDoNotWant();
            reachedScreenEdge();
            model.putMeHerePlease(x, y, this);
            genderOperator.setRect();
        }
    }

    //todo [impo] pattern NextMove
//todo [impo] pattern command
    private void nextStep() {
        switch (((NextMove) nextMove)) {
            case MOVE_RANDOMLY:
                moveRandomly();
                break;
            case NOTHING://todo at the end
                break;
            case TO_FOOD:
                if (myFood == null)
                    throw new RuntimeException("myfood is null");
                moveToFood(myFood.getX(), myFood.getY());
                break;
            case ONE_DIRECTION:
                moveToOneDirection();
                break;
            case TO_LOVE:
                moveToLove(genderOperator.myLove.getX(), genderOperator.myLove.getY());
                break;
            default:
                throw new RuntimeException("SOMETHING BIG baaaad HAPPENED");
        }
    }

    private void moveToLove(int x, int y) {
        moveToward(x, y);
        if (x == this.x && y == this.y) {
            doCermony();
        }
    }

    public boolean updateHunger() {
        if (ihth < increasingHungerThreshold) {
            ++ihth;
        } else {
            boolean isDead = increaseHunger();
            ihth = 0;
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

    public NextMove worthSearching() {
        if (mtodth < movingToOneDirectionThreshold) {
            ++mtodth;
            return NextMove.ONE_DIRECTION;
        }
        return NextMove.NOT_SET;
    }

    NextMove searchForFood() {
        NextMove result;
        if (hunger > SEARCH_FOOD_THRESHOLD)
            return NextMove.MOVE_RANDOMLY;
        if (myFood != null) {
            if (myFood.isAlive) {
                return NextMove.TO_FOOD;
            } else {
                myFoodMenu.remove(myFood);
                myFood = null;
            }
        }
        if ((result = worthSearching()) != NextMove.NOT_SET) {
            return result;
        }
        if (myFoodMenu.isEmpty())
            myFoodMenu = Utils.searchAroundAnimal(ANIMAL_FOOD_VISION_RANG, x, y, model, myFoodTypeList, GenderEnum.BOTH);
        if (myFoodMenu.isEmpty()) {
            return moveToOneDirectionSetUp();
        }
        //make sure that food that i kept in my list still available before going towards it
        //if not delete it
        GameObject target = getNextTarget();

        if (target == null)
            return NextMove.MOVE_RANDOMLY;

        //move the ANIMAL towards the food
        myFood = target;
        return NextMove.TO_FOOD;
    }

    private GameObject getNextTarget() {
        if (dfth < deleteFarThreshold) {
            ++dfth;
        } else {
            dfth = 0;
            deleteVeryFarPreys();
        }
        if (!myFoodMenu.isEmpty())
            return myFoodMenu.get(0);
        return null;
    }

    public NextMove moveToOneDirectionSetUp() {
        mtodth = 0;
        int rand = Utils.getRandom(0, moveDirection.length);
        direction = moveDirection[rand];
        return NextMove.ONE_DIRECTION;
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
        if (isAlive) {
            genderOperator.draw(canvas);
            nextMove = NextMove.NOT_SET;
        }
    }

    public void doCermony() {
        genderOperator.doCeremony();
    }

    public abstract void addChild();

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

    public enum NextMove{
        MOVE_RANDOMLY, TO_LOVE, TO_FOOD, NOTHING, NOT_SET, ONE_DIRECTION;
    }
}
