package jsh.boggle.view;

import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.StackPane;
import jsh.boggle.util.Position2D;

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
    private double width;
    private double height;
    private Position2D<Double> position;
    private Position2D<Integer> boardPosition;
    private boolean active;

    public Dice() { }

    public char getLetter() {
        return letter;
    }

    public Dice setPosition(Position2D<Double> position) {
        this.position = position;
        return this;
    }

    public Dice setBoardPosition(Position2D<Integer> position) {
        this.boardPosition = position;
        return this;
    }

    public Position2D<Integer> getBoardPosition() {
        return boardPosition;
    }

    public Position2D<Double> getPosition() {
        return position;
    }

    public Dice setSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Dice setActive(boolean active) {
        this.active = active;
        return this;
    }

    public boolean isActive() {
        return active;
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
        double x = getPosition().getX();
        double y = getPosition().getY();

        Rectangle dice = new Rectangle(x, y, width, height);
        dice.setFill(color);
        dice.setStroke(Color.BLACK);

        Label text  = new Label(Character.toString(letter));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, (Double.valueOf(height / 2).intValue())));
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.BLACK);
        if (isActive()) {
            dice.setFill(Color.DARKGRAY);
            text.setUnderline(true);
        }

        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(dice, text);
    }
}
