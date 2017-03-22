package jsh.boggle.model;

/**
 * @author Joël Hoekstra
 */
public class Dictionary {
    private String filename = "nl-dict.txt";

    private TrieNode words;

    public Dictionary() {
        String[] dictionary = DictionaryReader.read(filename);

        char c = '\0'; // root value
        words = new TrieNode(c);
        int i;
        for (i = 0; i < dictionary.length; i++) {
            words.insert(words, dictionary[i]);
        }
    }

    public TrieNode getWords() {
        return words;
    }
}
