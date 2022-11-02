package com.vicnetto.javafx2048.controller;

import com.vicnetto.javafx2048.constant.GameParameter;
import com.vicnetto.javafx2048.model.Game;
import com.vicnetto.javafx2048.model.GameInformation;
import com.vicnetto.javafx2048.view.MenuView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class MenuController {

    public MenuController(MenuView menuView, BoardController boardController, GameInformation gameInformation, Stage stage) {
        setView(menuView, boardController, gameInformation, stage);
    }

    private void setView(MenuView menuView, BoardController boardController, GameInformation gameInformation, Stage stage) {
        menuView.getNewGame().setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        menuView.getNewGame().setOnAction(actionEvent -> {
            gameInformation.newGame();
            Game.createGame(stage, gameInformation, boardController.getBoard().getBoardSize(), gameInformation.getGoal());
        });

        MenuItem[] menuItems = menuView.getSizes();
        for (int i = 0; i < GameParameter.POSSIBLE_BOARD_SIZES.length; i++) {
            MenuItem item = menuItems[i];

            int finalI = i;
            item.setOnAction(actionEvent -> {
                if (GameParameter.POSSIBLE_BOARD_SIZES[finalI] != boardController.getBoard().getBoardSize()) {
                    gameInformation.newGame();
                    Game.createGame(stage, gameInformation, GameParameter.POSSIBLE_BOARD_SIZES[finalI], gameInformation.getGoal());
                }
            });
        }

        menuItems = menuView.getGoals();
        for (int i = 0; i < GameParameter.POSSIBLE_GOALS.length; i++) {
            MenuItem item = menuItems[i];

            int finalI = i;
            item.setOnAction(actionEvent -> {
                if (GameParameter.POSSIBLE_GOALS[finalI] != gameInformation.getGoal()) {
                    gameInformation.setGoal(GameParameter.POSSIBLE_GOALS[finalI]);
                    gameInformation.newGame();
                    Game.createGame(stage, gameInformation, boardController.getBoard().getBoardSize(), gameInformation.getGoal());
                }
            });
        }

        menuView.getQuit().setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        menuView.getQuit().setOnAction(actionEvent -> System.exit(0));
    }
}
