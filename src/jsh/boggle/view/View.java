package jsh.boggle.view;

import javafx.scene.Group;
import jsh.boggle.model.Model;
import javafx.scene.paint.Color;

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
        int x, y;
        for (x = 0; x < tempBoard.length; x++) {
            for (y = 0; y < tempBoard.length; y++) {
                dice = new Dice()
                        .setPosition(new Position2D().setX(x * diceSize).setY(y * diceSize))
                        .setLetter(tempBoard[x][y])
                        .setColor(((x + y) % 2 == 0) ? Color.GOLDENROD : Color.ALICEBLUE)
                        .setSize(diceSize);
                dice.render();
                group.getChildren().add(dice);
            }
        }
        return group;
    }
}
