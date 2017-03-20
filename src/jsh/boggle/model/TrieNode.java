package jsh.boggle.model;

/**
 * @author Joël Hoekstra
 */
public class TrieNode {
    char c;
    TrieNode[] child;
    boolean isWord;

    public TrieNode(char c) {
        this.c = c;
        child = new TrieNode[256];
        isWord = false;
    }

    public void insert(TrieNode troot, String s) {
        int i;
        if (troot == null) return;
        if (s.length() < 3) return; // boggle rule

        TrieNode root = troot;
        TrieNode[] children = root.child;
        for (i = 0; i < s.length(); i++) {
            if (children[s.charAt(i)] == null) {
                // child does not exist
                TrieNode node = new TrieNode(s.charAt(i));
                children[s.charAt(i)] = node;
                root = node;
                children = node.child;
            } else {
                root = children[s.charAt(i)];
                children = children[s.charAt(i)].child;
            }
        }
        root.isWord = true;
    }
}
