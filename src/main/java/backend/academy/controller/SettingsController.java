package backend.academy.controller;

import backend.academy.StaticVariables;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcessException;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.game.process.SettingsProcess;
import backend.academy.io.CustomInput;
import backend.academy.io.language.LanguageManager;
import backend.academy.io.output.CustomOutput;
import java.security.SecureRandom;
import lombok.Getter;

@Getter
public class SettingsController extends Executable {

    private final SettingsProcess settingsProcess;
    private final SecureRandom secureRandom;

    public SettingsController() throws NotInitializedException {
        super(CustomInput.getInstance(), CustomOutput.getInstance(), LanguageManager.dictionary());
        settingsProcess = new SettingsProcess();
        secureRandom = new SecureRandom();
    }

    private boolean setMazeWidth(String input) {
        boolean done;
        try {
            settingsProcess.mazeWidth(Integer.parseInt(input));
            done = true;
        } catch (NumberFormatException e) {
            done = false;
        }
        return done;
    }

    private boolean setMazeHeight(String input) {
        boolean done;
        try {
            settingsProcess.mazeHeight(Integer.parseInt(input));
            done = true;
        } catch (NumberFormatException e) {
            done = false;
        }
        return done;
    }

    private boolean setStartPoint(String input1, String input2) {
        boolean done;
        try {
            settingsProcess.start(new Point(
                Integer.parseInt(input1),
                Integer.parseInt(input2))
            );
            done = true;
        } catch (NumberFormatException e) {
            done = false;
        }
        return done;
    }

    private boolean setEndPoint(String input1, String input2) {
        boolean done;
        try {
            settingsProcess.end(new Point(
                Integer.parseInt(input1),
                Integer.parseInt(input2))
            );
            done = true;
        } catch (NumberFormatException e) {
            done = false;
        }
        return done;
    }

    private boolean setCreateMazeAlgorithmByNumber(int number) {
        if (number == StaticVariables.RANDOM_INDEX()) {
            settingsProcess
                .createMazeAlgorithm(
                    CreateMazeAlgorithm
                        .values()[secureRandom.nextInt(CreateMazeAlgorithm.values().length)]);
            return true;
        } else if (number < -1 || number >= CreateMazeAlgorithm.values().length) {
            return false;
        } else {
            settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.values()[number]);
            return true;
        }
    }

    private boolean setCreateMazeAlgorithmByString(String input) {
        switch (input.trim().toLowerCase()) {
            case "prims":
                settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.PRIMS);
                break;
            case "recursive back tracker":
                settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.RECURSIVE_BACKTRACKER);
                break;
            case "wave propagation":
                settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.WAVE_PROPAGATION);
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean setCreateMazeAlgorithm(String input) {
        boolean done;
        int number;
        try {
            number = Integer.parseInt(input) + StaticVariables.INDEX_TRANSFORMATION();
            done = setCreateMazeAlgorithmByNumber(number);
        } catch (NumberFormatException e) {
            done = true;
            if (input.trim().toLowerCase().contains(StaticVariables.RANDOM())) {
                settingsProcess
                    .createMazeAlgorithm(
                        CreateMazeAlgorithm
                            .values()[secureRandom.nextInt(CreateMazeAlgorithm.values().length)]);
            } else {
                done = setCreateMazeAlgorithmByString(input);
            }
        }
        return done;
    }

    private boolean setSolveMazeAlgorithmByNumber(int number) {
        if (number == StaticVariables.RANDOM_INDEX()) {
            settingsProcess
                .findMazePathAlgorithm(
                    FindMazePathAlgorithm
                        .values()[secureRandom.nextInt(FindMazePathAlgorithm.values().length)]);
            return true;
        } else if (number < -1 || number >= FindMazePathAlgorithm.values().length) {
            return false;
        } else {
            settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.values()[number]);
            return true;
        }
    }

    private boolean setSolveMazeAlgorithmByString(String input) {
        switch (input.trim().toLowerCase()) {
            case "dead end filler":
                settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.DEAD_END_FILLER);
                break;
            case "shortest path finder":
                settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.SHORTEST_PATH_FINDER);
                break;
            case "a*":
                settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.A_STAR);
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean setSolveMazeAlgorithm(String input) {
        boolean done;
        int number;
        try {
            number = Integer.parseInt(input) + StaticVariables.INDEX_TRANSFORMATION();
            done = setSolveMazeAlgorithmByNumber(number);
        } catch (NumberFormatException e) {
            done = true;
            if (input.trim().toLowerCase().contains(StaticVariables.RANDOM())) {
                settingsProcess
                    .findMazePathAlgorithm(
                        FindMazePathAlgorithm
                            .values()[secureRandom.nextInt(FindMazePathAlgorithm.values().length)]);
            } else {
                done = setSolveMazeAlgorithmByString(input);
            }
        }
        return done;
    }

    private void widthInput() {
        boolean done;
        output.writeOutput(dictionary.getString("Input a width of the maze"));
        do {
            done = setMazeWidth(listener.readInputLine().trim());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    private void heightInput() {
        boolean done;
        output.writeOutput(dictionary.getString("Input a height of the maze"));
        do {
            done = setMazeHeight(listener.readInputLine().trim());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    private void startPointInput() {
        boolean done;
        output.writeOutput(dictionary.getString("Input a start point <x y>"));
        output.writeOutput(dictionary.getCountingStartsFrom());
        do {
            String[] input = listener.readInputLine().trim().split(" ");
            if (input.length != 2) {
                done = false;
                continue;
            }
            done = setStartPoint(input[0], input[1]);
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    private void endPointInput() {
        boolean done;
        output.writeOutput(dictionary.getString("Input an end point <x y>"));
        output.writeOutput(dictionary.getCountingStartsFrom());
        do {
            String[] input = listener.readInputLine().trim().split(" ");
            if (input.length != 2) {
                done = false;
                continue;
            }
            done = setEndPoint(input[0], input[1]);
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    private void creationAlgorithmInput() {
        boolean done;
        output
            .writeOutput(
                dictionary
                    .getString("Choose an algorithm for creation of the maze")
            );
        output.writeOutput(dictionary.getInputNumberOrNameOfAlgorithm());
        output.writeOutput(dictionary.getCreateAlgorithms(), true, "");
        do {
            done = setCreateMazeAlgorithm(listener.readInputLine());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    private void solveAlgorithmDependency() {
        if (settingsProcess.createMazeAlgorithm() == CreateMazeAlgorithm.WAVE_PROPAGATION) {
            output
                .writeOutput(CreateMazeAlgorithm.WAVE_PROPAGATION
                    + dictionary.getString(" was chosen"));
            output
                .writeOutput(dictionary.getString("For this generation algorithm, only ")
                    + FindMazePathAlgorithm.A_STAR
                    + dictionary.getString(" is suitable"));
            settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.A_STAR);
        } else {
            solveAlgorithmInput();
        }
    }

    private void solveAlgorithmInput() {
        boolean done;
        output
            .writeOutput(
                dictionary
                    .getString("Choose an algorithm for solving the maze")
            );
        output.writeOutput(dictionary.getInputNumberOrNameOfAlgorithm());
        output.writeOutput(dictionary.getSolveAlgorithms(), true, "");
        do {
            done = setSolveMazeAlgorithm(listener.readInputLine());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);
    }

    @Override
    public void execute() throws UnsuccessfulPreviousProcessException {
        widthInput();
        heightInput();
        startPointInput();
        endPointInput();
        creationAlgorithmInput();
        solveAlgorithmDependency();
    }
}
