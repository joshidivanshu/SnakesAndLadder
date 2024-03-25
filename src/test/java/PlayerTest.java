import org.example.BoardElement;
import org.example.GameBoard;
import org.example.GameConfig;
import org.example.Player;
import org.example.movementStrategy.SumMovementStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void testMoveWithValidDiceValues() {
        Player player = new Player("Player 1", new BoardElement(0, 0), null, 0);

        GameBoard gameBoard = new GameBoard(10,1);
        String movementStrategyValue = "sum";
        int[] diceValues = {2};

        player.move(diceValues, gameBoard, movementStrategyValue);

        // Check if the player moved to the correct position
        assertEquals(new BoardElement(0, 2), player.getCurrentPosition(), "Player should move to the correct position");

    }

}
