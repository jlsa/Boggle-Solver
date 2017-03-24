package jsh.boggle.model;

import java.util.*;

import jsh.boggle.util.Config;
import jsh.boggle.util.Position2D;
import jsh.boggle.view.View;

/**
 * @author Joël Hoekstra
 */
public class Model {
    private int N;
    private Board board;
    private TreeSet<String> words = new TreeSet<>();
    private Dictionary dictionary;
    private HashMap<String, ArrayList<Position2D<Integer>>> positionsList;

    public Model(View view) {
        view.setModel(this);
        positionsList = new HashMap<>();
        dictionary = new Dictionary();
        board = new Board();
        N = 0;
        words.clear();
        board.generate();
    }

    public void printBoard() {
        System.out.println();
        for (int x = 0; x < Config.GRID_SIZE; x++) {
            for (int y = 0; y < Config.GRID_SIZE; y++) {
                System.out.print(" " + board.getBoard()[x][y]);
            }
            System.out.println();
        }
    }

    public char[][] getBoard() {
        return board.getBoard();
    }

    public ArrayList<Position2D<Integer>> getPositionsFromWord(String word) {
        if (positionsList.containsKey(word)) {
            return positionsList.get(word);
        }
        return null;
    }

    public void reset() {
        N = 0;
        words.clear();
        board.generate();

        printBoard();
        findWords();
    }

    public TreeSet<String> getAllWordsInBoard() {
        return words;
    }

    private void findWords() {
        long startTime = System.currentTimeMillis();
        boolean[][] visited  = new boolean[Config.GRID_SIZE][Config.GRID_SIZE];
        int x, y;
        for(x = 0; x < Config.GRID_SIZE; x++){
            for(y = 0; y < Config.GRID_SIZE; y++)
                visited[x][y] = false;
        }
        TrieNode[] child  = dictionary.getWords().child;
        for(x = 0; x < Config.GRID_SIZE; x++){
            for(y = 0; y < Config.GRID_SIZE; y++){
                char letter = board.getBoard()[x][y];
                String str = "";
                if(child[letter] != null){
                    findWordsUtil(child[letter], str + letter, visited, new Position2D<Integer>(x, y));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " ms to find all the words.");
        System.out.println("There are " + positionsList.size() + " words found");
    }


    private void findWordsUtil(TrieNode child, String str, boolean[][] visited, Position2D<Integer> position) {
        TrieNode[] node = child.child;
        int i = position.getX();
        int j = position.getY();
        visited[i][j] = true;

        if(child.isWord){
            if (str.length() >= 3) {
                words.add(str);
                N++;
                ArrayList<Position2D<Integer>> positions = new ArrayList<>();
                for (int xx = 0; xx < Config.GRID_SIZE; xx++) {
                    for (int yy = 0; yy < Config.GRID_SIZE; yy++) {
                        if (visited[xx][yy]) {
                            positions.add(new Position2D<>(xx, yy));
                        }
                    }
                }
                positionsList.put(str, positions);
            }
        }

        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
        for(int k = 0; k < 8; k++){
            int x  = i + dx[k];
            int y = j + dy[k];
            if (isValid(x, y) && !visited[x][y]){
                if(node[board.getBoard()[x][y]] != null){
                    findWordsUtil(
                            node[board.getBoard()[x][y]],
                            str + board.getBoard()[x][y],
                            visited,
                            new Position2D<Integer>(x, y)
                    );
                }
            }
        }
        visited[i][j] = false;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0 && x < Config.GRID_SIZE && y < Config.GRID_SIZE);
    }

}
