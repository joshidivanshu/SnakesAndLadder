package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SnakesAndLaddersGame {
    private final static String MANUAL = "manual";
    private final static String AUTO = "auto";
    private final static String NORMAL = "normal";
    private final static String FILE_PATH = "E:/SnakesAndLaddersGameSolution/src/main/java/org/example/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        * normal - for normal snakes and ladder gameplay , taking input from driver
        * manual - for taking input from json file but adding the capability to add player initial positions
        * and snakes and ladder positions manually
        * auto - automatic logic to generate snakes and ladders taking input in the json file and running the game.
        * */
        String executionType = scanner.next().toLowerCase();
        switch (executionType) {
            case "normal":
                normalGameExecution(scanner);
                break;
            case "auto":
                automaticGameExecution(scanner);
                break;
            case "manual":
                manualExecution(scanner);
                break;
            default:
                System.out.println("Unexpected Input Provided Please Note \n" + "normal , auto and manual Expected");

        }

        scanner.close();
    }

    public static void normalGameExecution(Scanner scanner) {
        GameBoard gameBoard = new GameBoard(10, 1);
        List<Player> players =new ArrayList<>();
        DriverCode.manualInput(scanner, gameBoard, players);
        GameExecution.normalGameExecution(players, gameBoard);
        return;
    }

    public static void automaticGameExecution(Scanner scanner) {
        GameConfig gameConfig = readGameConfigFromFile("game_config.json");
        performGameExecution(gameConfig, AUTO, scanner);
    }

    public static void manualExecution(Scanner scanner) {
        GameConfig gameConfig = readGameConfigFromFile("game_config.json");
        performGameExecution(gameConfig, MANUAL, scanner);
    }

    private static GameConfig readGameConfigFromFile(String filename) {
        return GameConfigReader.readGameConfig(FILE_PATH + filename);
    }

    private static void performGameExecution(GameConfig gameConfig, String executionType, Scanner scanner) {
        System.out.println(gameConfig);

        // Create game board with snakes, ladders, and special objects
        GameBoard gameBoard = new GameBoard(gameConfig);
        List<Player> players = new ArrayList<>();
        String movementStrategy = "";

        if (gameConfig != null) {
            players = gameConfig.getPlayers();
            movementStrategy = gameConfig.getMovementStrategy();
        }

        if (executionType.equals(MANUAL)) {
            ManualOverrideHandler.performManualOverride(players, gameBoard, scanner);
        }

        GameExecution.simulateGame(players, gameBoard, executionType, scanner);
    }
}
