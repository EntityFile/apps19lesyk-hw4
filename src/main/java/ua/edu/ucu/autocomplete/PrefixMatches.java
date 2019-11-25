package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

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
                if (!(trie.contains(element)) && element.length() > 2) {
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
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
