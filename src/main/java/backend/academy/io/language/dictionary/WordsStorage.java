package backend.academy.io.language.dictionary;

import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;

public class WordsStorage {
    protected static List<String> keys;
    protected static List<String> ruValues;

    protected WordsStorage() {
    }

    static {
        keys = new ArrayList<>();
        ruValues = new ArrayList<>();
        addWord(Pair.of("Input a width of the maze",
            "Введите ширину лабиринта"));
        addWord(Pair.of("Input a height of the maze",
            "Введите высоту лабиринта"));
        addWord(Pair.of("Input a start point <x y>",
            "Введите начальную позицию <x y>"));
        addWord(Pair.of("Input an end point <x y>",
            "Введите конечную позицию <x y>"));
        addWord(Pair.of("Counting of coordinates starts from 0",
            "Счет координат начинается с 0"));
        addWord(Pair.of("Incorrect input. Try again",
            "Неправильный ввод. Попробуйте ещё раз"));
        addWord(Pair.of("Choose an algorithm for creation of the maze",
            "Выберите алгоритм для создания лабиринта"));
        addWord(Pair.of("Choose an algorithm for solving the maze",
            "Выберите алгоритм для решения лабиринта"));
        addWord(Pair.of("Input number or the name of the algorithm",
            "Введите номер или имя алгоритма"));
        addWord(Pair.of("The wall is displayed as ",
            "Стена обозначена как "));
        addWord(Pair.of("The path is displayed as ",
            "Путь обозначен как "));
        addWord(Pair.of("There is no path from start to end in the maze",
            "В этом лабиринте нет пути от начала до конца"));
        addWord(Pair.of("The correct path from start to end is displayed as ",
            "Правильный путь от начала до конца обозначен как "));
        addWord(Pair.of("Something went wrong",
            "Что-то пошло не так"));
        addWord(Pair.of("To generation the maze input 1",
            "Чтобы начать генерацию введите 1"));
        addWord(Pair.of("To solve the maze input 1",
            "Чтобы найти решение введите 1"));
        addWord(Pair.of("Hi! You are in the maze program. Let's make a settings",
            "Привет! Вы в программе лабиринта. Давайте сделаем настройки"));
        addWord(Pair.of("Make settings again",
            "Сделайте настройки ещё раз"));
    }


    private static void addWord(Pair<String, String> stringPair) {
        keys.add(stringPair.first());
        ruValues.add(stringPair.second());
    }
}
