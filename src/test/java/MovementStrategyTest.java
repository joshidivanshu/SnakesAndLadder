import org.example.movementStrategy.MaxMovementStrategy;
import org.example.movementStrategy.MinMovementStrategy;
import org.example.movementStrategy.MovementStrategy;
import org.example.movementStrategy.SumMovementStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementStrategyTest {

    @Test
    void testMaxMovementStrategy() {
        MovementStrategy movementStrategy = new MaxMovementStrategy();
        int[] diceValues = {1, 3 ,5};
        assertEquals(5, movementStrategy.calculateMove(diceValues), "MaxMovementStrategy should return the maximum value of dice");
    }

    @Test
    void testMinimumMovementStrategy() {
        MovementStrategy movementStrategy = new MinMovementStrategy();
        int[] diceValues = {1, 3, 5};
        assertEquals(1, movementStrategy.calculateMove(diceValues), "Minmovement strategy should return the minimum value of dice");
    }

    @Test
    void testSumMovementStrategy() {
        MovementStrategy movementStrategy = new SumMovementStrategy();
        int[] diceValues ={1, 3, 5};
        assertEquals(9, movementStrategy.calculateMove(diceValues), "Summovement strategy should return the sum of values in dice");
    }
}
