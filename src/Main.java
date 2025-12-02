import java.util.*;

public class Main{

/**
 * Entry point of the program. 
 * Initializes the game and starts the encounter setup.
 *
 * @param args command-line arguments (not used).
 */
  public static void main(String[] args) {
    List<Entity> ents = new ArrayList<>();
    GameState game = new GameState();
    Scanner scanner = new Scanner(System.in);
    int entCount;

    System.out.println("---------------------------------------");

    entCount = ManagePlayer.playAmount(scanner);

    ents = ManagePlayer.enterPlayerInfo(entCount, scanner, game);

    ManageEntity.checkEnemy(scanner, ents, game);
    ManageEncounter.printOrder(ents, game);
    ManageEncounter.runEncounter(ents, scanner, game);

    scanner.close();

  }
}