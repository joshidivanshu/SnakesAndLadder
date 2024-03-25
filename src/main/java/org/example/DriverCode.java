package org.example;

import java.util.List;
import java.util.Scanner;

public class DriverCode {

    public static void manualInput(Scanner scanner, GameBoard gameBoard, List<Player> players) {
        System.out.println("Please add total number of snakes");
        int numberOfSnakes = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfSnakes; i++) {
            String[] values = scanner.nextLine().split(" ");
            int val1 = Integer.parseInt(values[0]);
            int val2 = Integer.parseInt(values[1]);
            if (val1 < val2) {
                System.out.println("Incorrect data, head cannot be smaller than the tail of a snake. Exiting Please start a new game");
                return;
            }
            int headRow = val1 / 10;
            int headColumn = val1 % 10;
            int tailRow = val2 / 10;
            int tailColumn = val2 % 10;
            gameBoard.getSnakes().put(new BoardElement(headRow, headColumn), tailRow * gameBoard.getBoardSize() + tailColumn);
            System.out.println("Snake added successfully from " + new BoardElement(headRow, headColumn).toString() + " to " + new BoardElement(tailRow, tailColumn).toString());
        }

        System.out.println("Please add total number of ladders");
        int numberOfLadders = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfLadders; i++) {
            String[] values = scanner.nextLine().split(" ");
            int val1 = Integer.parseInt(values[0]);
            int val2 = Integer.parseInt(values[1]);
            if (val1 >= val2) {
                System.out.println("The ladder bottom cannot be greater than top of the ladder incorrect input. Exiting the game, Start a new game!!");
            }
            int bottomRow = val1 / 10;
            int bottomColumn = val1 % 10;
            int topRow = val2 / 10;
            int topColumn = val2 % 10;
            gameBoard.getLadders().put(new BoardElement(bottomRow, bottomColumn), topRow * gameBoard.getBoardSize() + topColumn);
            System.out.println("Ladder added successfully from " + new BoardElement(bottomRow, bottomColumn).toString() + " to " + new BoardElement(topRow, topColumn).toString());
        }

        System.out.println("Please enter the total number of players");
        int numberOfPlayers = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Enter player name and starting location (row column) separated by space:");
            String[] values = scanner.nextLine().split(" ");
            String playerName = values[0];
            int val1 = Integer.parseInt(values[1]);
            int row = val1 / 10;
            int column = val1 % 10;
            Player player = new Player(playerName, new BoardElement(row, column), null, 0);
            players.add(player);
        }

    }
}
