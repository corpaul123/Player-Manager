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
 * Prompt user to enter the number of players participating in encounter.
 * @param scan Scanner instance for input
 * @return amount of players present
 */
    public static int playAmount(Scanner scan){
        int count = 0;
        while(true){
            System.out.print("Enter number of players: ");
            try{
                count = Integer.parseInt(scan.nextLine());
                if(count > 0){
                    break;
                }
                else{
                    System.out.println("Invalid input, there must be at least one player for an encounter.");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter a valid integer.");

            }
        }
        return count;
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
            String name = "";
            int init = 0;
            while(true){
                System.out.println("Input player name:" );
                String input = scan.nextLine();
                boolean exist = ents.stream().anyMatch(e -> e.getName().equalsIgnoreCase(input));
                if(exist){
                    System.out.println("Player has already been entered, please enter a different one.");
                    continue;
                }
                else{
                    name = input;
                    break;
                }
            }

            while(true){
                try{
                    System.out.println("Input player initiative:" );
                    init = Integer.parseInt(scan.nextLine());
                    if(init > 0 && init <= game.getMaxInit()){
                        break;
                    }
                    else{
                        System.out.println("Invalid initiative, must be between 1 and 40");
                        continue;
                    }
                }catch(NumberFormatException e){
                    System.out.println("Invalid input, please enter valid integer.");
                }
            }
            addEntity(name, init, ents);
            cont--;
        }
        return ents;

    }

}