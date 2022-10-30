package com.vicnetto.javafx2048.controller;

import com.vicnetto.javafx2048.model.Board;
import com.vicnetto.javafx2048.model.GameInformation;
import com.vicnetto.javafx2048.view.MenuView;
import javafx.beans.binding.Bindings;
import javafx.scene.input.KeyCombination;

import java.util.Arrays;

public class MenuController {

    public MenuController(MenuView menuView, Board board, GameInformation gameInformation) {
        setView(menuView, board, gameInformation);
    }

    private void setView(MenuView menuView, Board board, GameInformation gameInformation) {
        menuView.getNewGame().setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        menuView.getNewGame().setOnAction(actionEvent -> {
            board.newGame();
            gameInformation.newGame();
        });

        menuView.getBoardSizeText().textProperty().bind(Bindings
                .concat("Size: ")
                .concat(board.boardSizeProperty().asString()));
        menuView.getBoardSizeText().setOnAction(actionEvent -> {
            board.setNewBoardSize();
            board.newGame();
            gameInformation.newGame();
        });

        menuView.getGoalText().textProperty().bind(Bindings
                .concat("Goal: ")
                .concat(gameInformation.goalProperty().asString()));
        menuView.getGoalText().setOnAction(actionEvent -> {
            gameInformation.setNewBoardSize();
            board.newGame();
            gameInformation.newGame();
        });

        menuView.getQuit().setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        menuView.getQuit().setOnAction(actionEvent -> {
            System.exit(0);
        });
    }
}
