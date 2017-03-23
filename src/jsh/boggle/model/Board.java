package jsh.boggle.model;

import java.util.Random;
import java.util.ArrayList;
import jsh.boggle.util.Stack;
import java.util.Collections;

/**
 * @author Joël Hoekstra
 */
public class Board {
    private char[][] board;
    private int gridSize;
    private final char[][] DIE_FACES = {
            {'a', 'a', 'e', 'e', 'g', 'n'}, // [A A E E G N]
            {'a', 'b', 'b', 'j', 'o', 'o'}, // [A B B J O O]
            {'a', 'c', 'h', 'o', 'p', 's'}, // [A C H O P S]
            {'a', 'f', 'f', 'k', 'p', 's'}, // [A F F K P S]

            {'a', 'o', 'o', 't', 't', 'w'}, // [A O O T T W]
            {'c', 'i', 'm', 'o', 't', 'u'}, // [C I M O T U]
            {'d', 'e', 'i', 'l', 'r', 'x'}, // [D E I L R X]
            {'d', 'e', 'l', 'r', 'v', 'y'}, // [D E L R V Y]

            {'d', 'i', 's', 't', 't', 'y'}, // [D I S T T Y]
            {'e', 'e', 'g', 'h', 'n', 'w'}, // [E E G H N W]
            {'e', 'e', 'i', 'n', 's', 'u'}, // [E E I N S U]
            {'e', 'h', 'r', 't', 'v', 'w'}, // [E H R T V W]

            {'e', 'i', 'o', 's', 's', 't'}, // [E I O S S T]
            {'e', 'l', 'r', 't', 't', 'y'}, // [E L R T T Y]
            {'h', 'i', 'm', 'n', 'u', 'q'}, // [H I M N U Q]
            {'h', 'l', 'n', 'n', 'r', 'z'}  // [H L N N R Z]
    };

    private Random rand = new Random();
    private Stack<Integer> allDice = new Stack<>();

    public Board(int gridSize) {
        this.gridSize = gridSize;
    }

    public char[][] getBoard() {
        return board;
    }

    public Board generate(int gridSize) {
        int x, y;
        this.gridSize = gridSize;
        board = new char[gridSize][gridSize];
        shuffleDice();
        for (x = 0; x < gridSize; x++) {
            for (y = 0; y < gridSize; y++) {
                board[x][y] = generateLetter();
            }
        }
        return this;
    }

    public void generatetestBoardSmall() {
        gridSize = 2;
        char[][] testBoard = {
                {'k', 'a'},
                {'r', 't'}
        };
        board = testBoard;
    }


    public void generateTestBoard() {
        gridSize = 4;
        char[][] testBoggleBoard = {
                {'d', 'g', 'h', 'i'},
                {'k', 'l', 'p', 's'},
                {'y', 'e', 'u', 't'},
                {'e', 'o', 'r', 'n'}
        };
        board = testBoggleBoard;
    }

    public int getGridSize() {
        return gridSize;
    }

    public char generateLetter() {
        if (gridSize > 4) {
            int die = rand.nextInt(DIE_FACES.length);
            return DIE_FACES[die][rand.nextInt(DIE_FACES[die].length)];
        }
        int dice = allDice.pop();
        return DIE_FACES[dice][rand.nextInt(DIE_FACES[dice].length)];
    }

    private void shuffleDice() {
        allDice = new Stack<>();
        ArrayList<Integer> dice = new ArrayList<>();
        for (int i = 0; i < gridSize * gridSize; i++) {
            dice.add(i);
        }
        Collections.shuffle(dice);
        for (Integer die : dice) allDice.push(die);
    }
}
