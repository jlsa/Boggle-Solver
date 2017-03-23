package jsh.boggle.view;

import javafx.scene.Group;
import jsh.boggle.model.Model;
import javafx.scene.paint.Color;

/**
 * @author Joël Hoekstra
 */
public class View {
    private Model model = null;

    public View() {}

    public void setModel(Model model) {
        this.model = model;
    }

    public Group generateBoard(double diceWidth, double diceHeight) {
        Group group = new Group();
        char[][] tempBoard = model.getBoard().getBoard();
        Dice dice;
        int x, y;
        for (x = 0; x < tempBoard.length; x++) {
            for (y = 0; y < tempBoard.length; y++) {
                dice = new Dice()
                        .setPosition(new Position2D().setX(x * diceWidth).setY(y * diceHeight))
                        .setLetter(tempBoard[x][y])
                        .setColor(((x + y) % 2 == 0) ? Color.GOLDENROD : Color.ALICEBLUE)
                        .setSize(diceWidth, diceHeight)
                        .setActive(false)
                        .setBoardPosition(new Position2D().setX(x).setY(y));
                dice.render();
                group.getChildren().add(dice);
            }
        }
        return group;
    }
}
