package com.blogspot.soyamr.lifesimulation;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*

1- Type.Java -> Utils.Java


 */
public abstract class Utils {
    public static final Random rand = new Random();
    static String tag = "search algorithm";

    public static List<GameObject>
    searchAroundAnimal(int searchRange, int currentX, int currentY, Model model,
                       List<Type> searchFor, GenderEnum genderEnum) {
        int iStart = getRowIdx(currentY) - searchRange;
        int jStart = getColIdx(currentX) - searchRange;
        iStart = Math.max(iStart, 0);
        jStart = Math.max(jStart, 0);

        int iEnd = getRowIdx(currentY) + searchRange;
        int jEnd = getColIdx(currentX) + searchRange;
        iEnd = Math.min(iEnd, Const.M);
        jEnd = Math.min(jEnd, Const.N);

        int posI = Utils.getRowIdx(currentY);
        int posJ = Utils.getColIdx(currentX);


        List<GameObject> gameObjects = new ArrayList<>();
        for (int i = iStart; i < iEnd; i++) {
            for (int j = jStart; j < jEnd; j++) {
                //found myself
                if (i == posI && j == posJ) {
                    continue;
                }
                for (Type preyType : searchFor) {
                    GameObject prey = preyType.getMeFromHere(model, i, j, genderEnum);
                    if (prey != null) {
                        prey.distance = getManhattanDistance(currentX, currentY,
                                prey.getX(), prey.getY());
                        gameObjects.add(prey);
                    }
                }
            }
        }
        gameObjects.sort((lhs, rhs) -> {
            // -1 - less than, 1 - greater than, 0 - equal, for ascending
            return Integer.compare(lhs.distance, rhs.distance);
        });
        return gameObjects;
    }


//    public static GameObject search(Species searchFor, Model model, int i, int j) {
//        //todo make use of enumerations to get the animals, i can put methods there .... enum.plant.get()...
//        List<GameObject> objectSOnCell = model.getObjectResidingHere(i, j);
//        if (searchFor == Species.ANIMAL) {
//            for (GameObject currentObject : objectSOnCell)
//                if (currentObject instanceof Animal)
//                    return currentObject;
//        } else if (searchFor == Species.PLANT) {
//            for (GameObject currentObject : objectSOnCell)
//                if (currentObject instanceof Plant)
//                    return currentObject;
//        } else if (searchFor == Species.FEMALE_ANIMAL) {
//            for (GameObject currentObject : objectSOnCell)
//                if (currentObject instanceof FemaleAnimal)
//                    return currentObject;
//        } else if (searchFor == Species.BOTH) {
//            for (GameObject currentObject : objectSOnCell) {
//                if (currentObject instanceof Animal && ((Animal) currentObject).myFoodTypeList != Species.BOTH
//                        && !((Animal) currentObject).inRelation
//                        || currentObject instanceof Plant)
//                    return currentObject;
//            }
//        } else if (searchFor == Species.HERBIVORE) {
//            for (GameObject currentObject : objectSOnCell) {
//                if (currentObject instanceof Animal && ((Animal) currentObject).myFoodTypeList == Species.PLANT &&
//                        !((Animal) currentObject).inRelation)
//                    return currentObject;
//            }
//        }
//        return null;
//    }

    public static int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    public static int getRowIdx(int index) {
        return index / Const.CELL_HEIGHT;
    }

    public static int getColIdx(int index) {
        return index / Const.CELL_WIDTH;
    }

}
