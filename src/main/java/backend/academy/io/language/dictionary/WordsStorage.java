package backend.academy.io.language.dictionary;

import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;

public class WordsStorage {
    protected static List<String> keys;
    protected static List<String> ruValues;

    static {
        keys = new ArrayList<>();
        ruValues = new ArrayList<>();
        addWord(Pair.of("Write a width of the maze", "Ширина лабиринта"));
        addWord(Pair.of("Write a height of the maze", "Высота лабиринта"));

    }


    private static void addWord(Pair<String, String> stringPair) {
        keys.add(stringPair.first());
        ruValues.add(stringPair.second());
    }
}
