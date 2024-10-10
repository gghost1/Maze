package backend.academy;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.Maze;
import backend.academy.game.maze.algorithm.findPath.DeadEndFiller;
import backend.academy.game.maze.algorithm.findPath.FindMazePath;
import backend.academy.game.maze.algorithm.findPath.ShortestPathFinder;
import backend.academy.game.maze.algorithm.generate.CreateMaze;
import backend.academy.game.maze.algorithm.generate.PrimsAlgorithm;
import backend.academy.game.maze.algorithm.generate.RecursiveBacktrackerAlgorithm;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import it.unimi.dsi.fastutil.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    @Test
    public void mazeCreationTest() {
        Maze maze = new Maze(3, 3, new PrimsAlgorithm(), new DeadEndFiller());

        assertEquals(3*2+3, maze.maze().size());
        assertEquals(3*2+3, maze.maze().getFirst().size());
        assertInstanceOf(Wall.class, maze.maze().getFirst().getFirst());
        assertInstanceOf(Path.class, maze.maze().get(1).get(1));
    }

    private static Stream<CreateMaze> provideCreateMazeAlgorithms() {
        return Stream.of(new PrimsAlgorithm(), new RecursiveBacktrackerAlgorithm());
    }

    @ParameterizedTest
    @MethodSource("provideCreateMazeAlgorithms")
    public void generateMazeTest(CreateMaze createMaze) {
        Maze maze = new Maze(3, 3, createMaze, new DeadEndFiller());
        maze.generateMaze(Pair.of(0,0), Pair.of(3,3));

        assertEquals(3*2+3, maze.maze().size());
        assertEquals(3*2+3, maze.maze().getFirst().size());
        assertInstanceOf(Wall.class, maze.maze().getFirst().getFirst());
        assertInstanceOf(Path.class, maze.maze().get(1).get(1));
        assertTrue(maze.maze().get(1).get(2) == null || maze.maze().get(2).get(1) == null);
        assertTrue(maze.maze().get(3*3-2).get(3*3-3) == null || maze.maze().get(3*3-3).get(3*3-2) == null);
    }

    private static Stream<FindMazePath> provideFindMazePathAlgorithms() {
        return Stream.of(new DeadEndFiller(), new ShortestPathFinder());
    }

    @ParameterizedTest
    @MethodSource("provideFindMazePathAlgorithms")
    public void solveMazeTest(FindMazePath findMazePath) throws PathNotFoundException {
        Maze maze = new Maze(3, 3, new PrimsAlgorithm(), findMazePath);
        maze.generateMaze(Pair.of(0, 0), Pair.of(3, 3));
        List<Pair<Integer, Integer>> path = maze.solveMaze(Pair.of(0, 0), Pair.of(3, 3));

        assertTrue(path.contains(Pair.of(0, 0)) && path.contains(Pair.of(3, 3)));
    }

}
