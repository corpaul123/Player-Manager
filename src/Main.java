import java.util.*;

public class Main{

/** 
* Entry Point: manages player amount, setup, and running the encounter loop. 
**/
  public static void main(String[] args) {
    List<Entity> ents = new ArrayList<>();
    GameState game = new GameState();
    Scanner scanner = new Scanner(System.in);
    int entCount;

    System.out.println("---------------------------------------");

    entCount = ManagePlayer.playAmount(scanner);

    ents = ManagePlayer.enterPlayerInfo(entCount, scanner, game);

    ManageEntity.checkEnemy(scanner, ents, game);
    ManageEncounter.sortInit(ents);
    ManageEncounter.printOrder(ents);
    ManageEncounter.runEncounter(ents, scanner, game);

    scanner.close();

  }
}