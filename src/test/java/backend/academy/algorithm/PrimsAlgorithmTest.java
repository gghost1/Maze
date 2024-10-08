package backend.academy.algorithm;

import backend.academy.game.maze.algorithm.findPath.DeadEndFiller;
import backend.academy.game.maze.algorithm.findPath.ShortestPathFinder;
import backend.academy.game.maze.algorithm.findPath.FindMazePath;
import backend.academy.game.maze.algorithm.generate.CreateMaze;
import backend.academy.game.maze.algorithm.generate.PrimsAlgorithm;
import backend.academy.game.maze.algorithm.generate.RecursiveBacktrackerAlgorithm;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellFlorType;
import backend.academy.game.maze.cell.CellType;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import it.unimi.dsi.fastutil.Pair;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PrimsAlgorithmTest {

    @Test
    public void PrimsAlgorithmTest() {
        CreateMaze primsAlgorithm = new PrimsAlgorithm();
        List<List<Cell>> maze = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            maze.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                if (i % 2 == 0) {
                    maze.get(i).add(new Wall(i, j));
                } else {
                    if (j % 2 == 0) {
                        maze.get(i).add(new Wall(i,j));
                    } else {
                        maze.get(i).add(new Path((j-1)/2, (i-1)/2, CellType.PATH, CellFlorType.GOOD));
                    }
                }
            }
        }
        List<List<Cell>> maze2 = primsAlgorithm.apply(maze, Pair.of(0,0), Pair.of(3,3));
// correct
        FindMazePath findMazePath = new DeadEndFiller();

        findMazePath.apply(maze2, Pair.of(0,0), Pair.of(3,3));


    }

}
