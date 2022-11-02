package com.vicnetto.javafx2048.view;

import com.vicnetto.javafx2048.constant.ColorPalette;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import static com.vicnetto.javafx2048.constant.TextStyle.DATA_TEXT_STYLE;
import static com.vicnetto.javafx2048.constant.TextStyle.LABEL_TEXT_STYLE;

public class GameInformationView {

    private final Text message = new Text("");
    private final Label scoreText = new Label("");

    private final Label winsText = new Label("");

    private final Label attemptsText = new Label("");

    private final Label bestScoreText = new Label("");

    private final Parent view;

    public GameInformationView() {
        view = createView();
    }

    private HBox createView() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        HBox title = new HBox(gameTitle());
        title.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(title, Priority.ALWAYS);

        HBox data = new HBox(gameData());
        data.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(data, Priority.ALWAYS);

        hBox.getChildren().addAll(title, data);

        return hBox;
    }

    private Node gameTitle() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Text text = new Text("2048");
        text.setStyle("-fx-font: 60 arial; -fx-font-weight: bold");
        text.setFill(Paint.valueOf(ColorPalette.RED_SUPER_GIANT));
        text.setStrokeWidth(2);
        text.setStroke(Paint.valueOf(ColorPalette.ORANGE_SPACE_DEBRIS));

        VBox statusVBox = new VBox();
        statusVBox.setAlignment(Pos.CENTER);
        Text status = new Text("STATUS:");
        status.setStyle("-fx-font: 14 arial; -fx-font-weight: bold");
        status.setFill(Paint.valueOf(ColorPalette.WHITE));
        message.setText("Goal not yet achieved!");
        message.setStyle("-fx-font: 14 arial; -fx-font-weight: bold");
        message.setFill(Paint.valueOf(ColorPalette.VERY_LIGHT_GREY));
        statusVBox.getChildren().addAll(status, message);

        vBox.getChildren().addAll(text, statusVBox);

        return vBox;
    }

    public void setGameWinnerMessage() {
        message.setText("You won! Keep going!");
        message.setStyle("-fx-font: 14 arial; -fx-font-weight: bold");
        message.setFill(Paint.valueOf(ColorPalette.GREEN_PLANETARIUM));
    }

    public void setGameLostMessage() {
        message.setText("You lost! Try again!");
        message.setStyle("-fx-font: 14 arial; -fx-font-weight: bold");
        message.setFill(Paint.valueOf(ColorPalette.RED_SUPER_GIANT));
    }

    private Node gameData() {
        GridPane gridPane = new GridPane();

        Label score = new Label("Score: ");
        score.setStyle(LABEL_TEXT_STYLE);
        scoreText.setStyle(DATA_TEXT_STYLE);
        Label wins = new Label("Wins: ");
        wins.setStyle(LABEL_TEXT_STYLE);
        winsText.setStyle(DATA_TEXT_STYLE);
        Label attempts = new Label("Attempts: ");
        attempts.setStyle(LABEL_TEXT_STYLE);
        attemptsText.setStyle(DATA_TEXT_STYLE);
        Label bestScore = new Label("Best Score: ");
        bestScore.setStyle(LABEL_TEXT_STYLE);
        bestScoreText.setStyle(DATA_TEXT_STYLE);

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
                new ColumnConstraints(10, 120, 300, Priority.SOMETIMES, HPos.RIGHT, true),
                new ColumnConstraints(10, 70, 300, Priority.SOMETIMES, HPos.LEFT, true)
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
