package com.vicnetto.javafx2048.view;

import com.vicnetto.javafx2048.constant.CellColor;
import com.vicnetto.javafx2048.constant.ColorPalette;
import com.vicnetto.javafx2048.constant.UIParameter;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BoardView {

    private final List<List<Label>> labels = new ArrayList<>();

    private final List<List<Rectangle>> rectangles = new ArrayList<>();

    private final Parent view;

    public BoardView(int boardSize) {
        view = createView(boardSize);
    }

    public StackPane createView(int boardSize) {
        StackPane stackPane = new StackPane();
        Rectangle rectangle = new Rectangle(UIParameter.BOARD_BORDER_UI_SIZE, UIParameter.BOARD_BORDER_UI_SIZE);
        rectangle.setFill(Paint.valueOf(ColorPalette.DARK_GREY));

        GridPane gridPane = new GridPane();

        createBoard(gridPane, boardSize);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(UIParameter.SPACE_BETWEEN_CELLS);
        gridPane.setVgap(UIParameter.SPACE_BETWEEN_CELLS);

        stackPane.getChildren().addAll(rectangle, gridPane);

        return stackPane;
    }

    private void createBoard(GridPane gridPane, int boardSize) {
        rectangles.clear();
        labels.clear();

        for (int i = 0; i < boardSize; i++) {
            rectangles.add(new ArrayList<>());
            labels.add(new ArrayList<>());

            for (int j = 0; j < boardSize; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Paint.valueOf(ColorPalette.WHITE));

                Label label = new Label("1");
                label.setTextFill(Paint.valueOf(ColorPalette.LIGHT_GREY));
                label.setStyle("-fx-font: " + (28 - (boardSize*2)) + " arial; -fx-font-weight: bold");

                int cellSize = ((UIParameter.BOARD_UI_SIZE - ((boardSize - 1) * UIParameter.SPACE_BETWEEN_CELLS)) / boardSize);
                rectangle.setHeight(cellSize);
                rectangle.setWidth(cellSize);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(rectangle, label);

                rectangles.get(i).add(rectangle);
                labels.get(i).add(label);

                GridPane.setConstraints(stackPane, j, i);
                gridPane.getChildren().add(stackPane);
            }
        }
    }

    public void updateRectangleColors() {
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < rectangles.size(); j++) {
                rectangles.get(i).get(j).setFill(CellColor.getColorAccordingToNumber(Integer.parseInt(labels.get(i).get(j).getText())));
            }
        }
    }

    public Parent getView() {
        return view;
    }

    public List<List<Label>> getLabels() {
        return labels;
    }
}
