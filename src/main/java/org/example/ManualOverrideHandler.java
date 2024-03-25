package org.example;

import java.util.List;
import java.util.Scanner;

public class ManualOverrideHandler {
    public static void performManualOverride(List<Player> players, GameBoard gameBoard, Scanner scanner) {
        System.out.println("Performing manual override...");
        System.out.println("1. Set Player Starting Locations");
        System.out.println("2. Modify Game State");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                setPlayerStartingLocations(players, scanner, gameBoard);
                break;
            case 2:
                modifyGameState(gameBoard, scanner);
                  break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void setPlayerStartingLocations(List<Player> players, Scanner scanner, GameBoard gameBoard) {
        System.out.println("Setting player starting locations...");
        for (Player player : players) {
            System.out.print("Enter starting row for " + player.getName() + ": ");

            int row = scanner.nextInt();
            while (row < 0 || row > gameBoard.getBoardSize()) {
                System.out.println("Entered incorrect row value, Please remember the board size is "  + gameBoard.getBoardSize() + " X " + gameBoard.getBoardSize());
                row = scanner.nextInt();
            }

            System.out.print("Enter starting column for " + player.getName() + ": ");

            int column = scanner.nextInt();
            while (column < 0 || column > gameBoard.getBoardSize()) {
                System.out.println("Entered incorrect column value, Please remember the board size is "  + gameBoard.getBoardSize() + " X " + gameBoard.getBoardSize());
                column = scanner.nextInt();
            }

            player.setCurrentPosition(new BoardElement(row, column));
        }
    }

    private static void modifyGameState(GameBoard gameBoard, Scanner scanner) {
        System.out.println("Modifying game state...");
        System.out.println("1. Add Snake");
        System.out.println("2. Add Ladder");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (choice) {
            case 1:
                System.out.print("Enter the number of snakes to add : ");
                int numSnakes = scanner.nextInt();
                addSnakes(gameBoard, scanner, numSnakes);
                break;
            case 2:
                System.out.print("Enter the number of ladders to add : ");
                int numLadders = scanner.nextInt();
                addLadders(gameBoard, scanner, numLadders);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void addSnakes(GameBoard gameBoard, Scanner scanner, int numSnakes) {
        for (int i = 0; i < numSnakes; i++) {
            System.out.println("Adding snake " + (i + 1));
            addSnake(gameBoard, scanner);
        }
    }

    private static void addLadders(GameBoard gameBoard, Scanner scanner, int numLadders) {
        for (int i = 0; i < numLadders; i++) {
            System.out.println("Adding Ladder " + (i + 1));
            addLadder(gameBoard, scanner);
        }
    }



    private static void addSnake(GameBoard gameBoard, Scanner scanner) {
        System.out.print("Enter head row for the snake: ");
        int headRow = scanner.nextInt();
        System.out.print("Enter head column for the snake: ");
        int headColumn = scanner.nextInt();
        System.out.print("Enter tail row for the snake: ");
        int tailRow = scanner.nextInt();
        System.out.print("Enter tail column for the snake: ");
        int tailColumn = scanner.nextInt();
        gameBoard.getSnakes().put(new BoardElement(headRow, headColumn), tailRow * gameBoard.getBoardSize() + tailColumn);
        System.out.println("Snake added successfully from " + new BoardElement(headRow, headColumn).toString() + " to " + new BoardElement(tailRow, tailColumn).toString());
    }

    private static void addLadder(GameBoard gameBoard, Scanner scanner) {
        System.out.print("Enter bottom row for the ladder: ");
        int bottomRow = scanner.nextInt();
        System.out.print("Enter bottom column for the ladder: ");
        int bottomColumn = scanner.nextInt();
        System.out.print("Enter top row for the ladder: ");
        int topRow = scanner.nextInt();
        System.out.print("Enter top column for the ladder: ");
        int topColumn = scanner.nextInt();
        gameBoard.getLadders().put(new BoardElement(bottomRow, bottomColumn), topRow * gameBoard.getBoardSize() + topColumn);
        System.out.println("Ladder added successfully from " + new BoardElement(bottomRow, bottomColumn).toString() + " to " + new BoardElement(topRow, topColumn).toString());
    }

}
