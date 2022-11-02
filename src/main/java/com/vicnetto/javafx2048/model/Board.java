package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.watcher.Watcher;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Watcher {

    private final IntegerProperty boardSize;

    private IntegerProperty[][] board;

    private final Random random = new Random();


    public Board(Integer boardSize) {
        this.boardSize = new SimpleIntegerProperty(boardSize);

        initializeBoardWithZeros();
    }

    private void initializeBoardWithZeros() {
        board = new IntegerProperty[boardSize.get()][boardSize.get()];

        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                board[i][j] = new SimpleIntegerProperty(0);
            }
        }
    }

    public boolean move(Direction direction) {

        int[][] boardBeforeMove = new int[boardSize.get()][boardSize.get()];
        List<Pair<Integer, Integer>> emptySpaces = new ArrayList<>();

        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                boardBeforeMove[i][j] = board[i][j].get();
            }
        }

        // Remove gaps from the board.
         for (int i = 0; i < boardSize.get() - 1; i++)
             move(direction, false);

        // Make the movement according to the direction.
        move(direction, true);

        // Verify if there are possible movements, or the game is over.
        boolean isEndGame = verifyPossibleMovements(emptySpaces);

        if (!verifyIfBoardIsTheSameAfterMove(boardBeforeMove) && !emptySpaces.isEmpty())
            enterTwoInRandomPosition(emptySpaces);

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

                int ii = moveDirection.newRow(i);
                int jj = moveDirection.newColumn(j);

                // If the next number is equal, and if the cells must be combined, sum and reallocate to the next position.
                if (shouldCombine && board[i][j].get() == board[ii][jj].get()) {
                    board[i][j].set(board[ii][jj].get() * 2);
                    board[ii][jj].set(0);
                    move(direction, false);
                }

                // If the next value is a zero, swap.
                if (board[i][j].get() == 0 && board[ii][jj].get() != 0) {
                    board[i][j].set(board[ii][jj].get());
                    board[ii][jj].set(0);
                }
            }
        }
    }

    /**
     * @return -> True if there is at least one available move, and false if there is none.
     */
    private boolean verifyPossibleMovements(List<Pair<Integer, Integer>> emptySpaces) {
        int stuckNumbers = 0;
        emptySpaces.clear();

        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                int currentNumber = board[i][j].get();

                if (currentNumber == 0)
                    emptySpaces.add(new Pair<>(i, j));

                if (currentNumber != 0 && !checkNeighbors(i, j, currentNumber))
                    stuckNumbers++;
            }
        }

        return stuckNumbers != (boardSize.get() * boardSize.get());
    }

    public void enterTwoInRandomPosition(List<Pair<Integer, Integer>> emptyEspaces) {
        int randomPosition = emptyEspaces.size() == 1 ? 0 : random.nextInt(emptyEspaces.size() - 1);
        Pair<Integer, Integer> position = emptyEspaces.get(randomPosition);
        board[position.getKey()][position.getValue()].set(2);
    }

    public void initializeWithTwos() {
        int i = random.nextInt(boardSize.get() - 1);
        int j = random.nextInt(boardSize.get() - 1);

        board[i][j].set(2);

        int ii;
        int jj;

        do {
            ii = random.nextInt(boardSize.get() - 1);
            jj = random.nextInt(boardSize.get() - 1);

            board[ii][jj].set(2);
        } while (ii == i && jj == j);
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

    private boolean verifyIfBoardIsTheSameAfterMove(int[][] board) {
        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                if (board[i][j] != this.board[i][j].get())
                    return false;
            }
        }

        return true;
    }

    public int getBoardSize() {
        return boardSize.get();
    }

    public IntegerProperty[][] getBoard() {
        return board;
    }

    @Override
    public void newGame() {
        for (int i = 0; i < boardSize.get(); i++) {
            for (int j = 0; j < boardSize.get(); j++) {
                board[i][j].set(0);
            }
        }

        initializeWithTwos();
    }
}
