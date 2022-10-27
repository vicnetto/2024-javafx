package com.vicnetto.javafx2048.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class GameInformationView {

    private Label scoreText = new Label("");

    private Label winsText = new Label("");

    private Label attemptsText = new Label("");

    private Label bestScoreText = new Label("");

    private Parent view;

    public GameInformationView() {
        view = createView();
    }

    private HBox createView() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        HBox title = new HBox(gameTitle());
        title.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);

        HBox data = new HBox(gameData());
        data.setAlignment(Pos.CENTER);
        HBox.setHgrow(data, Priority.ALWAYS);

        hBox.getChildren().addAll(title, data);

        return hBox;
    }

    private Node gameTitle() {
        Label title = new Label("2048");
        title.setStyle("-fx-font: 50 arial; -fx-font-weight: bold");
        title.setTextFill(Paint.valueOf("#ffb86c"));

        return title;
    }

    private Node gameData() {
        GridPane gridPane = new GridPane();

        Label score = new Label("Score: ");
        score.setStyle("-fx-font: 15 arial; -fx-font-weight: bold");
        scoreText.setStyle("-fx-font: 15 arial;");
        Label wins = new Label("Wins: ");
        wins.setStyle("-fx-font: 15 arial; -fx-font-weight: bold");
        winsText.setStyle("-fx-font: 15 arial;");
        Label attempts = new Label("Attempts: ");
        attempts.setStyle("-fx-font: 15 arial; -fx-font-weight: bold");
        attemptsText.setStyle("-fx-font: 15 arial;");
        Label bestScore = new Label("Best Score: ");
        bestScore.setStyle("-fx-font: 15 arial; -fx-font-weight: bold");
        bestScoreText.setStyle("-fx-font: 15 arial;");

        GridPane.setRowIndex(score, 0);
        GridPane.setRowIndex(wins, 1);
        GridPane.setRowIndex(attempts, 2);
        GridPane.setRowIndex(bestScore, 3);
        GridPane.setConstraints(scoreText, 1, 0);
        GridPane.setConstraints(winsText, 1, 1);
        GridPane.setConstraints(attemptsText, 1, 2);
        GridPane.setConstraints(bestScoreText, 1, 3);

        gridPane.getChildren().addAll(score, wins, attempts, bestScore,
                                      scoreText, winsText, attemptsText, bestScoreText);

        gridPane.getRowConstraints().addAll(
                new RowConstraints(10, 30, 100, Priority.SOMETIMES, VPos.CENTER, true),
                new RowConstraints(10, 30, 100, Priority.SOMETIMES, VPos.CENTER, true),
                new RowConstraints(10, 30, 100, Priority.SOMETIMES, VPos.CENTER, true),
                new RowConstraints(10, 30, 100, Priority.SOMETIMES, VPos.CENTER, true)
        );
        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(10, 150, 300, Priority.SOMETIMES, HPos.RIGHT, true),
                new ColumnConstraints(10, 20, 300, Priority.SOMETIMES, HPos.LEFT, true)
        );

        return gridPane;
    }

    public Parent getView() {
        return view;
    }

    public Label getWinsText() {
        return winsText;
    }

    public Label getAttemptsText() {
        return attemptsText;
    }

    public Label getScoreText() {
        return scoreText;
    }

    public Label getBestScoreText() {
        return bestScoreText;
    }
}
