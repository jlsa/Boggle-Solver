package jsh.boggle.view;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.StackPane;

/**
 * A cell is a graphical representation of a dice
 * @author Joël Hoekstra
 */
public class Cell extends StackPane {

    public Cell(double x, double y, double size, String textValue, Color color) {
        Rectangle cell = new Rectangle(x, y, size, size);
        cell.setFill(color);
        cell.setStroke(Color.BLACK);

        Label text  = new Label(textValue);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.PURPLE);

        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(cell, text);
    }
}