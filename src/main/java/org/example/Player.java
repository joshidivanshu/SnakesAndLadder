package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.movementStrategy.MaxMovementStrategy;
import org.example.movementStrategy.MinMovementStrategy;
import org.example.movementStrategy.MovementStrategy;
import org.example.movementStrategy.SumMovementStrategy;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private BoardElement currentPosition;
    private MovementStrategy movementStrategy;
    private int mineCooldown;
}
