package com.vicnetto.javafx2048.builder;

import com.vicnetto.javafx2048.constant.WindowSize;
import com.vicnetto.javafx2048.controller.BoardController;
import com.vicnetto.javafx2048.model.Direction;
import com.vicnetto.javafx2048.model.GameInformation;
import com.vicnetto.javafx2048.view.GameInformationView;
import com.vicnetto.javafx2048.view.MainView;
import javafx.scene.Scene;

public class SceneBuilder {

    private SceneBuilder() {
    }

    public static Scene buildScene(MainView mainView, BoardController boardController, GameInformation gameInformation, GameInformationView gameInformationView) {
        Scene scene = new Scene(mainView.getView(), WindowSize.WIDTH.getValue(), WindowSize.LENGTH.getValue());

        scene.setOnKeyPressed(key -> {
            if (key.getCode().getName().equals("Up"))
                boardController.makeMove(Direction.UP, gameInformation, gameInformationView);

            if (key.getCode().getName().equals("Down"))
                boardController.makeMove(Direction.DOWN, gameInformation, gameInformationView);

            if (key.getCode().getName().equals("Left"))
                boardController.makeMove(Direction.LEFT, gameInformation, gameInformationView);

            if (key.getCode().getName().equals("Right"))
                boardController.makeMove(Direction.RIGHT, gameInformation, gameInformationView);
        });

        return scene;
    }
}
