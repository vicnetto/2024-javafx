package com.vicnetto.javafx2048.controller;

import com.vicnetto.javafx2048.constant.GameParameter;
import com.vicnetto.javafx2048.model.Board;
import com.vicnetto.javafx2048.view.BoardView;

public class BoardController {

    private Board board;

    public BoardController(BoardView boardView) {
        setView(boardView);
    }

    private void setView(BoardView boardView) {
        board = new Board(GameParameter.DEFAULT_BOARD_SIZE);

        for (int i = 0; i < GameParameter.DEFAULT_BOARD_SIZE; i++) {
            for (int j = 0; j < GameParameter.DEFAULT_BOARD_SIZE; j++) {
                 boardView.getLabels().get(i).get(j).textProperty().bind((board.getBoard()[i][j]).asString());
            }
        }

        board.move(true, false);
//        board.downMove();

        System.out.println("Hello");
    }
}
