package backend.academy.controller;

import backend.academy.exception.NotInitializedException;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.Wall;
import backend.academy.game.process.maze.MazeGenerationProcess;
import java.util.List;

public class MazeGenerationController extends Executable {

    private final MazeGenerationProcess mazeGenerationProcess;

    public MazeGenerationController(MazeGenerationProcess mazeGenerationProcess) throws NotInitializedException {
        this.mazeGenerationProcess = mazeGenerationProcess;
    }

    @Override
    public void execute() {
        mazeGenerationProcess.generateMaze();
        output.writeOutput(dictionary.getString("The wall is displayed as ") + dictionary.getWall());
        output.writeOutput(dictionary.getString("The path is displayed as ") + dictionary.getPath());
        List<List<Cell>> maze = mazeGenerationProcess.mazeProcess().maze().maze();
        for (int i = 0; i < maze.size(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < maze.getFirst().size(); j++) {
                if (maze.get(i).get(j) instanceof Wall) {
                    line.append(dictionary.getWall());
                } else {
                    line.append(dictionary.getPath());
                }
            }
            output.writeOutput(line.toString());
        }
    }
}
