package backend.academy.game.process;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import lombok.Getter;
import java.util.Optional;

@Getter
public class Settings {

    private int mazeWidth;
    private int mazeHeight;
    private Point start;
    private Point end;
    private Optional<CreateMazeAlgorithm> createMazeAlgorithm;
    private Optional<FindMazePathAlgorithm> findMazePathAlgorithm;

    public void setMazeWidth(int mazeWidth) {
        this.mazeWidth = mazeWidth;
    }

    public void setMazeHeight(int mazeHeight) {
        this.mazeHeight = mazeHeight;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public void setCreateMazeAlgorithm(Optional<CreateMazeAlgorithm> createMazeAlgorithm) {
        this.createMazeAlgorithm = createMazeAlgorithm;
    }

    public void setFindMazePathAlgorithm(Optional<FindMazePathAlgorithm> findMazePathAlgorithm) {
        this.findMazePathAlgorithm = findMazePathAlgorithm;
    }

}
