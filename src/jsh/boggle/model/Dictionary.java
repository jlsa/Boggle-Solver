package jsh.boggle.model;

import jsh.boggle.util.Config;

/**
 * @author Joël Hoekstra
 */
public class Dictionary {
    private TrieNode words;

    public Dictionary() {
        String[] dictionary = DictionaryReader.read(Config.DICTIONARY_FILENAME);

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
