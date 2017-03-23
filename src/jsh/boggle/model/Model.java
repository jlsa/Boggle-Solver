package jsh.boggle.model;

import java.util.*;

import jsh.boggle.view.Position2D;
import jsh.boggle.view.View;

/**
 * @author Joël Hoekstra
 */
public class Model {
    private int gridSize;
    private int N;
    private Board board;
    private TreeSet<String> words = new TreeSet<>();
    private Dictionary dictionary;
    private HashMap<String, ArrayList<Position2D<Integer>>> positionsList;

    public Model(View view) {
        view.setModel(this);
        positionsList = new HashMap<>();
        dictionary = new Dictionary();
        board = new Board(4);
        gridSize = board.getGridSize();
        N = 0;
        words.clear();
        board.generate(gridSize);
    }

    public void printBoard() {
        System.out.println();
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
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
        board.generate(gridSize);

        printBoard();
        findWords();
    }

    public TreeSet<String> getAllWordsInBoard() {
        return words;
    }

    private void findWords() {
        long startTimeNano = System.nanoTime();
        long startTime = System.currentTimeMillis();
        boolean[][] visited  = new boolean[gridSize][gridSize];
        int x, y;
        for(x = 0; x < gridSize; x++){
            for(y = 0; y < gridSize; y++)
                visited[x][y] = false;
        }
        TrieNode[] child  = dictionary.getWords().child;
        for(x = 0; x < gridSize; x++){
            for(y = 0; y < gridSize; y++){
                char letter = board.getBoard()[x][y];
                String str = "";
                if(child[letter] != null){
//                    findWordsUtil(child[ch], str + ch, visited, i, j);
                    findWordsUtil(child[letter], str + letter, visited, new Position2D<Integer>(x, y));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long endTimeNano = System.nanoTime();

        long elapsedTimeNano = endTimeNano - startTimeNano;
        System.out.println("It took " + (endTime - startTime) + " ms to find all the words.");
        System.out.println("It took " + (elapsedTimeNano) + " ns to find all the words.");
        System.out.println("There are " + positionsList.size() + " words found");
    }


    private void findWordsUtil(TrieNode child, String str, boolean[][] visited, Position2D<Integer> position) {
        findWordsUtil(child, str, visited, position.getX(), position.getY());
    }

    private void findWordsUtil(TrieNode child, String str, boolean[][] visited, int i, int j) {
        TrieNode[] node = child.child;
        visited[i][j] = true;

        if(child.isWord){
            if (str.length() >= 3) {
                words.add(str);
                N++;
                ArrayList<Position2D<Integer>> positions = new ArrayList<>();
                for (int xx = 0; xx < gridSize; xx++) {
                    for (int yy = 0; yy < gridSize; yy++) {
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
                    findWordsUtil(node[board.getBoard()[x][y]], str + board.getBoard()[x][y], visited, x, y);
                }
            }
        }
        visited[i][j] = false;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0 && x < gridSize && y < gridSize);
    }

}
