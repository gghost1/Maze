package backend.academy.io.language;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.language.dictionary.EngDictionary;
import backend.academy.io.language.dictionary.RuDictionary;
import backend.academy.io.language.dictionary.WordsStorage;

public class LanguageManager extends WordsStorage {
    public static Dictionary instance;

    public static Dictionary getDictionary(String language) {
        return switch (language) {
            case "en" -> {
                if (instance == null || instance instanceof RuDictionary) {
                    instance = new EngDictionary(keys, keys);
                }
                yield instance;
            }
            case "ru" -> {
                if (instance == null || instance instanceof EngDictionary) {
                    instance = new RuDictionary(keys, ruValues);
                }
                yield instance;
            }
            default -> null;
        };
    }

    public static Dictionary dictionary() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException("Dictionary not initialized");
        }
        return instance;
    }
}
