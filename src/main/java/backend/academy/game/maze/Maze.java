package backend.academy.game.maze;

import backend.academy.game.maze.algorithm.findPath.FindMazePath;
import backend.academy.game.maze.algorithm.generate.CreateMaze;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellFlorType;
import backend.academy.game.maze.cell.CellType;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import it.unimi.dsi.fastutil.Pair;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<List<Cell>> maze;
    private CreateMaze createMaze;
    private FindMazePath findMazePath;

    private int width;
    private int height;

    public Maze(int width, int height, CreateMaze createMaze, FindMazePath findMazePath) {
        this.width = width;
        this.height = height;
        this.createMaze = createMaze;
        this.findMazePath = findMazePath;
        this.maze = createInitMaze();
    }

    public void generateMaze(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        maze = createMaze.apply(maze, start, end);
    }

    public List<Pair<Integer, Integer>> solveMaze(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
        return findMazePath.apply(maze, start, end);
    }

    private List<List<Cell>> createInitMaze() {
        List<List<Cell>> initMaze = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            List<Cell> row = new ArrayList<>();
            if (i%2 == 0) {
                for (int j = 0; j < width; j++) {
                    row.add(new Wall(j, i));
                }
                initMaze.add(row);
            } else {
                for (int j = 0; j < width; j++) {
                    if (j%2 == 0) {
                        row.add(new Wall(j, i));
                    } else {
                        row.add(new Path(j/2, i/2, CellType.PATH, CellFlorType.GOOD));
                    }
                }
                initMaze.add(row);
            }
        }
        return initMaze;
    }

}
