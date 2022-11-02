package com.vicnetto.javafx2048.model;

public class MoveDirection {

    public final int start;

    public final int end;

    private final int boardSize;

    private final Direction direction;

    public MoveDirection(int boardSize, Direction direction) {
        this.boardSize = boardSize;
        this.direction = direction;

        this.start = calculateStart();
        this.end = calculateEnd();
    }

    public boolean columnStopCondition(int index) {
        return switch (direction) {
            case LEFT -> index < end;
            case RIGHT -> index > 0;
            case UP -> index <= end;
            case DOWN -> index >= 0;
        };
    }

    public boolean rowStopCondition(int index) {
        return switch (direction) {
            case LEFT -> index <= end;
            case RIGHT -> index >= 0;
            case UP -> index < end;
            case DOWN -> index > 0;
        };
    }

    public int next(int index) {
        return switch (direction) {
            case LEFT, UP -> index + 1;
            case RIGHT, DOWN -> index - 1;
        };
    }

    public int newRow(int index) {
        return switch (direction) {
            case LEFT, RIGHT -> index;
            case DOWN, UP -> next(index);
        };
    }

    public int newColumn(int index) {
        return switch (direction) {
            case LEFT, RIGHT -> next(index);
            case DOWN, UP -> index;
        };
    }

    private int calculateStart() {
        return switch (direction) {
            case LEFT, UP -> 0;
            case RIGHT, DOWN -> boardSize - 1;
        };
    }

    private int calculateEnd() {
        return switch (direction) {
            case LEFT, UP -> boardSize - 1;
            case RIGHT, DOWN -> 0;
        };
    }

}