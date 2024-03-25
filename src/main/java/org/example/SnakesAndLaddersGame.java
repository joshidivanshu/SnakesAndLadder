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
            normalGameExecution(players, gameBoard);
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
        simulateGame(players, gameBoard, movementStrategy, executionType, scanner);

        scanner.close();
    }

    private static void normalGameExecution(List<Player> players, GameBoard gameBoard) {
        Dice dice = new Dice();
        int currentPlayerIndex = 0;
        while (!isGameFinished(players, gameBoard)) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int[] diceValues = {dice.roll()};

            for (int diceValue : diceValues) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " ");
            }

            // Move player
            currentPlayer.move(diceValues, gameBoard, "");

            //check if after moving the player encounters a snake or a ladder.
            checkIfSnakeOrLadder(currentPlayer, gameBoard);

            //check if this player reaches a point where there is already a player
            checkIfTwoPlayers(players, currentPlayer);

            // Next player's turn
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        System.out.println("Game Over");
    }

    private static void simulateGame(List<Player> players, GameBoard gameBoard, String movementStrategy, String manual, Scanner scanner) {
        Dice dice = new Dice();
        int currentPlayerIndex = 0;
        while (!isGameFinished(players, gameBoard)) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int[] diceValues = new int[gameBoard.getNumberOfDice()];
            if( manual.equalsIgnoreCase("manual")) {
                for (int i  = 0; i < gameBoard.getNumberOfDice(); i++) {
                    System.out.println("Please input " + gameBoard.getNumberOfDice() + "Dice values for this step for Player " + currentPlayer.getName());
                    int value = scanner.nextInt();
                    if(value > 6 || value < 0) {
                        System.out.println("Entered incorrect die value, closing the game. Please start a new game!!");
                        return;
                    }
                    diceValues[i] = value;
                }
            }
            else {
                for (int i = 0; i < gameBoard.getNumberOfDice(); i++) {
                    diceValues[i] = dice.roll();
                }
            }

            for (int diceValue : diceValues) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " ");
            }

            // Move player
            currentPlayer.move(diceValues, gameBoard, movementStrategy);

            //check if after moving the player encounters a snake or a ladder.
            checkIfSnakeOrLadder(currentPlayer, gameBoard);

            //check if after moving the player encounters a specialObject
            checkIfSpecialObject(currentPlayer, gameBoard);

            //check if this player reaches a point where there is already a player
            checkIfTwoPlayers(players, currentPlayer);

            // Next player's turn
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        System.out.println("Game Over");
    }

    private static boolean isGameFinished(List<Player> players, GameBoard gameBoard) {
        for (Player player : players) {
            if (player.getCurrentPosition().getRow() == gameBoard.getBoardSize() && player.getCurrentPosition().getColumn() == 0) {
                return true;
            }
        }
        return false;
    }

    private static void checkIfTwoPlayers(List<Player> players,Player player) {
        for (Player otherPlayer : players) {
            if (player != otherPlayer && otherPlayer.getCurrentPosition().getRow() == player.getCurrentPosition().getRow()
                    && otherPlayer.getCurrentPosition().getColumn() == player.getCurrentPosition().getColumn()) {
                // If occupied, reset the other player's position
                System.out.println("Player " + otherPlayer.getName() + " encountered Player " + player.getName() + " and has to start again from 1.");
                otherPlayer.setCurrentPosition(new BoardElement(0, 0));
                break;
            }
        }
    }

    private static void checkIfSnakeOrLadder(Player player, GameBoard gameBoard) {
        BoardElement currentPosition = player.getCurrentPosition();
        Map<BoardElement, Integer> snakes = gameBoard.getSnakes();
        Map<BoardElement, Integer> ladders = gameBoard.getLadders();

        // Check if the current position corresponds to a snake
        if (snakes.containsKey(currentPosition)) {
            int tailPosition = snakes.get(currentPosition);
            // Move the player to the tail of the snake
            player.setCurrentPosition(gameBoard.getPositionFromIndex(tailPosition));
            System.out.println(player.getName() + " encountered a snake and moved to " + player.getCurrentPosition());
        }
        // Check if the current position corresponds to a ladder
        else if (ladders.containsKey(currentPosition)) {
            int topPosition = ladders.get(currentPosition);
            // Move the player to the top of the ladder
            player.setCurrentPosition(gameBoard.getPositionFromIndex(topPosition));
            System.out.println(player.getName() + " encountered a ladder and moved to " + player.getCurrentPosition());
        }
    }

    private static void checkIfSpecialObject(Player player, GameBoard gameBoard) {
        BoardElement currentPosition = player.getCurrentPosition();
        Map<BoardElement, SpecialObjectType> specialObjects = gameBoard.getSpecialObjects();

        if (specialObjects.containsKey(currentPosition)) {
            SpecialObjectType objectType = specialObjects.get(currentPosition);
            switch (objectType) {
                case CROCODILE:
                    movePlayerBack(player);
                    System.out.println(player.getName() + " encountered a crocodile and moved 5 steps back to " + player.getCurrentPosition());
                    break;
                case MINE:
                    player.setCoolDown(gameBoard.getCoolDown()); // Set cooldown to 2 turns
                    System.out.println(player.getName() + " encountered a mine and will be held for 2 turns");
                    break;
            }
        }
    }

    private static void movePlayerBack(Player player) {
        int newPosition = player.getCurrentPosition().getRow() * 10 + player.getCurrentPosition().getColumn() - 5;
        int newRow = newPosition / 10;
        int newColumn = newPosition % 10;
        player.setCurrentPosition(new BoardElement(newRow, newColumn));
    }



}
