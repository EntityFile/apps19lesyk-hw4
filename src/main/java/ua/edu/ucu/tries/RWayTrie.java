package ua.edu.ucu.tries;

public class RWayTrie implements Trie {
    private static final int SIZE = 26;

    private class Node {
        Node[] next = new Node[SIZE];
        boolean wordEnd;

        Node() {
            wordEnd = false;
            for (int i = 0; i < SIZE; i++)
                next[i] = null;
        }
    };

    private Node root;
    private int size;

    public RWayTrie() {
        root = new Node();
    }

    private Node insert(Node node, String key, int ind) {
        if (node == null) {
            node = new Node();
        }

        if (ind == key.length()) {
            node.wordEnd = true;
            return node;
        }

        int ch = key.charAt(ind);
        ch = ch - 97;
        node.next[ch] = insert(node.next[ch], key, ind + 1);
        return node;
    }

    @Override
    public void add(Tuple t) {
        root = insert(root, t.term, 0);
        size += 1;
    }

    private Node get(String word) {
        int ch = word.charAt(0);
        ch -= 97;
        Node node = root.next[ch];
        for (int ind = 1; ind < word.length(); ind++) {
            if (node == null) {
                return null;
            }
            ch = word.charAt(ind);
            node = node.next[ch];
        }
        return node;
    }

    @Override
    public boolean contains(String word) {
        return get(word) != null;
    }

    @Override
    public boolean delete(String word) {
        Node node = get(word);
        if (node != null && node.wordEnd) {
            node.wordEnd = false;
            size -= 1;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {

    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        return size;
    }
}
