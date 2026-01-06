
package src;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import src.Entity;
import src.ManagePlayer;

public class TestAssertions {
    @Test
    public void testPlayerCount() {
        List<Entity> list = new ArrayList<>();
        ManagePlayer.playerInitAdd("Boris", 14, list);

        int expectedSize = 1;
        int actualSize = list.size();

        assertEquals(expectedSize, actualSize);

    }
}