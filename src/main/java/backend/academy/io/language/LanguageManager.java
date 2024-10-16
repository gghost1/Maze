package backend.academy.io.language;

import backend.academy.exception.NotInitializedException;
import backend.academy.io.language.dictionary.EngDictionary;
import backend.academy.io.language.dictionary.RuDictionary;
import backend.academy.io.language.dictionary.WordsStorage;
import lombok.Getter;

@Getter
public class LanguageManager extends WordsStorage {
    private static Dictionary instance;

    public static Dictionary getDictionary(Language language) {
        return switch (language) {
            case en -> {
                if (instance == null || instance instanceof RuDictionary) {
                    init(language);
                    instance = new EngDictionary(getKeys(), getKeys());
                }
                yield instance;
            }
            case ru -> {
                if (instance == null || instance instanceof EngDictionary) {
                    init(language);
                    instance = new RuDictionary(getKeys(), getValues());
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
