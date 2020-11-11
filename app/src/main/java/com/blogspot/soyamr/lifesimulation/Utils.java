package com.blogspot.soyamr.lifesimulation;

import android.util.Log;

import com.blogspot.soyamr.lifesimulation.model.Animal;
import com.blogspot.soyamr.lifesimulation.model.FemaleAnimal;
import com.blogspot.soyamr.lifesimulation.model.GameObject;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.Plant;

import java.util.List;
import java.util.Random;

public abstract class Utils {
    public static final Random rand = new Random();
    static String tag = "search algorithm";

    public static GameObject searchAroundAnimal(int searchRange, int currentX, int currentY, Model model, Const.SearchFor searchFor) {
        //Log.i(tag,""+searchFor);
//        if (searchFor != Const.SearchFor.ANIMAL)
//            return null;
        int iStart = getRowIndex(currentY) - searchRange;
        int jStart = getColumnIndex(currentX) - searchRange;
        iStart = Math.max(iStart, 0);
        jStart = Math.max(jStart, 0);

        int iEnd = getRowIndex(currentY) + searchRange;
        int jEnd = getColumnIndex(currentX) + searchRange;
        iEnd = Math.min(iEnd, Const.M);
        jEnd = Math.min(jEnd, Const.N);

        Log.i(tag, "i= " + iStart + " j= " + jStart);
        Log.i(tag, "iEnd= " + iEnd + " jEnd= " + jEnd);

        int posI = Utils.getRowIndex(currentY);
        int posJ = Utils.getColumnIndex(currentX);

        Log.i(tag, "posI= " + posI + " posJ= " + posJ);


        int shortestDistance = Integer.MAX_VALUE;//impossible length;
        GameObject gameObject = null;
        for (int i = iStart; i < iEnd; i++) {
            //Log.i(tag ,"*-***********************");
            //Log.i(tag,"i= "+i);
            for (int j = jStart; j < jEnd; j++) {
                //Log.i(tag,"j= "+j);
                //found myself
                if (i == posI && j == posJ) {
                    continue;
                }
                GameObject currentObject = search(searchFor, model, i, j);
                if (currentObject == null)
                    continue;

                int newPathDistance = getManhattanDistance(currentX, currentY,
                        currentObject.getX(), currentObject.getY());

                if (shortestDistance > newPathDistance) {
                    gameObject = currentObject;
                    shortestDistance = newPathDistance;
                }
            }
        }
        if (searchFor == Const.SearchFor.FEMALE_ANIMAL) {
        }
        //Log.i(tag,"finished searching for woman " + searchFor + " " + gameObject);
        return gameObject;
    }


    private static GameObject search(Const.SearchFor searchFor, Model model, int i, int j) {
//        List<GameObject> currentObject = model.getObjectResidingHere(i, j);
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
//        return distance >= (SEARCH_FOOD_OPTIMIZATION_THRESHOLD * Utils.Const.CELL_WIDTH);
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
        public static int CELL_WIDTH = 100;
        public static int CELL_HEIGHT = 100;
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
