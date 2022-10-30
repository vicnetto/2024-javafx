package com.vicnetto.javafx2048.view;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuView {

    private MenuItem newGame = new MenuItem("New Game");

    private MenuItem boardSizeText = new MenuItem("");

    private MenuItem goalText = new MenuItem("");

    private MenuItem quit = new MenuItem("Quit");

    private Parent view;

    public MenuView() {
        view = createView();
    }

    private MenuBar createView() {
        MenuBar menuBar = new MenuBar();

        Menu game = new Menu("Game");
        menuBar.getMenus().add(game);

        game.getItems().addAll(newGame, boardSizeText, goalText, quit);

        return menuBar;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getBoardSizeText() {
        return boardSizeText;
    }

    public MenuItem getGoalText() {
        return goalText;
    }

    public MenuItem getQuit() {
        return quit;
    }

    public Parent getView() {
        return view;
    }
}