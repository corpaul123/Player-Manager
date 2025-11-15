import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagePlayer {

/**
* Add entity to the Entity list.
* @param name  name of the entity to be added
* @param initiative  the initiative of the entity being added
* @param list  the list entities to which entities will be added
*/
    public static void addEntity(String name, int initiative, List<Entity> list){
        list.add(new Entity(name, initiative));
    }

/**
* Prompt user for player name and initiative. 
* @param entCount the number of players in the encounter
* @param scan Scanner instance for input
* @param game instance for initiative and enemy count
* @return list of the player entities with their information
*/ 
    public static List<Entity> enterPlayerInfo(int entCount, Scanner scan, GameState game){
        List<Entity> ents = new ArrayList<>();
        int cont = entCount;
        while(cont > 0){
            System.out.println("input name and initiative:" );
            String name = scan.nextLine();
            boolean exist = ents.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
            if(exist){
            System.out.println("Player has already been entered, please enter a different one.");
            continue;
            }
            int init = scan.nextInt();
            scan.nextLine();
            if(init > 0 && init <= game.getMaxInit()){
            addEntity(name, init, ents);
            cont--;
            }
            else{
            System.out.println("Invalid initiative, must be between 0 and 40");
            continue;
            }
        }
        return ents;

    }

}