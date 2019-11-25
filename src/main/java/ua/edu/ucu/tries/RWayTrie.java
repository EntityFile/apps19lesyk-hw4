package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

import java.util.Arrays;
import java.util.Comparator;

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
            node = node.next[ch - 97];
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
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node node = get(s);
        Queue queue = new Queue();
        collect(node, s, queue);

        if (!contains(s)){
            queue.dequeue();
        }

        Object[] queueArray = queue.toArray();

        //String[] array = (String[]) Arrays.stream(queueArray).map(x -> (String) x).toArray();
        String[] toReturn = Arrays.copyOf(queueArray, queueArray.length, String[].class);
        Arrays.sort(toReturn, Comparator.comparingInt(String::length));

        return Arrays.asList(toReturn);
    }

    private void collect(Node node, String s, Queue queue) {
        if (node == null){
            return;
        }
        if (node.wordEnd) {
            queue.enqueue(s);
        }
        for (char ch = 97; ch < SIZE + 97; ch++) {
            collect(node.next[ch - 97], s + ch, queue);
        }
    }


    @Override
    public int size() {
        return size;
    }
}
