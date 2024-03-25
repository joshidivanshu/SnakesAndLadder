package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.movementStrategy.MaxMovementStrategy;
import org.example.movementStrategy.MinMovementStrategy;
import org.example.movementStrategy.MovementStrategy;
import org.example.movementStrategy.SumMovementStrategy;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private BoardElement currentPosition;
    private MovementStrategy movementStrategy;
    private int mineCooldown;
    public void move(int []diceValues, GameBoard gameBoard, String movementStrategyValue) {

        if (mineCooldown > 0) {
            mineCooldown--;
            System.out.println("Player " + name + " is trapped by a mine. Skipping turn...");
            return;
        }

        String getMovStrategy = movementStrategyValue.toLowerCase();
        if (getMovStrategy.equals("min")) {
            movementStrategy = new MinMovementStrategy();
        }
        else if(getMovStrategy.equals("max")) {
            movementStrategy = new MaxMovementStrategy();
        }
        else if (getMovStrategy.equals("sum")) {
            movementStrategy = new SumMovementStrategy();
        }
        else if (getMovStrategy.isEmpty()) {
            movementStrategy = null;
        }
        int steps = 0;
        if( movementStrategy != null) {
            steps = movementStrategy.calculateMove(diceValues);
        }
        else {
            steps = diceValues[0];
        }

        BoardElement previousPosition = this.currentPosition;
        int newPosition = currentPosition.getRow() * 10 + currentPosition.getColumn() + steps;

        // Check if the new position is within the bounds of the game board
        int newRow = newPosition / 10;
        int newColumn = newPosition % 10;
        if (isInvalidMove(newRow, newColumn, gameBoard)) {
            return;
        }

        currentPosition = new BoardElement(newRow, newColumn);
        System.out.println(name + " moved from " + previousPosition + " to " + currentPosition);

    }

    public boolean isInvalidMove(int newRow, int newColumn, GameBoard gameBoard) {
        if (newRow < 0 || newRow > gameBoard.getBoardSize() || newColumn < 0 || newColumn > gameBoard.getBoardSize()) {
            System.out.println("Invalid move: Player " + name + " attempted to move out of bounds.");
            return true;
        }
        return false;
    }

    public void setCoolDown(int value) {
        this.mineCooldown = value;
        return;
    }

}
