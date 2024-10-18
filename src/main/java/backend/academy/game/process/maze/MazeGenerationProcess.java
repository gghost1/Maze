package backend.academy.game.process.maze;

public record MazeGenerationProcess(MazeProcess mazeProcess) {
    public void generateMaze() {
        mazeProcess.maze().generateMaze(mazeProcess.settings().start(), mazeProcess.settings().end());
    }

}
