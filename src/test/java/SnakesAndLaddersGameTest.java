import org.example.*;
import org.example.movementStrategy.MaxMovementStrategy;
import org.example.movementStrategy.MinMovementStrategy;
import org.example.movementStrategy.SumMovementStrategy;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakesAndLaddersGameTest {
    @Test
    public void testGameSimulationWithRandomDiceRolls() {
        // Setting up game configuration
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(1);
        gameConfig.setNumberOfSnakes(2);
        gameConfig.setNumberOfLadders(2);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1", new BoardElement(0, 0), new MaxMovementStrategy(), 0);
        players.add(player1);
        gameConfig.setPlayers(players);

        // Simulating game with random dice rolls
        String manualInput = "random\n";
        InputStream inputStream = new ByteArrayInputStream(manualInput.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});
    }

    @Test
    public void testGameAutoSimulationWithSpecialObjectMine() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(4);
        gameConfig.setNumberOfSnakes(2);
        gameConfig.setNumberOfLadders(2);
        gameConfig.setNumberOfMines(10);
        gameConfig.setNumberOfPlayers(2);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1", new BoardElement(0, 0), new MaxMovementStrategy(), 0);
        Player player2 = new Player("Player 2", new BoardElement(0, 1), new MaxMovementStrategy(), 0);
        players.add(player1);
        players.add(player2);
        gameConfig.setPlayers(players);

        String manualInput = "autoSim\n";
        InputStream inputStream = new ByteArrayInputStream(manualInput.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});
    }

    @Test
    public void testGameAutoSimulationWithSpecialObjectCrocodile() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(4);
        gameConfig.setNumberOfSnakes(2);
        gameConfig.setNumberOfLadders(2);
        gameConfig.setNumberOfCrocodiles(10);
        gameConfig.setNumberOfPlayers(2);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1", new BoardElement(0, 0), new MaxMovementStrategy(), 0);
        Player player2 = new Player("Player 2", new BoardElement(0, 1), new MaxMovementStrategy(), 0);
        players.add(player1);
        players.add(player2);
        gameConfig.setPlayers(players);

        String manualInput = "autoSim\n";
        InputStream inputStream = new ByteArrayInputStream(manualInput.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});
    }

    @Test
    public void testGameAutoSimulationWithSumMovementStrategy() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(4);
        gameConfig.setNumberOfSnakes(2);
        gameConfig.setNumberOfLadders(2);
        gameConfig.setNumberOfMines(10);
        gameConfig.setNumberOfCrocodiles(5);
        gameConfig.setNumberOfPlayers(2);
        List<Player> players = new ArrayList<>();
        for (int i  = 0; i < 9; i++) {
            Player temp = new Player("Player " + i, new BoardElement(0,0), new SumMovementStrategy(), 0);
            players.add(temp);
        }
        gameConfig.setPlayers(players);

        String manualInput = "autoSim\n";
        InputStream inputStream = new ByteArrayInputStream(manualInput.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});
    }

    @Test
    public void testGameAutoSimulationWithMinMovementStrategy() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setBoardSize(10);
        gameConfig.setNumberOfDice(4);
        gameConfig.setNumberOfSnakes(2);
        gameConfig.setNumberOfLadders(2);
        gameConfig.setNumberOfMines(10);
        gameConfig.setNumberOfCrocodiles(5);
        gameConfig.setNumberOfPlayers(2);
        List<Player> players = new ArrayList<>();
        for (int i  = 0; i < 9; i++) {
            Player temp = new Player("Player " + i, new BoardElement(0,0), new MinMovementStrategy(), 0);
            players.add(temp);
        }
        gameConfig.setPlayers(players);

        String manualInput = "autoSim\n";
        InputStream inputStream = new ByteArrayInputStream(manualInput.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});
    }

    @Test
    public void testManualOverrideSetPlayerStartingLocations() {
        // Mocking user input
        String input = "1\n" + // Select Set Player Starting Locations
                "1\n1\n" + // Player 1 starting position
                "2\n2\n";  // Player 2 starting position
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Setup test data
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", new BoardElement(0, 0), new MaxMovementStrategy(), 0));
        players.add(new Player("Player 2", new BoardElement(0, 0), new MaxMovementStrategy(), 0));
        GameBoard gameBoard = new GameBoard(new GameConfig());
        gameBoard.setBoardSize(10);

        // Perform manual override
        ManualOverrideHandler.performManualOverride(players, gameBoard, new Scanner(System.in));

        // Verify that the players' starting positions are set correctly
        assertEquals(new BoardElement(1, 1), players.get(0).getCurrentPosition());
        assertEquals(new BoardElement(2, 2), players.get(1).getCurrentPosition());
    }

    @Test
    public void testNormalSnakesLadderExecution() {
        GameBoard gameBoard = new GameBoard(10, 1);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Player player= new Player("Player" + (i + 1), new BoardElement(0, 0), null, 0);
            players.add(player);
        }
        String input = "normal\n" +
                "1\n" +
                "78 26\n" +
                "1\n" +
                "5 67\n" +
                "2\n" +
                "player1 0\n" +
                "player2 0\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        SnakesAndLaddersGame.main(new String[]{});

    }


}
