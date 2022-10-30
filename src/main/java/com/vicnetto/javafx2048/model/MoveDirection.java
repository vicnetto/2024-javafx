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
            case RIGHT -> index < end;
            case LEFT -> index > 0;
            case DOWN -> index <= end;
            case UP -> index >= 0;
        };
    }

    public boolean rowStopCondition(int index) {
        return switch (direction) {
            case RIGHT -> index <= end;
            case LEFT -> index >= 0;
            case DOWN -> index < end;
            case UP -> index > 0;
        };
    }

    public int next(int index) {
        return switch (direction) {
            case RIGHT, DOWN -> index + 1;
            case LEFT, UP -> index - 1;
        };
    }

    public int newRow(int index) {
        return switch (direction) {
            case RIGHT, LEFT -> index;
            case UP, DOWN -> next(index);
        };
    }

    public int newColumn(int index) {
        return switch (direction) {
            case RIGHT, LEFT -> next(index);
            case UP, DOWN -> index;
        };
    }

    private int calculateStart() {
        return switch (direction) {
            case RIGHT, DOWN -> 0;
            case LEFT, UP -> boardSize - 1;
        };
    }

    private int calculateEnd() {
        return switch (direction) {
            case RIGHT, DOWN -> boardSize - 1;
            case LEFT, UP -> 0;
        };
    }

}