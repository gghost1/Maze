package backend.academy.algorithm;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.Maze;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.AStar;
import backend.academy.game.maze.algorithm.findPath.DeadEndFiller;
import backend.academy.game.maze.algorithm.findPath.FindMazePath;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.findPath.ShortestPathFinder;
import backend.academy.game.maze.algorithm.generate.CreateMaze;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.game.maze.algorithm.generate.PrimsAlgorithm;
import backend.academy.game.maze.algorithm.generate.RecursiveBacktrackerAlgorithm;
import backend.academy.game.maze.algorithm.generate.WavePropagationAlgorithm;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    @Test
    public void mazeCreationTest() {
        Maze maze = new Maze(3, 3, new PrimsAlgorithm(), new DeadEndFiller());

        assertEquals(3*2+1, maze.maze().size());
        assertEquals(3*2+1, maze.maze().getFirst().size());
        assertInstanceOf(Wall.class, maze.maze().getFirst().getFirst());
        assertInstanceOf(Path.class, maze.maze().get(1).get(1));
    }

    @ParameterizedTest
    @MethodSource("provideCreateMazeAlgorithms")
    public void generateMazeTest(CreateMaze createMaze) {
        Maze maze = new Maze(3, 3, createMaze, new DeadEndFiller());
        maze.generateMaze(new Point(0, 0), new Point(2, 2));

        assertEquals(3*2+1, maze.maze().size());
        assertEquals(3*2+1, maze.maze().getFirst().size());
        assertInstanceOf(Wall.class, maze.maze().getFirst().getFirst());
        assertInstanceOf(Path.class, maze.maze().get(1).get(1));
        assertTrue(maze.maze().get(1).get(2) == null || maze.maze().get(2).get(1) == null);
        assertTrue(maze.maze().get(3*2-1).get(3*2-2) == null || maze.maze().get(3*2-2).get(3*2-1) == null);
    }

    @ParameterizedTest
    @MethodSource("provideCreateMazeAlgorithms")
    public void enumCreateMazeTest(CreateMaze createMaze) {
        assertEquals(CreateMazeAlgorithm.valueOf(createMaze.getClass()).getAlgorithm(), createMaze.getClass());
    }

    @ParameterizedTest
    @MethodSource("provideFindMazePathAlgorithms")
    public void solveMazeTest(FindMazePath findMazePath) throws PathNotFoundException {
        Maze maze = new Maze(3, 3, new PrimsAlgorithm(), findMazePath);
        maze.generateMaze(new Point(0, 0), new Point(2, 2));
        List<Point> path = maze.solveMaze(new Point(0, 0), new Point(2, 2));
        assertTrue(checkValidPath(maze.maze(), path));
    }

    @ParameterizedTest
    @MethodSource("provideFindMazePathAlgorithms")
    public void enumFindMazePathTest(FindMazePath findMazePath) {
        assertEquals(FindMazePathAlgorithm.valueOf(findMazePath.getClass()).getAlgorithm(), findMazePath.getClass());
    }

    private static Stream<CreateMaze> provideCreateMazeAlgorithms() {
        return Stream.of(new PrimsAlgorithm(), new RecursiveBacktrackerAlgorithm(), new WavePropagationAlgorithm());
    }

    private static Stream<FindMazePath> provideFindMazePathAlgorithms() {
        return Stream.of(new DeadEndFiller(), new ShortestPathFinder(), new AStar());
    }

    private boolean checkValidPath(List<List<Cell>> maze, List<Point> path) {
        List<Point> currentPath = new ArrayList<>(path);
        Point current = currentPath.removeFirst();
        for (Point point: currentPath) {
            if (maze.get(
                    (current.y() * 2 + 1) + (point.y() - current.y()))
                .get((current.x() * 2 + 1) + (point.x() - current.x())) != null) {
                return false;
            }
            current = point;
        }
        return true;
    }

}
