import org.example.GameConfig;
import org.example.GameConfigReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class GameConfigReaderTest {
    @Test
    void testReadGameConfig() {
        String fileName = "/Users/divanshujoshi/Documents/SnakesGame/SnakesAndLadder/src/main/java/org/example/test_config.json"; // Provide the path to your JSON file
        GameConfig gameConfig = GameConfigReader.readGameConfig(fileName);

        assertNotNull(gameConfig);
        assertEquals(10, gameConfig.getBoardSize());
        assertEquals(1, gameConfig.getNumberOfDice());
        assertEquals("min", gameConfig.getMovementStrategy());
        assertEquals(2, gameConfig.getPlayers().size());
        assertEquals(1, gameConfig.getNumberOfMines());
        assertEquals(1, gameConfig.getNumberOfCrocodiles());
    }

}
