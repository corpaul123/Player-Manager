import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManageEncounter {

/**
* Sort the list in descending order.
* @param list the list of entities to be sorted
* @return sorted list of entities based on initiative 
*/ 
    public static List<Entity> sortInit(List<Entity> list){
        list.sort((e1, e2) -> Integer.compare(e2.getInit(), e1.getInit()));

        if(list.isEmpty()){
            return null;
        }
        return list;
    }

/**
* Print the current initiative order in a unified format.
* @param list the list of initialized entities to be printed
*/
    public static void printOrder(List<Entity> list){
        System.out.println("/-----------------Current Order--------------------/");
        for (Entity entry : list){
            System.out.println(entry.toString());
        }
    }

/**
* Rotate the initiative order to the current entity taking action. 
* @param list list of entities to be rotated
*/ 
    public static void sortRotate(List<Entity> list){
        
        if( list == null || list.isEmpty()){
            System.out.println("no initiative");
            return;
        }
        Entity first = list.remove(0);
        list.add(first);
    }

/**
 *  Display the encounter options.
 */
    public static void displayEncounter(){
        System.out.println("Actions: ");
        System.out.println("End encounter: 1");
        System.out.println("Remove enemy health: 2");
        System.out.println("Increase enemy health: 3");
        System.out.println("Add more enemies: 4");
        System.out.println("No action: 0");
    }

/**
 * Prompt the user to enter the name of the desired enemy, and verifies that the enemy exists.
 * @param list a list of initialized entities
 * @param scan Scanner instance for input
 * @param game game instance for initiative and enemy count
 * @return name of enemy for further processes
 */
    public static Optional<Entity> enemyHelper(List<Entity> list, Scanner scan, GameState game){

        Optional<Entity> enemOpt;
        do{


            System.out.println("Enter enemy name:");
            String enem = scan.nextLine().trim();
            enemOpt = list.stream().filter(e -> e.getName().equalsIgnoreCase(enem)).findFirst();
            if(!enemOpt.isPresent()){
                System.out.println("Enemy was not found. Please try again.");
            }
        }while(!enemOpt.isPresent());
        return enemOpt;
    }

/**
 * Prompt the user to enter amount of hit points to be removed from an enemy.
 * @param scan Scanner instance for input
 * @return amount of hit points to be removed (damaged)
 */
    public static int damageHelper(Scanner scan){
        int damage = 0;
        while(true){
            try{
                System.out.println("Enter amount of damage: ");
                damage = Integer.parseInt(scan.nextLine());
                if(damage > 0){
                    break;
                }

            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integer.");
            }

        }
        return damage;

    }
/**
 * Prompt user to enter amount of hit points to heal an enemy.
 * @param scan Scanner instance for input
 * @return amount of hit points to be added (healed)
 */
    public static int healthHelper(Scanner scan){
        int health = 0;
        while(true){
            try{
                System.out.println("Enter hit points healed: ");
                health = Integer.parseInt(scan.nextLine());
                if(health > 0){
                    break;
                }

            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integer.");
            }

        }

        return health;
    }

/**
 * Prompt user to enter amount of hit points to hurt an enemy.
 * @param list a list of initialized entities
 * @param scan Scanner instance for input
 * @param game game instance for initiative and enemy count
 */
    public static void hurtEnemy(List<Entity> list, Scanner scan, GameState game){
        if(game.getEn() > 0){
            Optional<Entity> enemOpt = enemyHelper(list, scan, game);
            int damage = damageHelper(scan);
            if(enemOpt.isPresent()){
                Entity enemy = enemOpt.get();
                enemy.takeDamage(damage);

            if(enemy.getHP() <= 0){
                list.remove(enemy);
                System.out.println(enemy + " has been defeated");
                game.decrementEn();
            }
        
            }else{
            System.out.println("Invalid enemy name");
            }
        }else{
            System.out.println("Unable to complete action, no enemies present.");
        }
    }

/**
 * 
 * @param list a list of initialized entities
 * @param scan Scanner instance for input
 * @param game game instance for initiative and enemy count
 */
    public static void healEnemy(List<Entity> list, Scanner scan, GameState game){
        if(game.getEn() > 0){
            Optional<Entity> enemOpt = enemyHelper(list, scan, game);

            int health = healthHelper(scan);

            if(enemOpt.isPresent()){
                Entity enemy = enemOpt.get();
                enemy.moreHealth(health);
        
            }else{
                System.out.println("Invalid enemy name");
            }
        }else{
            System.out.println("Unable to complete action, no enemies present.");
            printOrder(list);
        }

    }

/** 
* Allow user to define operations during each round.
* User is able to end the encounter manually, allowing encounters to continue or end based on more dynamic factors.
* User can remove health from a specific enemy, which will remove enemy from encounter when they are defeated.
* User can add more enemies to encounter, allowing encounters to be more dynamic.
* User can add more health to enemies, allowing them to heal.
* User can continue encounter without action. 
* @param list a list of initialized entities
* @param scan Scanner instance for input
* @param game game instance for initiative and enemy count
**/
    public static void runEncounter(List<Entity> list, Scanner scan, GameState game){

        while(true){
            displayEncounter();
            int response = Integer.parseInt(scan.nextLine());
            if(response == 1 || response == 2 || response == 3 || response == 4 || response == 0 ){
                if(response == 1){
                    System.out.println("Ending Encounter");
                    break;
                }else if (response == 2) {
                    hurtEnemy(list, scan, game);
                }else if(response == 3){
                    healEnemy(list,scan, game);
                }else if(response == 4){
                    ManageEntity.addEnemy(scan, list, game);
                }else if(response == 0){
                    System.out.println("No Action");
                    sortRotate(list);
                }
            }else{
                System.out.println("Invalid response, must be 1, 2, 3, 4 or 0");
            }
            printOrder(list);
            
        }
    }
}

