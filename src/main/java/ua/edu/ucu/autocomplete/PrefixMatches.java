package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int words_number = 0;
        for (String word : strings) {
            String[] words = word.trim().split("\\s+");
            for (String element : words) {
                if (!(trie.contains(element)) && element.length() >= 2) {
                    trie.add(new Tuple(element, element.length()));
                    words_number += 1;
                }
            }
        }
        return words_number;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        }
        else {
            return null;
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() >= 2) {
            ArrayList<String> wordsList = new ArrayList<>();
            int counter = 1;
            int wordLength = 3;

            for (String word : wordsWithPrefix(pref)) {
                if (word.length() >= 3) {
                    System.out.println(word);
                    if (word.length() > wordLength) {
                        wordLength += 1;
                        counter += 1;
                    }
                    if (counter > k) {
                        System.out.println(word);
                        break;
                    }
                    wordsList.add(word);
                }
            }

            return wordsList;
        } else {
            return null;
        }
    }

    public int size() {
        return trie.size();
    }
}
