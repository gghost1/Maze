package backend.academy.controller;

import backend.academy.StaticVariables;
import backend.academy.exception.NotInitializedException;
import backend.academy.exception.UnsuccessfulPreviousProcess;
import backend.academy.game.maze.algorithm.Point;
import backend.academy.game.maze.algorithm.findPath.FindMazePathAlgorithm;
import backend.academy.game.maze.algorithm.generate.CreateMazeAlgorithm;
import backend.academy.game.process.SettingsProcess;
import java.security.SecureRandom;
import lombok.Getter;

@Getter
public class SettingsController extends Executable {

    private final SettingsProcess settingsProcess;

    public SettingsController() throws NotInitializedException {
        settingsProcess = new SettingsProcess();
    }

    private boolean setMazeWidth(String input) {
        boolean done = false;
        try {
            settingsProcess.mazeWidth(Integer.parseInt(input));
            done = true;
        } catch (NumberFormatException e) {

        }
        return done;
    }

    private boolean setMazeHeight(String input) {
        boolean done = false;
        try {
            settingsProcess.mazeHeight(Integer.parseInt(input));
            done = true;
        } catch (NumberFormatException e) {

        }
        return done;
    }

    private boolean setStartPoint(String input1, String input2) {
        boolean done = false;
        try {
            settingsProcess.start(new Point(
                Integer.parseInt(input1),
                Integer.parseInt(input2))
            );
            done = true;
        } catch (NumberFormatException e) {

        }
        return done;
    }

    private boolean setEndPoint(String input1, String input2) {
        boolean done = false;
        try {
            settingsProcess.end(new Point(
                Integer.parseInt(input1),
                Integer.parseInt(input2))
            );
            done = true;
        } catch (NumberFormatException e) {

        }
        return done;
    }

    private boolean setCreateMazeAlgorithm(String input) {
        boolean done;
        int number;
        try {
            number = Integer.parseInt(input) - 1;
            if (number == -1) {
                settingsProcess
                    .createMazeAlgorithm(
                        CreateMazeAlgorithm
                            .values()[new SecureRandom().nextInt(CreateMazeAlgorithm.values().length)]);
                done = true;
            } else if (number < -1 || number >= FindMazePathAlgorithm.values().length) {
                done = false;
            } else {
                settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.values()[number]);
                done = true;
            }
        } catch (NumberFormatException e) {
            done = true;
            if (input.trim().toLowerCase().contains(StaticVariables.RANDOM())) {
                settingsProcess
                    .createMazeAlgorithm(
                        CreateMazeAlgorithm
                            .values()[new SecureRandom().nextInt(CreateMazeAlgorithm.values().length)]);
            } else {
                switch (input.trim().toLowerCase()) {
                    case "prims":
                        settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.PRIMS);
                        break;
                    case "recursive back tracker":
                        settingsProcess.createMazeAlgorithm(CreateMazeAlgorithm.RECURSIVE_BACKTRACKER);
                        break;
                    default:
                        done = false;
                }
            }
        }
        return done;
    }

    private boolean setSolveMazeAlgorithm(String input) {
        boolean done;
        int number;
        try {
            number = Integer.parseInt(input) - 1;
            if (number == -1) {
                settingsProcess
                    .findMazePathAlgorithm(
                        FindMazePathAlgorithm
                            .values()[new SecureRandom().nextInt(FindMazePathAlgorithm.values().length)]);
                done = true;
            } else if (number < -1 || number >= FindMazePathAlgorithm.values().length) {
                done = false;
            } else {
                settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.values()[number]);
                done = true;
            }
        } catch (NumberFormatException e) {
            done = true;
            if (input.trim().toLowerCase().contains(StaticVariables.RANDOM())) {
                settingsProcess
                    .findMazePathAlgorithm(
                        FindMazePathAlgorithm
                            .values()[new SecureRandom().nextInt(FindMazePathAlgorithm.values().length)]);
            } else {
                switch (input.trim().toLowerCase()) {
                    case "dead end filler":
                        settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.DEAD_END_FILLER);
                        break;
                    case "shortest path finder":
                        settingsProcess.findMazePathAlgorithm(FindMazePathAlgorithm.SHORTEST_PATH_FINDER);
                        break;
                    default:
                        done = false;
                }
            }
        }
        return done;
    }

    @Override
    public void execute() throws UnsuccessfulPreviousProcess {
        boolean done;
        output.writeOutput(dictionary.getString("Input a width of the maze"));
        do {
            done = setMazeWidth(listener.readInputLine().trim());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);

        output.writeOutput(dictionary.getString("Input a height of the maze"));
        do {
            done = setMazeHeight(listener.readInputLine().trim());
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);

        output.writeOutput(dictionary.getString("Input a start point <x y>"));
        output.writeOutput(dictionary.getCountingStartsFrom());
        do {
            String[] input = listener.readInputLine().trim().split(" ");
            done = setStartPoint(input[0], input[1]);
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);

        output.writeOutput(dictionary.getString("Input an end point <x y>"));
        output.writeOutput(dictionary.getCountingStartsFrom());
        do {
            String[] input = listener.readInputLine().trim().split(" ");
            done = setEndPoint(input[0], input[1]);
            if (!done) {
                output.writeOutput(dictionary.exceptionIncorrectInput());
            }
        } while (!done);

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
}
