package backend.academy.io.language;

import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public interface Dictionary {
    String getString(String string);

    default List<String> getCreateAlgorithms() {
        List<String> algorithms = new ArrayList<>(List.of(
            "0. Random algorithm"
        ));
        for (int i = 0; i < CreateMazeAlgorithm.values().length; i++) {
            algorithms.add((i + 1) + ". " + CreateMazeAlgorithm.values()[i].toString());
        }
        return algorithms;
    }

    default List<String> getSolveAlgorithms() {
        List<String> algorithms = new ArrayList<>(List.of(
            "0. Random algorithm"
        ));
        for (int i = 0; i < FindMazePathAlgorithm.values().length; i++) {
            algorithms.add((i + 1) + ". " + FindMazePathAlgorithm.values()[i].toString());
        }
        return algorithms;
    }

    default String exceptionSomethingWentWrong() {
        return getString("Something went wrong");
    }

    default String exceptionIncorrectInput() {
        return getString("Incorrect input. Try again");
    }

    default String getInputNumberOrNameOfAlgorithm() {
        return getString("Input number or the name of the algorithm");
    }

    default String getCountingStartsFrom() {
        return getString("Counting of coordinates starts from 0");
    }

    default String getWall() {
        return "\uD83D\uDFE9";
    }

    default String getPath() {
        return "⬜\uFE0F";
    }

    default String getCorrectPath() {
        return "\uD83D\uDFE8";
    }

}
