package jsh.boggle.model;

import java.util.Random;
import java.util.ArrayList;
import jsh.boggle.util.Stack;
import java.util.Collections;
import jsh.boggle.util.Config;

/**
 * @author Joël Hoekstra
 */
public class Board {
    private char[][] board;

    private Random rand = new Random();
    private Stack<Integer> allDice = new Stack<>();

    public Board() { }

    public char[][] getBoard() {
        return board;
    }

    public Board generate() {
        int x, y;
        board = new char[Config.GRID_SIZE][Config.GRID_SIZE];
        shuffleDice();
        for (x = 0; x < Config.GRID_SIZE; x++) {
            for (y = 0; y < Config.GRID_SIZE; y++) {
                board[x][y] = generateLetter();
            }
        }
        return this;
    }

    public char generateLetter() {
        int dice;
        if (Config.GRID_SIZE > 4) {
            dice = rand.nextInt(Config.DIE_FACES.length);
            return Config.DIE_FACES[dice][rand.nextInt(Config.DIE_FACES[dice].length)];
        }
        dice = allDice.pop();
        return Config.DIE_FACES[dice][rand.nextInt(Config.DIE_FACES[dice].length)];
    }

    private void shuffleDice() {
        allDice = new Stack<>();
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < Config.GRID_SIZE * Config.GRID_SIZE; i++) {
            dice.add(i);
        }
        Collections.shuffle(dice);
        for (Integer die : dice) allDice.push(die);
    }
}
