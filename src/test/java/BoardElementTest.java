import org.example.BoardElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardElementTest {
    @Test
    void testToString() {
        BoardElement boardElement = new BoardElement(2, 3);
        assertEquals("23", boardElement.toString());
    }
}
