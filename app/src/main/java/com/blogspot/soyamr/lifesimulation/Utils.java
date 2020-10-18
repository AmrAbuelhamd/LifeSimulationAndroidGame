package com.blogspot.soyamr.lifesimulation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import static com.blogspot.soyamr.lifesimulation.Direction.*;

enum Direction {
    UP, LEFT, RIGHT, DOWN
}

public abstract class Utils {
    static final Random rand = new Random();

    static Plant searchAroundAnimal(int searchRange, Animal currentAnimal, Map<String, Plant> plants) {

        // The input matrix  dimension
        int matrixLength = searchRange;

        //The number of elements of the input matrix
        int numberOfElements = matrixLength * matrixLength;

        // The matrix mask help to decide which is the next direction or the next element to pick from the input matrix
        // All the values of the mask are initialized to zero.
        Map<String, Integer> mask = new LinkedHashMap<>();

        //The first element of the output(the spiral) is always the middle element of the input matrix
        int rowIndex = currentAnimal.y;
        int colIndex = currentAnimal.x;


        // Each time an element from the input matrix is added to the output spiral, the corresponding element in the mask is set to 1
        mask.put(rowIndex + " " + colIndex, 1);

        // The first direction is always to the left
        Direction nextDirection = LEFT;

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
                    if (plants.containsKey(key)) {
                        return plants.get(key);
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
                    if (plants.containsKey(key)) {
                        return plants.get(key);
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
                    if (plants.containsKey(key)) {
                        return plants.get(key);
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
                    if (plants.containsKey(key)) {
                        return plants.get(key);
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
        return null;
    }

    static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
}
