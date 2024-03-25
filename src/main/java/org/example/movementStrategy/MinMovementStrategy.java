package org.example.movementStrategy;

public class MinMovementStrategy implements MovementStrategy{
    @Override
    public int calculateMove(int[] diceValues) {
        int min = Integer.MAX_VALUE;
        for(int value : diceValues) {
            min = Math.min(min, value);
        }
        return min;
    }
}
