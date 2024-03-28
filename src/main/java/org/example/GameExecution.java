package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameExecution {
    public static void normalGameExecution(List<Player> players, GameBoard gameBoard) {
        Dice dice = new Dice();
        int currentPlayerIndex = 0;
        while (!GameUtility.isGameFinished(players, gameBoard)) {
            Player currentPlayer = players.get(currentPlayerIndex);
            int[] diceValues = {dice.roll()};

            for (int diceValue : diceValues) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " ");
            }

            // Move player
            currentPlayer.move(diceValues, gameBoard);

            //check if after moving the player encounters a snake or a ladder.
            GameUtility.checkIfSnakeOrLadder(currentPlayer, gameBoard);

            //check if this player reaches a point where there is already a player
            GameUtility.checkIfTwoPlayers(players, currentPlayer);

            // Next player's turn
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        System.out.println("Game Over");
    }

    public static void simulateGame(List<Player> players, GameBoard gameBoard, String manual, Scanner scanner) {
        Dice dice = new Dice();
        int currentPlayerIndex = 0;
        while (!GameUtility.isGameFinished(players, gameBoard)) {
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
            currentPlayer.move(diceValues, gameBoard);

            //check if after moving the player encounters a snake or a ladder.
            GameUtility.checkIfSnakeOrLadder(currentPlayer, gameBoard);

            //check if after moving the player encounters a specialObject
            GameUtility.checkIfSpecialObject(currentPlayer, gameBoard);

            //check if this player reaches a point where there is already a player
            GameUtility.checkIfTwoPlayers(players, currentPlayer);

            // Next player's turn
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        System.out.println("Game Over");
    }



}
