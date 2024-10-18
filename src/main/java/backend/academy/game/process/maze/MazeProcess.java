package backend.academy.game.process.maze;

import backend.academy.game.maze.Maze;
import backend.academy.game.maze.algorithm.Point;

public record MazeProcess(Maze maze, MazeSettings settings) {

    public record MazeSettings(Point start, Point end) {
    }
}
