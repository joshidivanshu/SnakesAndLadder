import org.example.BoardElement;
import org.example.GameBoard;
import org.example.GameConfig;
import org.example.SpecialObjectType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardTest {
    @Test
    void testPlaceSnakesAndLadders() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(1);
        gameConfig.setNumberOfSnakes(3);
        gameConfig.setNumberOfLadders(2);
        gameConfig.setMovementStrategy("min");

        GameBoard gameBoard = new GameBoard(gameConfig);

        // Verify snakes are placed
        Map<BoardElement, Integer> snakes = gameBoard.getSnakes();
        assertEquals(3, snakes.size()); // Ensure the correct number of snakes are placed

        // Verify ladders are placed
        Map<BoardElement, Integer> ladders = gameBoard.getLadders();
        assertEquals(2, ladders.size()); // Ensure the correct number of ladders are placed
    }

    @Test
    void testPlaceCrocodiles() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(1);
        gameConfig.setNumberOfCrocodiles(2);
        gameConfig.setMovementStrategy("min");

        GameBoard gameBoard = new GameBoard(gameConfig);

        // Verify crocodiles are placed
        Map<BoardElement, SpecialObjectType> specialObjects = gameBoard.getSpecialObjects();
        assertEquals(2, specialObjects.size()); // Ensure the correct number of crocodiles are placed
        assertTrue(specialObjects.values().stream().allMatch(type -> type == SpecialObjectType.CROCODILE)); // Ensure all special objects are crocodiles
    }

    @Test
    void testPlaceMines() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(1);
        gameConfig.setNumberOfMines(2);
        gameConfig.setMovementStrategy("min");

        GameBoard gameBoard = new GameBoard(gameConfig);

        // Verify mines are placed
        Map<BoardElement, SpecialObjectType> specialObjects = gameBoard.getSpecialObjects();
        assertEquals(2, specialObjects.size()); // Ensure the correct number of mines are placed
        assertTrue(specialObjects.values().stream().allMatch(type -> type == SpecialObjectType.MINE)); // Ensure all special objects are mines
    }

    @Test
    void testGetPositionFromIndex() {
        int boardSize = 10;
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(boardSize);
        gameConfig.setNumberOfDice(1);
        gameConfig.setMovementStrategy("min");

        GameBoard gameBoard = new GameBoard(gameConfig);

        // Test a few index to position mappings
        Map<Integer, BoardElement> expectedPositions = new HashMap<>();
        expectedPositions.put(0, new BoardElement(0, 0));
        expectedPositions.put(10, new BoardElement(1, 0));
        expectedPositions.put(99, new BoardElement(9, 9));

        for (Map.Entry<Integer, BoardElement> entry : expectedPositions.entrySet()) {
            assertEquals(entry.getValue(), gameBoard.getPositionFromIndex(entry.getKey()));
        }
    }
}


