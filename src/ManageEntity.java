import java.util.List;
import java.util.Scanner;

public class ManageEntity {

/**
* Add entity to the Entity list, including their hit points.
* @param name  name of the entity to be added
* @param initiative  the initiative of the entity being added
* @param hitP  the hit points of the entity being added
* @param list  the list entities to which entities will be added
*/
    public static void addEntity(String name, int initiative, int hitP, List<Entity> list){
        list.add(new Entity(name, initiative, hitP, true));
    }


/**
 * Prompt the user to specify whether enemies will be part of the encounter. 
 * @param scan Scanner instance for input
 * @param list list of entities to be updated
 * @param game instance for initiative and enemy count
 */
    public static void checkEnemy(Scanner scan, List<Entity> list, GameState game){

        while(true){
            System.out.println("Are there enemies in this encounter? Y/N ");
            char enc = scan.next().toUpperCase().charAt(0);
            scan.nextLine();
            switch(enc){
                case 'Y':
                    addEnemy(scan, list, game);
                    return;
                case 'N':
                    return;
                default:
                    System.out.println("Invalid input, response must be Y or N.");
            }
    
        }
    }

/**
 * Evaluates what number to use to name enemies based on other enemies present in the list.
 * @param list of entities to use for comparison of names
 * @return number to be used to name new enemy
 */
    public static int enemyAutoName(List<Entity> list){
        return list.stream()
        .map(Entity::getName)
        .filter(n -> n.startsWith("Enemy"))
        .map(n-> Integer.parseInt(n.substring(5)))
        .max(Integer::compare)
        .orElse(0) + 1;
    }

/**
 * Prompt user to enter enemy hit points. 
 * @param scan Scanner instance for input
 * @param list list list of entities to be updated
 * @param name name of enemy being given hit points
 * @return enemy hit points 
 */
    public static int enemyHpManager(Scanner scan, List<Entity> list, String name){
        int hitPoints = 0;
        while(true){
            try{
                System.out.println("Enter maximum hit points for " + name + ": ");
                hitPoints = Integer.parseInt(scan.nextLine());
                if(hitPoints > 0){
                    break;
                }

            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integer.");
            }

        }


        return hitPoints;
    }

/**
* Prompt user to enter a unique name for the enemy and its HP.
* @param scan Scanner instance for input
* @param list a list of initialized entities
* @param int enemy initiative for sorting
* @param game game instance for initiative and enemy count
*/
    public static void nameEnemy(Scanner scan, List<Entity> list, int enemyInit, GameState game){
        int hitP;
        String name;
        while(true){
            System.out.println("Would you like to name this enemy (Y/N)? ");
            char response = scan.next().toUpperCase().charAt(0);
            scan.nextLine();
            switch(response){
                case 'Y':
                    System.out.println("Enter name of enemy: ");
                    name = scan.nextLine();
                    hitP = enemyHpManager(scan, list, name);
                    addEntity(name, enemyInit, hitP, list);
                    game.incrementEn();
                    return;
                case 'N':
                    int numNext = enemyAutoName(list);
                    
                    name = "Enemy" + numNext;
                    hitP = enemyHpManager(scan, list, name);
                    addEntity(name, enemyInit, hitP, list); 
                    game.incrementEn();

                    return;
                default:
                    System.out.println("Invalid input, response must be Y or N.");
            }
        }
    }



/**  
* Prompt user to input enemy amount and initiative.
* Prompt user to define whether enemies will have individually-defined hit points or all use the same max hit points. 
* @param scan Scanner instance for input
* @param list a list of initialized entities
* @param game game instance for initiative and enemy count
*/
    public static void addEnemy(Scanner scan, List<Entity> list, GameState game){
        int enemyInit, enemyCount, hitP;

        while(true){
            System.out.println("Add enemy amount and initiative (eg. 2, 14): ");
            String input = scan.nextLine();

            String[] parseInput = input.split("\\s+");
            if(parseInput.length != 2){
                System.out.println("Invalid input, input must be two numbers: enemy count and initiative.");
                continue;
            }

            try{
                enemyCount = Integer.parseInt(parseInput[0]);
                enemyInit = Integer.parseInt(parseInput[1]);
                if((enemyInit > game.getMaxInit() || enemyInit < 0) || (enemyCount <= 0 || enemyCount > 40)){
                    System.out.println("Invalid input, initiative or enemy count are not in valid range.");
                }
                else{
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integers.");
            }

        }
        if(enemyCount == 1){
            nameEnemy(scan, list, enemyInit, game);
        }else{

            while(true){
                System.out.println("Will every enemy have their own HP? Y/N: ");
                char response = scan.next().toUpperCase().charAt(0);
                scan.nextLine();
                switch(response){
                    case 'Y':
                        System.out.println("Entering hit points individually: ");
                        for(int i = 0; i < enemyCount; i++){
                            int numNext = enemyAutoName(list);
                            String name = "Enemy" + numNext;
                            hitP = enemyHpManager(scan, list, name);
                            addEntity(name, enemyInit, hitP, list);
                            game.incrementEn();
                        }
                        
                        return;
                    case 'N':
                        hitP = enemyHpManager(scan, list, "all enemies");
                        for(int i = 0; i < enemyCount; i++){
                            String name = "Enemy" + enemyAutoName(list);
                            addEntity(name, enemyInit, hitP, list);  
                            game.incrementEn();
                        }
                        return;
                    default:
                        System.out.println("Invalid input, response must be Y or N.");
                }

            }
        }
    }

}
