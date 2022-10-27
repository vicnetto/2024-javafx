package com.vicnetto.javafx2048;

import com.vicnetto.javafx2048.constant.WindowSize;
import com.vicnetto.javafx2048.controller.GameInformationController;
import com.vicnetto.javafx2048.view.GameInformationView;
import com.vicnetto.javafx2048.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GameInformationView gameInformationView = new GameInformationView();
        MainView mainView = new MainView(null, null, gameInformationView);

        GameInformationController gameInformationController = new GameInformationController(gameInformationView);

        Scene scene = new Scene(mainView.getView(), WindowSize.WIDTH.getValue(), WindowSize.LENGTH.getValue());
        stage.setTitle("2024 - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}