package com.vicnetto.javafx2048.controller;

import com.vicnetto.javafx2048.model.Board;
import com.vicnetto.javafx2048.model.Direction;
import com.vicnetto.javafx2048.model.GameInformation;
import com.vicnetto.javafx2048.view.BoardView;
import com.vicnetto.javafx2048.view.GameInformationView;

public class BoardController {

    private Board board;

    private BoardView boardView;

    public BoardController(BoardView boardView, int boardSize) {

        setView(boardView, boardSize);
    }

    private void setView(BoardView boardView, int boardSize) {
        this.boardView = boardView;

        bindBoardWithLabels(boardSize);

        board.initializeWithTwos();
        boardView.updateRectangleColors();
    }

    private void bindBoardWithLabels(int boardSize) {
        board = new Board(boardSize);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boardView.getLabels().get(i).get(j).textProperty().bind((board.getBoard()[i][j]).asString());
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void makeMove(Direction direction, GameInformation gameInformation, GameInformationView gameInformationView) {
        boolean hasOtherMoves = board.move(direction);
        boardView.updateRectangleColors();

        int currentMax = getMaxValueOnTheBoard();
        gameInformation.setScore(currentMax);
        gameInformation.setBestScore(Math.max(gameInformation.getBestScore(), currentMax));
        gameInformation.addWin(gameInformationView);

        if (!hasOtherMoves)
            gameInformationView.setGameLostMessage();
    }

    private int getMaxValueOnTheBoard() {
        int max = 0;

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                max = Math.max(board.getBoard()[i][j].get(), max);
            }
        }

        return max;
    }
}
