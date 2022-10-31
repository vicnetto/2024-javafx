package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.watcher.Watcher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.vicnetto.javafx2048.constant.GameParameter.POSSIBLE_BOARD_SIZES;

public class Board implements Watcher {

    private IntegerProperty boardSize;

    private IntegerProperty[][] board;

    private Random random = new Random();


    public Board(Integer boardSize) {
        this.boardSize = new SimpleIntegerProperty(boardSize);

        board = new IntegerProperty[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new SimpleIntegerProperty(0);
            }
        }
    }

    public void setNewBoardSize() {
        int newBoardSize = Arrays.binarySearch(POSSIBLE_BOARD_SIZES, boardSize.get()) + 1;

        boardSize.set(newBoardSize < POSSIBLE_BOARD_SIZES.length ?
                POSSIBLE_BOARD_SIZES[newBoardSize] : POSSIBLE_BOARD_SIZES[0]);
    }

    public boolean move(Direction direction) {

        List<Pair<Integer, Integer>> emptySpaces = new ArrayList<>();

        // Make the movement according to the direction.
        move(direction, true);

        // Remove gaps from the board.
        for (int i = 0; i < (boardSize.get() / 2) - 1; i++)
            move(direction, false);

        // Verify if there are possible movements, or the game is over.
        boolean isEndGame = verifyPossibleMovements(emptySpaces);

        if (!emptySpaces.isEmpty())
            enterTwoInRandomPosition(emptySpaces, false);

        return isEndGame;
    }

    /**
     * @param direction -> In which direction the numbers must be combined.
     * @param shouldCombine -> To fill the empty spaces between two numbers after a move, this function is called
     *                      with the parameter equals to false.
     *                      - For example, combining to the right:
     *                      1            2            3
     *                      2 2 2 2 -> 0 4 2 2 -> 0 4 2 2 -> 0 4 0 4
     *                      - So to fill these gaps, the function is called with shouldCombine equals to false:
     *                      1            2            3
     *                      0 4 0 4 -> 0 4 0 4 -> 0 0 4 4 -> 0 0 4 4
     *
     */
    private void move(Direction direction, boolean shouldCombine) {

        MoveDirection moveDirection = new MoveDirection(boardSize.get(), direction);

        for (int i = moveDirection.start; moveDirection.rowStopCondition(i); i = moveDirection.next(i)) {
            for (int j = moveDirection.start; moveDirection.columnStopCondition(j); j = moveDirection.next(j)) {
                if (board[i][j].get() == 0)
                    continue;

                int ii = moveDirection.newRow(i);
                int jj = moveDirection.newColumn(j);

                // If the next number is equal, and if the cells must be combined, sum and reallocate to the next position.
                if (shouldCombine && board[i][j].get() == board[ii][jj].get()) {
                    board[ii][jj].set(board[i][j].get() * 2);
                    board[i][j].set(0);
                }

                // If the next value is a zero, swap.
                if (board[ii][jj].get() == 0) {
                    board[ii][jj].set(board[i][j].get());
                    board[i][j].set(0);
                }
            }
        }
    }

    /**
     * @return -> True if there is at least one available move, and false if there is none.
     */
    private boolean verifyPossibleMovements(List<Pair<Integer, Integer>> emptySpaces) {
        boolean isPossibleToContinue = false;
        emptySpaces.clear();

        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                int currentNumber = board[i][j].get();

                if (currentNumber == 0)
                    emptySpaces.add(new Pair<>(i, j));

                if (!isPossibleToContinue && checkNeighbors(i, j, currentNumber))
                    isPossibleToContinue = true;
            }
        }

        return isPossibleToContinue;
    }

    public void enterTwoInRandomPosition(List<Pair<Integer, Integer>> emptyEspaces, boolean isFirstTwo) {
        if (isFirstTwo) {
            board[random.nextInt(boardSize.get() - 1)][random.nextInt(boardSize.get() - 1)].set(2);
            return;
        }

        int randomPosition = emptyEspaces.size() == 1 ? 0 : random.nextInt(emptyEspaces.size() - 1);
        Pair<Integer, Integer> position = emptyEspaces.get(randomPosition);
        board[position.getKey()][position.getValue()].set(2);
    }

    private boolean checkNeighbors(int i, int j, int currentNumber) {
        if (i != 0 && board[i - 1][j].get() == currentNumber)
            return true;

        if (i != boardSize.get() - 1 && board[i + 1][j].get() == currentNumber)
            return true;

        if (j != 0 && board[i][j - 1].get() == currentNumber)
            return true;

        if (j != boardSize.get() - 1 && board[i][j + 1].get() == currentNumber)
            return true;

        return false;
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
