package backend.academy.algorithm.ioTest;

import backend.academy.exception.NoSuchPhraseException;
import backend.academy.exception.NotInitializedException;
import backend.academy.io.CustomInput;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import backend.academy.io.language.Dictionary;
import backend.academy.io.language.Language;
import backend.academy.io.language.LanguageManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomInputTest {

    @Test
    public void initializationTest() {
        assertDoesNotThrow(() -> CustomInput.getInstance(new InputStreamReader(System.in, StandardCharsets.UTF_8)));
    }

    @Test
    public void getCustomInputTest() {
        CustomInput.getInstance(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        assertDoesNotThrow(() -> CustomInput.getInstance());
    }

    @Test
    public void getCustomInputNegativeTest() {
        CustomInput.reset();
        assertThrows(NotInitializedException.class, CustomInput::getInstance);
    }

    @Test
    public void inputTest() {
        CustomInput.reset();
        CustomInput customInput = CustomInput.getInstance(new StringReader("test"));
        assertEquals("test", customInput.readInputLine());
    }

    @Test
    public void dictionaryExceptionTest() {
        Dictionary dictionary = LanguageManager.getDictionary(Language.en);
        assertThrows(NoSuchPhraseException.class, () -> dictionary.getString("test"));
    }
}
