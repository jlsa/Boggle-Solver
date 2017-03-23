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
    private HashMap<String, ArrayList<Position2D>> positionsList;

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

    public ArrayList<Position2D> getPositionsFromWord(String word) {
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
        int i, j;
        for(i = 0; i < gridSize; i++){
            for(j = 0; j < gridSize; j++)
                visited[i][j] = false;
        }
        TrieNode[] child  = dictionary.getWords().child;
        for(i = 0; i < gridSize; i++){
            for(j = 0; j < gridSize; j++){
                char ch = board.getBoard()[i][j];
                String str = "";
                if(child[ch] != null){
                    findWordsUtil(child[ch], str + ch, visited, i, j);
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

    private void findWordsUtil(TrieNode child, String str, boolean[][] visited, int i, int j) {
        TrieNode[] node = child.child;
        visited[i][j] = true;

        if(child.isWord){
            if (str.length() >= 3) {
                words.add(str);
                N++;
                ArrayList<Position2D> positions = new ArrayList<>();
                for (int xx = 0; xx < gridSize; xx++) {
                    for (int yy = 0; yy < gridSize; yy++) {
                        if (visited[xx][yy]) {
                            positions.add(new Position2D(xx, yy));
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
