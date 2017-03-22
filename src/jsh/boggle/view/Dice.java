package jsh.boggle.view;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.StackPane;

/**
 * A graphical representation of a dice
 * @author Joël Hoekstra
 */
public class Dice extends StackPane {
    private char face;
    private Color color;
    private double size;
    private Position2D position;

    public Dice(Position2D position, double size, char face, Color color) {
        this.position = position;
        this.size = size;
        this.face = face;
        this.color = color;
        render();
    }

    public void render() {
        double x = position.getX();
        double y = position.getY();

        Rectangle dice = new Rectangle(x, y, size, size);
        dice.setFill(color);
        dice.setStroke(Color.BLACK);

        Label text  = new Label(Character.toString(face));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.PURPLE);

        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(dice, text);
    }
}
