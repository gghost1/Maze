package backend.academy.io.language.dictionary;

import backend.academy.io.language.Language;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WordsStorage {
    private final static List<String> KEYS = new ArrayList<>();
    private final static List<String> VALUES = new ArrayList<>();

    protected WordsStorage() {
    }

    protected static void init(Language language) {
        KEYS.clear();
        VALUES.clear();
        addRuWord(Pair.of("Input a width of the maze",
            "Введите ширину лабиринта"), language);
        addRuWord(Pair.of("Input a height of the maze",
            "Введите высоту лабиринта"), language);
        addRuWord(Pair.of("Input a start point <x y>",
            "Введите начальную позицию <x y>"), language);
        addRuWord(Pair.of("Input an end point <x y>",
            "Введите конечную позицию <x y>"), language);
        addRuWord(Pair.of("Counting of coordinates starts from 0",
            "Счет координат начинается с 0"), language);
        addRuWord(Pair.of("Incorrect input. Try again",
            "Неправильный ввод. Попробуйте ещё раз"), language);
        addRuWord(Pair.of("Choose an algorithm for creation of the maze",
            "Выберите алгоритм для создания лабиринта"), language);
        addRuWord(Pair.of(" was chosen",
            " был выбран"), language);
        addRuWord(Pair.of("For this generation algorithm, only ",
            "Для данного алгоритма только "), language);
        addRuWord(Pair.of(" is suitable",
            " подходит"), language);
        addRuWord(Pair.of("Choose an algorithm for solving the maze",
            "Выберите алгоритм для решения лабиринта"), language);
        addRuWord(Pair.of("Input number or the name of the algorithm",
            "Введите номер или имя алгоритма"), language);
        addRuWord(Pair.of("The wall is displayed as ",
            "Стена обозначена как "), language);
        addRuWord(Pair.of("The passage is displayed as ",
            "Проход обозначен как "), language);
        addRuWord(Pair.of("The ",
            ""), language);
        addRuWord(Pair.of(" is displayed as ",
            " обозначена как "), language);
        addRuWord(Pair.of(". It slows down the player by ",
            ". Это снижает скорость игрока на "), language);
        addRuWord(Pair.of(" hours",
            " часов"), language);
        addRuWord(Pair.of("desert",
            "Пустыня"), language);
        addRuWord(Pair.of("dirt",
            "Грязь"), language);
        addRuWord(Pair.of("trail",
            "Тропинка"), language);
        addRuWord(Pair.of("road",
            "Дорога"), language);
        addRuWord(Pair.of("There is no path from start to end in the maze",
            "В этом лабиринте нет пути от начала до конца"), language);
        addRuWord(Pair.of("The correct and fastest path from start to end is displayed as ",
            "Правильный и быстрый путь от начала до конца обозначен как "), language);
        addRuWord(Pair.of("Something went wrong",
            "Что-то пошло не так"), language);
        addRuWord(Pair.of("To generation the maze input 1",
            "Чтобы начать генерацию введите 1"), language);
        addRuWord(Pair.of("To solve the maze input 1",
            "Чтобы найти решение введите 1"), language);
        addRuWord(Pair.of("Hi! You are in the maze program. Let's make a settings",
            "Привет! Вы в программе лабиринта. Давайте сделаем настройки"), language);
        addRuWord(Pair.of("Make settings again",
            "Сделайте настройки ещё раз"), language);
    }

    protected static List<String> getKeys() {
        return Collections.unmodifiableList(KEYS);
    }

    protected static List<String> getValues() {
        return Collections.unmodifiableList(VALUES);
    }

    private static void addRuWord(Pair<String, String> stringPair, Language language) {
        KEYS.add(stringPair.first());
        if (Objects.requireNonNull(language) == Language.ru) {
            VALUES.add(stringPair.second());
        }
    }
}
