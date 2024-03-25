import org.example.GameBoard;
import org.example.GameConfig;
import org.example.ManualOverrideHandler;
import org.example.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ManualOverrideHandlerTest {
    @Test
    void testSetPlayerStartingLocations() {
        // Create mock player list and game board
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        GameBoard gameBoard = new GameBoard(new GameConfig());
        gameBoard.setBoardSize(10);

        // Prepare input for setting player starting locations
        String input = "1\n" +     // Select Set Player Starting Locations
                "1\n1\n";   // Player 1 starting position (row = 1, column = 1)

        // Set the input stream to the prepared input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Perform manual override
        ManualOverrideHandler.performManualOverride(players, gameBoard, scanner);

        // Check if the player's starting location is set correctly
        assertEquals(1, players.get(0).getCurrentPosition().getRow());
        assertEquals(1, players.get(0).getCurrentPosition().getColumn());
    }
}
