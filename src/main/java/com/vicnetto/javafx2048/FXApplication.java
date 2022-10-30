package com.vicnetto.javafx2048;

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
import javafx.scene.Scene;
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

        Scene scene = new Scene(mainView.getView(), WindowSize.WIDTH.getValue(), WindowSize.LENGTH.getValue());
        stage.setTitle("2024 - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}