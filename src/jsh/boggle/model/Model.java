package jsh.boggle.model;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Observable;
import jsh.boggle.util.Stack;
import java.io.BufferedReader;
import java.nio.charset.Charset;

/**
 * @author Joël Hoekstra
 */
public class Model extends Observable {
    private int gridSize = 4;
    private final char[][] DIE_FACES = {
            {'a', 'a', 'e', 'e', 'g', 'n'}, // AAEEGN
            {'a', 'b', 'b', 'j', 'o', 'o'}, // ABBJOO
            {'a', 'c', 'h', 'o', 'p', 's'}, // ACHOPS
            {'a', 'f', 'f', 'k', 'p', 's'}, // AFFKPS

            {'a', 'o', 'o', 't', 't', 'w'}, // AOOTTW
            {'c', 'i', 'm', 'o', 't', 'u'}, // CIMOTU
            {'d', 'e', 'i', 'l', 'r', 'x'}, // DEILRX
            {'d', 'e', 'l', 'r', 'v', 'y'}, // DELRVY

            {'d', 'i', 's', 't', 't', 'y'}, // DISTTY
            {'e', 'e', 'g', 'h', 'n', 'w'}, // EEGHNW
            {'e', 'e', 'i', 'n', 's', 'u'}, // EEINSU
            {'e', 'h', 'r', 't', 'v', 'w'}, // EHRTVW

            {'e', 'i', 'o', 's', 's', 't'}, // EIOSST
            {'e', 'l', 'r', 't', 't', 'y'}, // ELRTTY
            {'h', 'i', 'm', 'n', 'u', 'q'}, // HIMNUQu
            {'h', 'l', 'n', 'n', 'r', 'z'}  // HLNNRZ
    };

    private Random rand = new Random();
    private int N;
    private TrieNode root;
    private char[][] board;
    private String dictionaryFileName = "nl-dict.txt";
    private Stack<Integer> allDice = new Stack<>();
    private TreeSet<String> words = new TreeSet<>();

    public Model() {
        // @todo performance increase would happen if it instantly adds it to the root
        String[] dictionary = readWordsFromFile(dictionaryFileName);

        char c = '\0'; // root value
        root = new TrieNode(c);
        int i;
        for (i = 0; i < dictionary.length; i++) {
            root.insert(root, dictionary[i]);
        }
    }

    public void printBoard() {
        System.out.println();
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                System.out.print(" " + board[x][y]);
            }
            System.out.println();
        }
    }

    public void createBoard() {
        int x, y;
        board = new char[gridSize][gridSize];

        for (x = 0; x < gridSize; x++) {
            for (y = 0; y < gridSize; y++) {
                board[x][y] = generateLetter();
            }
        }

        printBoard();
    }

    public void createTestBoard() {
        gridSize = 4;
        char[][] testBoggleBoard = {
                {'d', 'g', 'h', 'i'},
                {'k', 'l', 'p', 's'},
                {'y', 'e', 'u', 't'},
                {'e', 'o', 'r', 'n'}
        };
        board = testBoggleBoard;

        printBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public void reset() {
        N = 0;
        words.clear();
        shuffleDice();
        createBoard();
//        createTestBoard();
        findWords();
//        dataChanged();
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

    public char generateLetter() {
        if (gridSize > 4) {
            int die = rand.nextInt(DIE_FACES.length);
            return DIE_FACES[die][rand.nextInt(DIE_FACES[die].length)];
        }
        int dice = allDice.pop();
        return DIE_FACES[dice][rand.nextInt(DIE_FACES[dice].length)];
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
        TrieNode[] child  = root.child;
        for(i = 0; i < gridSize; i++){
            for(j = 0; j < gridSize; j++){
                char ch = board[i][j];
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
        System.out.println("There are " + N + " words found");
    }

    private void findWordsUtil(TrieNode child, String str, boolean[][] visited, int i, int j) {
        if(child.isWord){
            if (str.length() >= 3) {
                words.add(str);
                N++;
            }
        }
        TrieNode[] node = child.child;
        visited[i][j] = true;
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
        for(int k = 0; k < 8; k++){
            int x  = i + dx[k];
            int y = j + dy[k];
            if (isValid(x, y) && !visited[x][y]){
                if(node[board[x][y]] != null){
                    findWordsUtil(node[board[x][y]], str + board[x][y], visited, x, y);
                }
            }
        }
        visited[i][j] = false;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && y >= 0 && x < gridSize && y < gridSize);
    }

    private String[] readWordsFromFile(String filename) {
        ArrayList<String> words = new ArrayList<>();

        long startTimeNano = System.nanoTime();
        long startTime = System.currentTimeMillis();
        Charset charset = Charset.forName("ISO-8859-1"); // The file is not "UTF-8" @todo check the file type
        //ISO-8859-1

        int n = 0;
        Path path = Paths.get(Model.class.getResource(filename).getPath());
        try {
            String line;
            try (BufferedReader reader = Files.newBufferedReader(path,charset)) {
                while ((line = reader.readLine()) != null) {
                    words.add(line.toLowerCase());
                    n++;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        long endTime = System.currentTimeMillis();
        long endTimeNano = System.nanoTime();

        System.out.println("Reading word file and adding it to the dictionary took " + (endTime - startTime) + " ms");
        System.out.println("Reading word file and adding it to the dictionary took " + (endTimeNano - startTimeNano) + " nano seconds");
        System.out.println("There are " + n + " words added to the dictionary.");
        return words.toArray(new String[words.size()]);
    }


}
