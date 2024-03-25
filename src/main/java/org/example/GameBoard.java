package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Data
@AllArgsConstructor
public class GameBoard {
    private int coolDown;
    private int numberOfDice;
    private int boardSize;
    private Map<BoardElement, Integer> snakes;
    private Map<BoardElement, Integer> ladders;
    private Map<BoardElement, SpecialObjectType> specialObjects;
    private Random random;

    public GameBoard(GameConfig gameConfig) {
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.specialObjects = new HashMap<>();
        this.boardSize = gameConfig.getBoardSize();
        this.numberOfDice = gameConfig.getNumberOfDice();
        this.random = new Random();
        this.coolDown = 2;
        placeSnakesAndLadders(gameConfig);
        placeCrocodiles(gameConfig);
        placeMines(gameConfig);
    }

    private void placeSnakesAndLadders(GameConfig gameConfig) {
        // Place snakes
        for (int i = 0; i < gameConfig.getNumberOfSnakes(); i++) {
            int headRow = random.nextInt(gameConfig.getBoardSize());
            int headColumn = random.nextInt(gameConfig.getBoardSize());
            int tailRow = random.nextInt(headRow + 1); // Ensure tail is below or at the same row as the head
            int tailColumn = random.nextInt(gameConfig.getBoardSize());

            // If tailRow becomes 0, set it to 1 to ensure it's not the same as the head
            if (tailRow == 0) {
                tailRow = 1;
            }

            BoardElement head = new BoardElement(headRow, headColumn);
            BoardElement tail = new BoardElement(tailRow, tailColumn);
            snakes.put(head, tail.getRow() * gameConfig.getBoardSize() + tail.getColumn());
        }

        // Place ladders
        for (int i = 0; i < gameConfig.getNumberOfLadders(); i++) {
            int bottomRow = random.nextInt(gameConfig.getBoardSize() - 1); // Ensure bottom is above the top
            int bottomColumn = random.nextInt(gameConfig.getBoardSize());
            int topRow = random.nextInt(gameConfig.getBoardSize());
            int topColumn = random.nextInt(gameConfig.getBoardSize());
            BoardElement bottom = new BoardElement(bottomRow, bottomColumn);
            BoardElement top = new BoardElement(topRow, topColumn);
            ladders.put(bottom, top.getRow() * gameConfig.getBoardSize() + top.getColumn());
        }

    }

    private void placeCrocodiles(GameConfig gameConfig) {
        // Place crocodiles
        for (int i = 0; i < gameConfig.getNumberOfCrocodiles(); i++) {
            int crocodileRow = random.nextInt(gameConfig.getBoardSize());
            int crocodileColumn = random.nextInt(gameConfig.getBoardSize());
            BoardElement crocodileLocation = new BoardElement(crocodileRow, crocodileColumn);
            specialObjects.put(crocodileLocation, SpecialObjectType.CROCODILE);
        }
    }


    private void placeMines(GameConfig gameConfig) {

        // Place mines
        for (int i = 0; i < gameConfig.getNumberOfMines(); i++) {
            int mineRow = random.nextInt(gameConfig.getBoardSize());
            int mineColumn = random.nextInt(gameConfig.getBoardSize());
            BoardElement mineLocation = new BoardElement(mineRow, mineColumn);
            specialObjects.put(mineLocation, SpecialObjectType.MINE);
        }
    }




    public BoardElement getPositionFromIndex(int index) {
        int row = index / boardSize;
        int column = index % boardSize;
        return new BoardElement(row, column);
    }
}
