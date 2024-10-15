package backend.academy.io.language;

import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import java.util.HashMap;
import java.util.List;

public interface Dictionary {
    String getString(String string);
    default List<String> getCreateAlgorithms() {
        return List.of(
            "0. Random algorithm",
            "1. " + CreateMazeAlgorithm.values()[0].toString(),
            "2. " + CreateMazeAlgorithm.values()[1].toString()
        );
    }
    default List<String> getSolveAlgorithms() {
        return List.of(
            "0. Random algorithm",
            "1. " + FindMazePathAlgorithm.values()[0].toString(),
            "2. " + FindMazePathAlgorithm.values()[1].toString()
        );
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
