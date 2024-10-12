package backend.academy.game.process;

import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import lombok.Getter;
import lombok.Setter;
import java.util.Optional;

@Getter
public class SettingsProcess {

    @Setter private int mazeWidth;
    @Setter private int mazeHeight;
    @Setter private Point start;
    @Setter private Point end;
    private CreateMazeAlgorithm createMazeAlgorithm;
    private FindMazePathAlgorithm findMazePathAlgorithm;

    public void setCreateMazeAlgorithm(Optional<CreateMazeAlgorithm> createMazeAlgorithm) {
        this.createMazeAlgorithm = createMazeAlgorithm.get();
    }

    public void setFindMazePathAlgorithm(Optional<FindMazePathAlgorithm> findMazePathAlgorithm) {
        this.findMazePathAlgorithm = findMazePathAlgorithm.get();
    }

}
