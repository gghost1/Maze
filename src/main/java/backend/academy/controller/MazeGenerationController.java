package backend.academy.controller;

import backend.academy.exception.ExceptionLogger;
import backend.academy.exception.NotInitializedException;
import backend.academy.game.maze.cell.Cell;
import backend.academy.game.maze.cell.CellFlorType;
import backend.academy.game.process.maze.MazeGenerationProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.util.List;

public class MazeGenerationController extends Executable {

    private final MazeGenerationProcess mazeGenerationProcess;

    public MazeGenerationController(MazeGenerationProcess mazeGenerationProcess) throws NotInitializedException {
        super(CustomInput.getInstance(), CustomOutput.getInstance(), LanguageManager.dictionary());
        this.mazeGenerationProcess = mazeGenerationProcess;
    }

    @Override
    public void execute() {
        mazeGenerationProcess.generateMaze();
        output.writeOutput(dictionary.getString("The wall is displayed as ") + dictionary.getWall());
        output.writeOutput(dictionary.getString("The passage is displayed as ") + dictionary.getPath());
        for (CellFlorType type : CellFlorType.values()) {
            output
                .writeOutput(dictionary.getString("The ")
                    + dictionary.getString(type.name().toLowerCase())
                    + dictionary.getString(" is displayed as ")
                    + type
                    + dictionary.getString(". It slows down the player by ")
                    + type.getEffectValue() + dictionary.getString(" hours"));
        }

        List<List<Cell>> maze = mazeGenerationProcess.mazeProcess().maze().maze();
        for (List<Cell> row : maze) {
            StringBuilder line = new StringBuilder();
            for (Cell cell : row) {
                if (cell == null) {
                    line.append(dictionary.getPath());
                } else {
                    try {
                        line.append(cell.getRepresentation());
                    } catch (NotInitializedException e) {
                        ExceptionLogger.log(e);
                        throw new RuntimeException(e);
                    }
                }
            }
            output.writeOutput(line.toString());
        }
    }
}
