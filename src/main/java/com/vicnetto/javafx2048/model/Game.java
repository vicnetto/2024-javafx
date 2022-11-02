package com.vicnetto.javafx2048.model;

import com.vicnetto.javafx2048.builder.SceneBuilder;
import com.vicnetto.javafx2048.controller.BoardController;
import com.vicnetto.javafx2048.controller.GameInformationController;
import com.vicnetto.javafx2048.controller.MenuController;
import com.vicnetto.javafx2048.view.BoardView;
import com.vicnetto.javafx2048.view.GameInformationView;
import com.vicnetto.javafx2048.view.MainView;
import com.vicnetto.javafx2048.view.MenuView;
import javafx.stage.Stage;

public class Game {

    private Game() {
    }

    public static void createGame(Stage stage, GameInformation gameInformation, int currentBoardSize, int currentGoal) {
        gameInformation.setGameWon(false);

        MenuView menuView = new MenuView(currentBoardSize, currentGoal);
        BoardView boardView = new BoardView(currentBoardSize);
        GameInformationView gameInformationView = new GameInformationView();
        MainView mainView = new MainView(menuView, boardView, gameInformationView);

        GameInformationController gameInformationController = new GameInformationController(gameInformationView, gameInformation);
        BoardController boardController = new BoardController(boardView, currentBoardSize);
        new MenuController(menuView, boardController, gameInformationController.getGameInformation(), stage);

        stage.setScene(SceneBuilder.buildScene(mainView, boardController, gameInformation, gameInformationView));
    }
}
