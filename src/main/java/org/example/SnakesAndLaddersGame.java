package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SnakesAndLaddersGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        * normal - for normal snakes and ladder gameplay , taking input from driver
        * manual - for taking input from json file but adding the capability to add player initial positions
        * and snakes and ladder positions manually
        * auto - automatic logic to generate snakes and ladders taking input in the json file and running the game.
        * */
        String executionType = scanner.next();
        if(executionType.equalsIgnoreCase("normal")) {
            GameBoard gameBoard = new GameBoard(10, 1);
            List<Player> players = new ArrayList<>();
            DriverCode.manualInput(scanner, gameBoard, players);
            GameExecution.normalGameExecution(players, gameBoard);
            return;
        }


        // Read game configuration from JSON file
        GameConfig gameConfig = GameConfigReader.readGameConfig("/Users/divajosh/Downloads/SnakesAndLaddersGameSolution/src/main/java/org/example/game_config.json");
        System.out.println(gameConfig);

        // Create game board with snakes, ladders, and special objects
        GameBoard gameBoard = new GameBoard(gameConfig);
        List<Player> players = new ArrayList<>();
        String movementStrategy = "";

        if (gameConfig != null) {
            players = gameConfig.getPlayers();
            movementStrategy = gameConfig.getMovementStrategy();
        }


        //Manual Configurations can be added here.
        if (executionType.equalsIgnoreCase("manual")) {
            ManualOverrideHandler.performManualOverride(players, gameBoard, scanner);
        }

        // Simulate game turns in a round-robin fashion
        // Log game events
        GameExecution.simulateGame(players, gameBoard, movementStrategy, executionType, scanner);

        scanner.close();
    }
}
