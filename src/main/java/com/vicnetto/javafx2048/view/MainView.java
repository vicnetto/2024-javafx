package com.vicnetto.javafx2048.view;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainView {

    private Parent view;

    public MainView(MenuView menuView, BoardView boardView, GameInformationView gameInformationView) {
        view = createView(menuView, boardView, gameInformationView);
    }

    private Parent createView(MenuView menuView, BoardView boardView, GameInformationView gameInformationView) {
        BorderPane borderPane = new BorderPane();

        borderPane.setBottom(gameInformationView.getView());
        borderPane.setCenter(boardView.getView());
        borderPane.setTop(menuView.getView());

        borderPane.setStyle("-fx-background-color: #6272a4");

        return borderPane;
    }

    public Parent getView() {
        return view;
    }
}
