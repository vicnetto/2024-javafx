package com.vicnetto.javafx2048.view;

import com.vicnetto.javafx2048.constant.GameParameter;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.Objects;

public class MenuView {

    private final MenuItem newGame = new MenuItem("New Game");

    private final MenuItem[] sizes = new MenuItem[GameParameter.POSSIBLE_BOARD_SIZES.length];

    private final MenuItem[] goals = new MenuItem[GameParameter.POSSIBLE_GOALS.length];

    private final MenuItem quit = new MenuItem("Quit");

    private final Parent view;

    public MenuView(int currentBoardSize, int currentGoal) {
        view = createView(currentBoardSize, currentGoal);
    }

    private MenuBar createView(int currentBoardSize, int currentGoal) {
        MenuBar menuBar = new MenuBar();

        Menu game = new Menu("Game");
        game.getItems().addAll(newGame, quit);

        Menu size = new Menu("Size");
        for (int i = 0; i < GameParameter.POSSIBLE_BOARD_SIZES.length; i++) {
            sizes[i] = new MenuItem();

            if (Objects.equals(GameParameter.POSSIBLE_BOARD_SIZES[i], currentBoardSize))
                sizes[i].setText(GameParameter.POSSIBLE_BOARD_SIZES[i] + " (actual)");
            else
                sizes[i].setText(String.valueOf(GameParameter.POSSIBLE_BOARD_SIZES[i]));

            size.getItems().add(sizes[i]);
        }

        Menu goal = new Menu("Goal");
        for (int i = 0; i < GameParameter.POSSIBLE_GOALS.length; i++) {
            goals[i] = new MenuItem();

            if (Objects.equals(GameParameter.POSSIBLE_GOALS[i], currentGoal))
                goals[i].setText(GameParameter.POSSIBLE_GOALS[i] + " (actual)");
            else
                goals[i].setText(String.valueOf(GameParameter.POSSIBLE_GOALS[i]));

            goal.getItems().add(goals[i]);
        }

        menuBar.getMenus().addAll(game, size, goal);

        return menuBar;
    }

    public MenuItem[] getSizes() {
        return sizes;
    }

    public MenuItem[] getGoals() {
        return goals;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getQuit() {
        return quit;
    }

    public Parent getView() {
        return view;
    }
}