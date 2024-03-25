package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameConfigReader {
    public static GameConfig readGameConfig(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(fileName);

        try {
            GameConfig config = objectMapper.readValue(file, GameConfig.class);
            List<Player> players = config.getPlayers();
            System.out.println("Player size " + players.size());
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or throw a custom exception
        }
    }
}
