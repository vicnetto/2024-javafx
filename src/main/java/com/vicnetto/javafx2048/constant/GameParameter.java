package com.vicnetto.javafx2048.constant;

public class GameParameter {

    private GameParameter() {
    }

    public static final Integer[] POSSIBLE_BOARD_SIZES = {4, 5, 6, 7};

    public static final Integer[] POSSIBLE_GOALS = {256, 512, 1024, 2048, 4096, 8192};

    public static final Integer DEFAULT_BOARD_SIZE = POSSIBLE_BOARD_SIZES[0];

    public static final Integer DEFAULT_GOAL = POSSIBLE_GOALS[3];
}
