package src;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManageEncounter {


/**
* Print the current initiative order in a unified format.
*
* @param list The list of initialized entities to be printed.
*/
    public static void printOrder(List<Entity> list, GameState game){
        System.out.println(" ");
        System.out.println("/-----------------Current Order--------------------/");
        int currentTurn = game.getTurnIn();
        int size = list.size();

        if(size == 0){
            return;
        }

        for(int i = 0; i < size; i++){
            Entity e = list.get((currentTurn + i) % size);
            System.out.println(e.toString());
        }
    }

/**
* Rotate the initiative order to the current entity taking action.
*
* @param list List of entities to be rotated.
* @param game Game instance for initiative and enemy count.
*/ 
    public static void sortRotate(List<Entity> list, GameState game){
        
        if( list == null || list.isEmpty()){
            System.out.println("no initiative");
            return;
        }

        int turn = game.getTurnIn();
        turn = (turn + 1) % list.size();
        game.setTurnIn(turn);
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
 * 
 * @param list A list of initialized entities.
 * @param scan Scanner instance for input.
 * @param game Game instance for initiative and enemy count.
 * @return Name of enemy for further processes.
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
            else if(!enemOpt.get().isEnemy()){
                System.out.println("This is not an enemy, please enter an enemy name.");
                enemOpt = Optional.empty();
            }
        }while(!enemOpt.isPresent());
        return enemOpt;
    }

/**
 * Prompt the user to enter amount of hit points to be removed from an enemy.
 * 
 * @param scan Scanner instance for input.
 * @return Amount of hit points to be removed (damaged), or -1 to cancel.
 */
    public static int damageHelper(Scanner scan){
        int damage = 0;
        while(true){
            try{
                System.out.println("Enter amount of damage, 0 to cancel: ");

                String input = scan.nextLine();
                if("0".equals(input)){
                    System.out.println("cancelling.");
                    return -1;
                }

                damage = Integer.parseInt(input);
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
 * 
 * @param scan Scanner instance for input.
 * @return Amount of hit points to be added (healed), or -1 to cancel.
 */
    public static int healthHelper(Scanner scan){
        int health = 0;
        while(true){
            try{
                System.out.println("Enter hit points healed, 0 to cancel: ");
                String input = scan.nextLine().trim();

                if(input.equals("0")){
                    System.out.print("Cancelling.");
                    return -1;
                }
                health = Integer.parseInt(input);
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
 * 
 * @param list A list of initialized entities
 * @param scan Scanner instance for input
 * @param game Game instance for initiative and enemy count
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
 * Prompt user to enter amount of hit points to heal an enemy.
 * 
 * @param list A list of initialized entities.
 * @param scan Scanner instance for input.
 * @param game Game instance for initiative and enemy count.
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
        }

    }

/**
 * Allows the user to define operations during each round.
 *
 * During each round, the user can:
 * - End the encounter manually.
 * - Remove health from a specific enemy, automatically removing them if defeated.
 * - Add more enemies to the encounter for a dynamic challenge.
 * - Add health to enemies to allow healing.
 * - Continue the encounter without taking any action.
 *
 * @param list A list of initialized entities.
 * @param scan Scanner instance for input.
 * @param game Game instance for initiative and enemy count.
 */

    public static void runEncounter(List<Entity> list, Scanner scan, GameState game){

        while(true){
            displayEncounter();
            try{
                int response = Integer.parseInt(scan.nextLine());
                switch(response){
                    case 1:
                        System.out.println("Ending Encounter");
                        return;
                    case 2:
                        hurtEnemy(list, scan, game);
                        break;
                    case 3:
                        healEnemy(list,scan, game);
                        break;
                    case 4:
                        ManageEntity.addEnemy(scan, list, game, true);
                        break;
                    case 0:
                        System.out.println("No Action");
                        sortRotate(list, game);
                        break;
                
                    default:
                        System.out.println("Invalid response, must be 1, 2, 3, 4 or 0");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integers.");
            }

            printOrder(list, game);
            
        }
    }
}

