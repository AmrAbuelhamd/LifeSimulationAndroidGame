package com.blogspot.soyamr.lifesimulation;

import com.blogspot.soyamr.lifesimulation.model.Animal;
import com.blogspot.soyamr.lifesimulation.model.FemaleAnimal;
import com.blogspot.soyamr.lifesimulation.model.GameObject;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Utils {
    public static final Random rand = new Random();
    static String tag = "search algorithm";

    public static List<GameObject> searchAroundAnimal(int searchRange, int currentX, int currentY, Model model, Const.SearchFor searchFor) {
        int iStart = getRowIndex(currentY) - searchRange;
        int jStart = getColumnIndex(currentX) - searchRange;
        iStart = Math.max(iStart, 0);
        jStart = Math.max(jStart, 0);

        int iEnd = getRowIndex(currentY) + searchRange;
        int jEnd = getColumnIndex(currentX) + searchRange;
        iEnd = Math.min(iEnd, Const.M);
        jEnd = Math.min(jEnd, Const.N);

        int posI = Utils.getRowIndex(currentY);
        int posJ = Utils.getColumnIndex(currentX);



        List<GameObject> gameObjects = new ArrayList<>();
        for (int i = iStart; i < iEnd; i++) {
            for (int j = jStart; j < jEnd; j++) {
                //found myself
                if (i == posI && j == posJ) {
                    continue;
                }
                GameObject currentObject = search(searchFor, model, i, j);
                if (currentObject == null)
                    continue;

                currentObject.distance = getManhattanDistance(currentX, currentY,
                        currentObject.getX(), currentObject.getY());
                gameObjects.add(currentObject);
            }
        }
        gameObjects.sort((lhs, rhs) -> {
            // -1 - less than, 1 - greater than, 0 - equal, for ascending
            return Integer.compare(lhs.distance, rhs.distance);
        });
        return gameObjects;
    }


    public static GameObject search(Const.SearchFor searchFor, Model model, int i, int j) {
        List<GameObject> objectSOnCell = model.getObjectResidingHere(i, j);
        if (searchFor == Const.SearchFor.ANIMAL) {
            for (GameObject currentObject : objectSOnCell)
                if (currentObject instanceof Animal)
                    return currentObject;
            return null;
        } else if (searchFor == Const.SearchFor.PLANT) {
            for (GameObject currentObject : objectSOnCell)
                if (currentObject instanceof Plant)
                    return currentObject;
            return null;
        } else {
            for (GameObject currentObject : objectSOnCell)
                if (currentObject instanceof FemaleAnimal)
                    return currentObject;
            return null;
        }
    }

    private static int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }


    public static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    public static int getRowIndex(int index) {
        return index / Const.CELL_HEIGHT;
    }

    public static int getColumnIndex(int index) {
        return index / Const.CELL_WIDTH;
    }

    public static class Const {
        public static final int USER_CLICK_SEARCH_RANG = 20;
        public static final int N = 300;
        public static final int M = 600;
        public static int CELL_WIDTH = 10;
        public static int CELL_HEIGHT = 10;
        public static int FIELD_HEIGHT = CELL_HEIGHT * M;
        public static int FIELD_WIDTH = CELL_WIDTH * N;
        public static int SCREEN_WIDTH;
        public static int SCREEN_HEIGHT;
        public static final int SEARCH_FOOD_THRESHOLD_NORMAL = 31;//31
        public static final int SEARCH_PARTNER_THRESHOLD_NORMAL = 49;//49
        public static final int SEARCH_FOOD_THRESHOLD_HIGH = 80;
        public static final int SEARCH_PARTNER_THRESHOLD_PROHIBIT = 101;


        public enum Direction {
            UP, LEFT, RIGHT, DOWN
        }

        public enum SearchFor {
            ANIMAL, PLANT, FEMALE_ANIMAL
        }
    }
}
