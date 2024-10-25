package backend.academy.game.process;

import backend.academy.exception.IllegalSettingParameterException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingsProcess {
    private int mazeWidth;
    private int mazeHeight;
    private Point start;
    private Point end;
    private CreateMazeAlgorithm createMazeAlgorithm;
    private FindMazePathAlgorithm findMazePathAlgorithm;

    public boolean isValid() {
        if (mazeWidth <= 0 || mazeHeight <= 0) {
            throw new IllegalSettingParameterException("Maze size must be greater than zero");
        } else if (start.x() < 0 || start.y() < 0 || end.x() < 0 || end.y() < 0) {
            throw new IllegalSettingParameterException("Start and end must be greater than zero");
        } else if (start.x() >= mazeWidth || start.y() >= mazeHeight || end.x() >= mazeWidth || end.y() >= mazeHeight) {
            throw new IllegalSettingParameterException("Start and end must be less than maze size");
        }
        return true;
    }

}
