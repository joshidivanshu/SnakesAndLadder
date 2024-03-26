package org.example;

import java.util.List;
import java.util.Map;

public class GameUtility {
    public static boolean isGameFinished(List<Player> players, GameBoard gameBoard) {
        for (Player player : players) {
            if (player.getCurrentPosition().getRow() == gameBoard.getBoardSize() && player.getCurrentPosition().getColumn() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void checkIfTwoPlayers(List<Player> players,Player player) {
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

    public static void checkIfSnakeOrLadder(Player player, GameBoard gameBoard) {
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

    public static void checkIfSpecialObject(Player player, GameBoard gameBoard) {
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

    public static void movePlayerBack(Player player) {
        int newPosition = player.getCurrentPosition().getRow() * 10 + player.getCurrentPosition().getColumn() - 5;
        int newRow = newPosition / 10;
        int newColumn = newPosition % 10;
        player.setCurrentPosition(new BoardElement(newRow, newColumn));
    }
}
