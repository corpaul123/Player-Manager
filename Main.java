import java.util.*;

public class Main{

/** 
* Entry Point: manage input, setup, and run the encounter loop. 
**/
  public static void main(String[] args) {
    List<Entity> ents = new ArrayList<>();
    GameState game = new GameState();
    System.out.println("---------------------------------------");
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter number of players: ");
    int entCount = scanner.nextInt();
    scanner.nextLine();
    ents = ManagePlayer.enterPlayerInfo(entCount, scanner, game);

    ManageEntity.checkEnemy(scanner, ents, game);
    ManageEncounter.sortInit(ents);
    ManageEncounter.printOrder(ents);
    ManageEncounter.runEncounter(ents, scanner, game);

    scanner.close();

  }
}