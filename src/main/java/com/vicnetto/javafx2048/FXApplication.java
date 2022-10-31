package com.vicnetto.javafx2048;

import com.vicnetto.javafx2048.builder.SceneBuilder;
import com.vicnetto.javafx2048.constant.WindowSize;
import com.vicnetto.javafx2048.controller.BoardController;
import com.vicnetto.javafx2048.controller.GameInformationController;
import com.vicnetto.javafx2048.controller.MenuController;
import com.vicnetto.javafx2048.model.Board;
import com.vicnetto.javafx2048.view.BoardView;
import com.vicnetto.javafx2048.view.GameInformationView;
import com.vicnetto.javafx2048.view.MainView;
import com.vicnetto.javafx2048.view.MenuView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) {
        MenuView menuView = new MenuView();
        BoardView boardView = new BoardView();
        GameInformationView gameInformationView = new GameInformationView();
        MainView mainView = new MainView(menuView, boardView, gameInformationView);

        GameInformationController gameInformationController = new GameInformationController(gameInformationView);
        BoardController boardController = new BoardController(boardView);
        MenuController menuController = new MenuController(menuView, new Board(4), gameInformationController.getGameInformation());

        stage.setTitle("2024 - JavaFX");
        stage.setScene(SceneBuilder.buildScene(mainView, gameInformationController, boardController, menuController));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}