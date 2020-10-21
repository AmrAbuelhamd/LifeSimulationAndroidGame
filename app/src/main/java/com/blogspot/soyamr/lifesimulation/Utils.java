package com.blogspot.soyamr.lifesimulation;

import com.blogspot.soyamr.lifesimulation.model.GameObject;
import com.blogspot.soyamr.lifesimulation.model.Model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static com.blogspot.soyamr.lifesimulation.Utils.Const.Direction.*;

public abstract class Utils {
    public static final Random rand = new Random();

    public static GameObject searchAroundAnimal(int searchRange, int currentX, int currentY, Model model, Const.SearchFor searchFor) {

        GameObject object = null;
        // The input matrix  dimension
        int matrixLength = searchRange;

        //The number of elements of the input matrix
        int numberOfElements = matrixLength * matrixLength;

        // The matrix mask help to decide which is the next direction or the next element to pick from the input matrix
        // All the values of the mask are initialized to zero.
        Map<String, Integer> mask = new LinkedHashMap<>();

        //The first element of the output(the spiral) is always the middle element of the input matrix
        int rowIndex = currentY;
        int colIndex = currentX;


        // Each time an element from the input matrix is added to the output spiral, the corresponding element in the mask is set to 1
        mask.put(rowIndex + " " + colIndex, 1);

        // The first direction is always to the left
        Const.Direction nextDirection = LEFT;

        // This is a counter to loop through all the elements of the input matrix only one time
        int i = 0;
        String key;
        while (i++ < numberOfElements - 1) {

            // Check which direction to go (left, down, right or up)
            switch (nextDirection) {

                case LEFT:
                    // From the input matrix, take the number at the left of the current position
                    // (which is the middle of the input matrix) and add it to the spiral
                    colIndex -= Const.CELL_WIDTH;
                    key = colIndex + " " + rowIndex;
                    object = search(searchFor, model, key);
                    if (object != null) {
                        i = numberOfElements;
                        break;
                    }
                    //Update the mask
                    mask.put(rowIndex + " " + colIndex, 1);

                    // Decide which direction(or element in the input matrix) to take next.
                    // After moving to the left, you only have two choices : keeping the same direction or moving down
                    // To know which direction to take, check the mask
                    if (mask.containsKey((int) (rowIndex + Const.CELL_HEIGHT) + " " + colIndex)) {
                        nextDirection = LEFT;
                    } else {
                        nextDirection = DOWN;
                    }
                    break;

                case DOWN:
                    rowIndex += Const.CELL_HEIGHT;
                    key = colIndex + " " + rowIndex;
                    object = search(searchFor, model, key);
                    if (object != null) {
                        i = numberOfElements;
                        break;
                    }
                    //Update the mask
                    mask.put(rowIndex + " " + colIndex, 1);
                    if (mask.containsKey(rowIndex + " " + (int) (colIndex + Const.CELL_WIDTH))) {
                        nextDirection = DOWN;
                    } else {
                        nextDirection = RIGHT;
                    }
                    break;

                case RIGHT:
                    colIndex += Const.CELL_WIDTH;
                    key = colIndex + " " + rowIndex;
                    object = search(searchFor, model, key);
                    if (object != null) {
                        i = numberOfElements;
                        break;
                    }
                    //Update the mask
                    mask.put(rowIndex + " " + colIndex, 1);
                    if (mask.containsKey((int) (rowIndex - Const.CELL_HEIGHT) + " " + colIndex)) {
                        nextDirection = RIGHT;
                    } else {
                        nextDirection = UP;
                    }
                    break;

                case UP:
                    rowIndex -= Const.CELL_HEIGHT;
                    key = colIndex + " " + rowIndex;
                    object = search(searchFor, model, key);
                    if (object != null) {
                        i = numberOfElements;
                        break;
                    }
                    //Update the mask
                    mask.put(rowIndex + " " + colIndex, 1);
                    if (mask.containsKey(rowIndex + " " + (int) (colIndex - Const.CELL_WIDTH))) {
                        nextDirection = UP;
                    } else {
                        nextDirection = LEFT;
                    }
                    break;
            }
        }
        return object;
    }


    private static GameObject search(Const.SearchFor searchFor, Model model, String key) {
        if (searchFor == Const.SearchFor.ANIMAL)
            return searchAnimal(model, key);
        else if (searchFor == Const.SearchFor.PLANT)
            return searchPlant(model, key);
        else
            return searchSingleFemale(model, key);
    }

    private static GameObject searchSingleFemale(Model model, String key) {
        return model.getSingleFemaleAnimal(key);
    }

    private static GameObject searchPlant(Model model, String key) {
        return model.getPlant(key);
    }

    private static GameObject searchAnimal(Model model, String key) {
        return model.getAnimal(key);
    }

    public static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    public static class Const {
        public static final int USER_CLICK_SEARCH_RANG = 20;
        public static final int N = 300;
        public static final int M = 600;
        public static int CELL_WIDTH = 10;
        public static int CELL_HEIGHT = 10;
        public static int SCREEN_HEIGHT = CELL_HEIGHT * M;
        public static int SCREEN_WIDTH = CELL_WIDTH * N;
        public static final int SEARCH_FOOD_THRESHOLD_NORMAL = 31;
        public static final int SEARCH_PARTNER_THRESHOLD_NORMAL = 49;
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
