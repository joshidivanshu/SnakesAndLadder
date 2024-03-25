package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardElement {
    private int row;
    private int column;

    @Override
    public String toString() {
        return String.valueOf(row * 10 + column);
    }
}
