package com.vicnetto.javafx2048.builder;

import com.vicnetto.javafx2048.constant.WindowSize;
import com.vicnetto.javafx2048.controller.BoardController;
import com.vicnetto.javafx2048.controller.GameInformationController;
import com.vicnetto.javafx2048.controller.MenuController;
import com.vicnetto.javafx2048.model.Direction;
import com.vicnetto.javafx2048.view.MainView;
import javafx.scene.Scene;

public class SceneBuilder {

    public static Scene buildScene(MainView mainView, GameInformationController gameInformationController,
                                   BoardController boardController, MenuController menuController) {
        Scene scene = new Scene(mainView.getView(), WindowSize.WIDTH.getValue(), WindowSize.LENGTH.getValue());

        scene.setOnKeyPressed(key -> {
            if (key.getCode().getName().equals("Up"))
                boardController.makeMove(Direction.UP);

            if (key.getCode().getName().equals("Down"))
                boardController.makeMove(Direction.DOWN);

            if (key.getCode().getName().equals("Left"))
                boardController.makeMove(Direction.LEFT);

            if (key.getCode().getName().equals("Right"))
                boardController.makeMove(Direction.RIGHT);
        });

        return scene;
    }
}
