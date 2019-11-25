package ua.edu.ucu.iterator;


import java.util.Iterator;

public class WordsIterator implements Iterator<String> {

    private int val1 = 0;
    private int val2 = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        int x = val2;
        val2 = val1 + val2;
        val1 = x;
        return val1;
    }

    public static Iterable<String> words() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new WordsIterator();
            }
        };
    }
}
