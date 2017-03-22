package jsh.boggle.view;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.StackPane;

/**
 * A graphical representation of a dice in real life.
 * Extends a StackPane so that the letter can be shown above a rectangle.
 * A Dice has a face (char),
 * A Color,
 * A size, and
 * A position
 *
 * @author Joël Hoekstra
 */
public class Dice extends StackPane {
    private char letter;
    private Color color;
    private double size;
    private Position2D position;

    public Dice() { }

    public Dice setPosition(Position2D position) {
        this.position = position;
        return this;
    }

    public Dice setSize(double size) {
        this.size = size;
        return this;
    }

    public Dice setLetter(char letter) {
        this.letter = letter;
        return this;
    }

    public Dice setColor(Color color) {
        this.color = color;
        return this;
    }

    public void render() {
        double x = position.getX();
        double y = position.getY();

        Rectangle dice = new Rectangle(x, y, size, size);
        dice.setFill(color);
        dice.setStroke(Color.BLACK);

        Label text  = new Label(Character.toString(letter));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.PURPLE);

        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(dice, text);
    }
}
