package com.vicnetto.javafx2048;

import com.vicnetto.javafx2048.constant.GameParameter;
import com.vicnetto.javafx2048.model.Game;
import com.vicnetto.javafx2048.model.GameInformation;
import javafx.application.Application;
import javafx.stage.Stage;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) {
        Game.createGame(stage, new GameInformation(), GameParameter.DEFAULT_BOARD_SIZE, GameParameter.DEFAULT_GOAL);
        stage.setTitle("2048 - JavaFX");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}