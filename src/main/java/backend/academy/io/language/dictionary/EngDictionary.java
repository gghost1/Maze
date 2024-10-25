package backend.academy.io.language.dictionary;

import backend.academy.exception.NoSuchPhraseException;
import backend.academy.io.language.Dictionary;
import java.util.HashMap;
import java.util.List;

public class EngDictionary implements Dictionary {

    private final HashMap<String, String> dictionary;

    public EngDictionary(List<String> keys, List<String> values) {
        dictionary = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            dictionary.put(keys.get(i), values.get(i));
        }
    }

    @Override
    public String getString(String string) throws NoSuchPhraseException {
        String phrase = dictionary.get(string);
        if (phrase == null) {
            throw new NoSuchPhraseException("No such phrase: " + string);
        }
        return phrase;
    }
}
