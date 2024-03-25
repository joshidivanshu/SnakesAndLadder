package org.example.movementStrategy;

public class SumMovementStrategy implements MovementStrategy{
    @Override
    public int calculateMove(int[] diceValues) {
        int sum = 0;
        for (int value : diceValues) {
            sum += value;
        }
        return sum;
    }
}
