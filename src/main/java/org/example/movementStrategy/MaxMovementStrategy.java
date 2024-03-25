package org.example.movementStrategy;

public class MaxMovementStrategy implements MovementStrategy {
    @Override
    public int calculateMove(int[] diceValues) {
        int max = Integer.MIN_VALUE;
        for (int value : diceValues) {
            max = Math.max(max, value);
        }
        return max;
    }
}
