package org.example;


import lombok.Data;
import java.util.List;

@Data
public class GameConfig {
    private int numberOfPlayers;
    private int boardSize;
    private int numberOfSnakes;
    private int numberOfLadders;
    private int numberOfCrocodiles; // New field for the number of crocodiles
    private int numberOfMines;
    private int numberOfDice;
    private String movementStrategy;

    private List<Player> players;


}