package jsh.boggle.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import jsh.boggle.model.Board;
import jsh.boggle.model.Model;

/**
 * @author Joël Hoekstra
 */
public class View {
    private double diceSize = 100.0;
    private Model model = null;

    public View() {}

    public void setModel(Model model) {
        this.model = model;
    }

    public Group generateBoard() {
        Group group = new Group();
        char[][] tempBoard = model.getBoard().getBoard();
        Dice dice;
        Color color;
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard.length; y++) {
                if ((x + y) % 2 == 0) {
                    color = Color.GOLDENROD;
                } else {
                    color = Color.ALICEBLUE;
                }
                Position2D position = new Position2D(x * diceSize, y * diceSize);
                dice = new Dice(position, diceSize, tempBoard[x][y], color);
                group.getChildren().add(dice);
            }
        }
        return group;
    }
}
