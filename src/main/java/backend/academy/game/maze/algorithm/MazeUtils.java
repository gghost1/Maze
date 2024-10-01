package backend.academy.game.maze.algorithm;

public interface MazeUtils {
    default int getX(int x) {
        return x*2+1;
    }
    default int getY(int y) {
        return y*2+1;
    }
}
