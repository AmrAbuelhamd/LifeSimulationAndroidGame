package com.blogspot.soyamr.lifesimulation;

public class Const {
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

}
