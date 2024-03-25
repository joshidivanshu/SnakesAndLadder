import org.example.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    @Test
    void testRoll() {
        Dice dice = new Dice();

        for (int i = 0; i < 100; i++) {
            int result = dice.roll();
            assertTrue(result >= 1 && result <= 6,  "Dice roll result out of range [1, 6]: " + result);
        }
    }
}
