package jsh.boggle.view;

import javafx.scene.Group;
import javafx.scene.Node;
import jsh.boggle.model.Model;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * @author Joël Hoekstra
 */
public class View {
    private Model model = null;

    private Group board;
    private Position2D<Double> diceSize;

    public View() {

    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void render() {
        board = generateBoard();
    }

    public Group getBoardGroup() {
        board = generateBoard();
        return board;
    }

    public void deActivateAllDice() {
        for (int n = 0; n < board.getChildren().size(); n++) {
            Node node = board.getChildren().get(n);
            if (node instanceof Dice) {
                Dice die = ((Dice) node);
                die.setActive(false);
                die.render();
            }
        }
    }

    public void activateDice(String word) {
        ArrayList<Position2D<Integer>> positions = model.getPositionsFromWord(word);
        if (positions != null) {
            for (int j = 0; j < board.getChildren().size(); j++) {
                Node node = board.getChildren().get(j);
                if (node instanceof Dice) {
                    Dice die = ((Dice) node);
                    for (int i = 0; i < positions.size(); i++) {
                        int posX = positions.get(i).getX();
                        int posY = positions.get(i).getY();

                        int boardX = die.getBoardPosition().getX();
                        int boardY = die.getBoardPosition().getY();

                        if (posX == boardX && posY == boardY) {
                            die.setActive(true);
                            die.render();
                        }
                    }
                }
            }
        }
    }


    public Group generateBoard() {
        Group group = new Group();
        char[][] tempBoard = model.getBoard();
        Dice dice;
        diceSize = new Position2D<Double>()
                .setX(Integer.valueOf((1024-308) / model.getBoard().length).doubleValue())
                .setY(Integer.valueOf((768 - 60) / model.getBoard().length).doubleValue());

        int x, y;
        for (x = 0; x < tempBoard.length; x++) {
            for (y = 0; y < tempBoard.length; y++) {
                dice = new Dice()
                        .setPosition(new Position2D<Double>().setX(x * diceSize.getX()).setY(y * diceSize.getY()))
                        .setLetter(tempBoard[x][y])
                        .setColor(((x + y) % 2 == 0) ? Color.GOLDENROD : Color.ALICEBLUE)
                        .setSize(diceSize.getX(), diceSize.getY())
                        .setActive(false)
                        .setBoardPosition(new Position2D<Integer>().setX(x).setY(y));
                dice.render();
                group.getChildren().add(dice);
            }
        }
        return group;
    }
}
