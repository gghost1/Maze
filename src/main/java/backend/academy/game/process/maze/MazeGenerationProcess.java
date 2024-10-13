package backend.academy.game.process.maze;

public class MazeGenerationProcess {

    private final MazeProcess mazeProcess;

    public MazeGenerationProcess(MazeProcess mazeProcess) {
        this.mazeProcess = mazeProcess;
    }

    public void generateMaze() {
        mazeProcess.maze().generateMaze(mazeProcess.settings().start(), mazeProcess.settings().end());
    }

}
