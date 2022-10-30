package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.watcher.Watcher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;

import static com.vicnetto.javafx2048.constant.GameParameter.POSSIBLE_BOARD_SIZES;

public class Board implements Watcher {

    private IntegerProperty boardSize;

    private IntegerProperty[][] board;

    public Board(Integer boardSize) {
        this.boardSize = new SimpleIntegerProperty(boardSize);

        board = new IntegerProperty[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new SimpleIntegerProperty(2);
            }
        }
    }

    public void setNewBoardSize() {
        int newBoardSize = Arrays.binarySearch(POSSIBLE_BOARD_SIZES, boardSize.get()) + 1;

        boardSize.set(newBoardSize < POSSIBLE_BOARD_SIZES.length ?
                POSSIBLE_BOARD_SIZES[newBoardSize] : POSSIBLE_BOARD_SIZES[0]);
    }

    public void move(Direction direction) {

        move(direction, true);

        for (int i = 0; i < (boardSize.get() / 2) - 1; i++)
            move(direction, false);
    }

    private void move(Direction direction, boolean shouldCombine) {

        MoveDirection moveDirection = new MoveDirection(boardSize.get(), direction);

        for (int i = moveDirection.start; moveDirection.rowStopCondition(i); i = moveDirection.next(i)) {
            for (int j = moveDirection.start; moveDirection.columnStopCondition(j); j = moveDirection.next(j)) {
                if (board[i][j].get() == 0)
                    continue;

                int ii = moveDirection.newRow(i);
                int jj = moveDirection.newColumn(j);

                if (shouldCombine && board[i][j].get() == board[ii][jj].get()) {
                    board[ii][jj].set(board[i][j].get() * 2);
                    board[i][j].set(0);
                }

                if (board[ii][jj].get() == 0) {
                    board[ii][jj].set(board[i][j].get());
                    board[i][j].set(0);
                }
            }
        }
    }

    public int getBoardSize() {
        return boardSize.get();
    }

    public IntegerProperty boardSizeProperty() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize.set(boardSize);

        // After changing the board size, the board needs to be recreated.

        board = new IntegerProperty[boardSize][boardSize];
    }

    public IntegerProperty[][] getBoard() {
        return board;
    }

    @Override
    public void newGame() {
        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                board[i][j] = new SimpleIntegerProperty(0);
            }
        }
    }
}
