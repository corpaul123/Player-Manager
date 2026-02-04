
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;



public class Tests {
    @Test
    public void testPlayerCount() {
        List<Entity> list = new ArrayList<>();
        ManagePlayer.playerInitAdd("Boris", 14, list);

        int expectedSize = 1;
        int actualSize = list.size();

        assertEquals(expectedSize, actualSize);

    }

    @Test
    public void testEnemyCheck(){
        List<Entity> list = new ArrayList<>();
        GameState game = new GameState();
        String testInput = "Y\n"+
        "2, 14\n" +
        "N\n" +
        "25\n";
        Scanner scan = new Scanner(
            new ByteArrayInputStream(testInput.getBytes())
        );
        ManageEntity.checkEnemy(scan, list, game);

        int expectedSize = 2;
        int actualSize = list.size();

        assertEquals(expectedSize, actualSize);
    }
}