package backend.academy.controller;

import backend.academy.exception.ExceptionLogger;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.PathNotFoundException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellType;
import backend.academy.game.maze.cell.Path;
import backend.academy.game.process.maze.MazeSolvingProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;

public class MazeSolvingController extends Executable {

    private final MazeSolvingProcess mazeSolvingProcess;

    public MazeSolvingController(MazeSolvingProcess mazeSolvingProcess) throws NotInitializedException {
        super(CustomInput.getInstance(), CustomOutput.getInstance(), LanguageManager.dictionary());
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
            Cell cell = maze.get(point.y() - direction.second()).get(point.x() - direction.first());
            if (cell != null && cell.type() == CellType.PATH) {
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
            output.writeOutput(dictionary.getString("The passage is displayed as ") + dictionary.getPath());
            output.writeOutput(
                dictionary.getString("The correct and fastest path from start to end is displayed as ")
                    + dictionary.getCorrectPath());
            List<List<Cell>> maze = mazeSolvingProcess.mazeProcess().maze().maze();
            for (int i = 0; i < maze.size(); i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < maze.getFirst().size(); j++) {
                    Cell cell = maze.get(i).get(j);
                    if (cell != null) {
                        line.append(cell.getRepresentation());
                    } else {
                        if (isConnector(new Point(j, i), maze)) {
                            line.append(dictionary.getCorrectPath());
                        } else {
                            line.append(dictionary.getPath());
                        }
                    }
                }
                output.writeOutput(line.toString());
            }
        } catch (PathNotFoundException e) {
            output.writeOutput(dictionary.getString("There is no path from start to end in the maze"));
        } catch (NotInitializedException e) {
            ExceptionLogger.log(e);
            throw new RuntimeException(e);
        }
    }
}
