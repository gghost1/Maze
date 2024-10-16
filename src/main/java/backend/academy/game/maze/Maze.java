package backend.academy.game.maze;

import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePath;
import backend.academy.game.maze.algorithm.generate.CreateMaze;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellFlorType;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Maze {
    @Getter
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

    public void generateMaze(Point start, Point end) {
        maze = createMaze.apply(maze, start, end);
    }

    public List<Point> solveMaze(Point start, Point end)
        throws PathNotFoundException {
        return findMazePath.apply(maze, start, end);
    }

    private List<List<Cell>> createInitMaze() {
        List<List<Cell>> initMaze = new ArrayList<>();
        for (int i = 0; i < height * 2 + 1; i++) {
            List<Cell> row = new ArrayList<>();
            if (i % 2 == 0) {
                for (int j = 0; j < width * 2 + 1; j++) {
                    row.add(new Wall(j, i));
                }
                initMaze.add(row);
            } else {
                for (int j = 0; j < width * 2 + 1; j++) {
                    if (j % 2 == 0) {
                        row.add(new Wall(j, i));
                    } else {
                        row.add(new Path(j / 2, i / 2, CellFlorType.GOOD));
                    }
                }
                initMaze.add(row);
            }
        }
        return initMaze;
    }

}
