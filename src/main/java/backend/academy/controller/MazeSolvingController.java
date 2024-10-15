package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.maze.cell.Wall;
import backend.academy.game.process.maze.MazeSolvingProcess;
import it.unimi.dsi.fastutil.Pair;
import org.checkerframework.checker.units.qual.C;
import java.util.Dictionary;
import java.util.List;

public class MazeSolvingController extends Executable {

    private final MazeSolvingProcess mazeSolvingProcess;

    public MazeSolvingController(MazeSolvingProcess mazeSolvingProcess) throws NotInitializedException {
        this.mazeSolvingProcess = mazeSolvingProcess;
    }

    private boolean isConnector(Point point, List<List<Cell>> maze) {
        int counterNeighbours = 0;
        List<Pair<Integer, Integer>> directions = List.of(
            Pair.of(-1, 0),
            Pair.of(0, -1),
            Pair.of(1, 0),
            Pair.of(0, 1)
        );
        for (Pair<Integer, Integer> direction: directions) {
            Cell cell = maze.get(point.y()-direction.second()).get(point.x()-direction.first());
            if (cell instanceof Path) {
                if (((Path) cell).isPath()) {
                    counterNeighbours++;
                }
            }
        }
        return counterNeighbours == 2;
    }

    @Override
    public void execute() {
        try {
            mazeSolvingProcess.solveMaze();
            output.writeOutput(dictionary.getString("The wall is displayed as ") + dictionary.getWall());
            output.writeOutput(dictionary.getString("The path is displayed as ") + dictionary.getPath());
            output.writeOutput(dictionary.getString("The correct path from start to end is displayed as ") + dictionary.getCorrectPath());
            List<List<Cell>> maze = mazeSolvingProcess.mazeProcess().maze().maze();
            for (int i = 0; i < maze.size(); i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < maze.getFirst().size(); j++) {
                    Cell cell = maze.get(i).get(j);
                    if (cell instanceof Wall) {
                        line.append(dictionary.getWall());
                    } else {
                        if (cell instanceof Path) {
                            if (((Path) cell).isPath()) {
                                line.append(dictionary.getCorrectPath());
                            } else {
                                line.append(dictionary.getPath());
                            }
                        } else {
                            if (isConnector(new Point(j,i), maze)) {
                                line.append(dictionary.getCorrectPath());
                            } else {
                                line.append(dictionary.getPath());
                            }
                        }
                    }
                }
                output.writeOutput(line.toString());
            }
        } catch (PathNotFoundException e) {
            output.writeOutput(dictionary.getString("There is no path from start to end in the maze"));
        }
    }
}
