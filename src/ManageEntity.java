import java.util.List;
import java.util.Scanner;

public class ManageEntity {



/**
 * Inserts an enemy into the encounter list.
 * How the enemy is inserted changes based on whether on encounter is happening or not.
 * @param name name of the enemy being added
 * @param initiative initiative of the enemy being added
 * @param hitP the starting hit points of the enemy being added
 * @param list list of entities to be updated
 * @param game instance for initiative and enemy count
 */
    public static void initAddEnc(String name, int initiative, int hitP, 
        List<Entity> list, GameState game, boolean midEn){
        int i = 0;
        while(i < list.size() && list.get(i).getInit() > initiative){
            i++;
        }
        list.add(i, new Entity(name, initiative, hitP, true));

        if(midEn && i <= game.getTurnIn()){
            game.setTurnIn(game.getTurnIn() + 1);
        }


    }

/**
 * Prompt the user to specify whether enemies will be part of the encounter. 
 * Call addEnemy to insert enemy into the list.
 * Continue to the encounter without enemies.
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
                    addEnemy(scan, list, game, false);
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
 * @param list list of entities to be updated
 * @param name name of enemy being given hit points
 * @return enemy hit points, or -1 to cancel operation
 */
    public static int enemyHpManager(Scanner scan, List<Entity> list, String name){
        int hitPoints = 0;
        while(true){
            try{
                System.out.println("Enter maximum hit points for " + name + ", 0 to cancel: ");
                String input = scan.nextLine();
                if(input.equals("0")){
                    System.out.println("Cancelling.");
                    return -1;
                }
                hitPoints = Integer.parseInt(input);
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
 * Prompt user to enter a name manually.
 * @param scan Scanner instance for input
 * @param list list of entities to be updated
 * @param enemyInit initiative of current enemy
 * @param game game instance for initiative and enemy count
 * @param mid boolean to verify whether an encounter is in progress
 */
    public static void manualName(Scanner scan, List<Entity> list, int enemyInit, GameState game, boolean mid){
        int hitP;
        String name;
        System.out.println("Enter name of enemy: ");
        name = scan.nextLine();
        hitP = enemyHpManager(scan, list, name);
        initAddEnc(name, enemyInit, hitP, list, game, mid);
        game.incrementEn();

    }

/**
 * 
 * @param scan Scanner instance for input
 * @param list a list of initialized entities
 * @param enemyInit initiative of current enemy
 * @param game game instance for initiative and enemy count
 * @param mid boolean to verify whether an encounter is in progress
 */
    public static void handleAutoNaming(Scanner scan, List<Entity> list, int enemyInit, GameState game, boolean mid){
        String name;
        int hitP;
        int numNext = enemyAutoName(list);
        name = "Enemy" + numNext;
        hitP = enemyHpManager(scan, list, name);
        initAddEnc(name, enemyInit, hitP, list, game, mid); 
        game.incrementEn();
    }

/**
* Prompt user to enter a unique name for the enemy and its HP.
* @param scan Scanner instance for input
* @param list a list of initialized entities
* @param int enemy initiative for sorting
* @param game game instance for initiative and enemy count
*/
    public static void nameEnemy(Scanner scan, List<Entity> list, int enemyInit, GameState game, boolean mid){
        while(true){
            System.out.println("Would you like to name this enemy (Y/N)? 0 to cancel ");
            char response = scan.next().toUpperCase().charAt(0);
            scan.nextLine();
            switch(response){
                case 'Y':
                    manualName(scan, list, enemyInit, game, mid);
                    return;
                case 'N':
                    handleAutoNaming(scan, list, enemyInit, game, mid);
                    return;
                case '0':
                    System.out.print("Cancelling.");
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
    public static void addEnemy(Scanner scan, List<Entity> list, GameState game, boolean mid){
        int enemyInit, enemyCount, hitP;

        while(true){
            System.out.println("Add enemy amount and initiative (eg. 2, 14) 0 to cancel: ");
            String input = scan.nextLine().trim();

            if(input.equals("0")){
                System.out.println("Cancelling.");
                return;
            }
            String[] parseInput = input.split("[,\\s]+");
            if(parseInput.length != 2){
                System.out.println("Invalid input, input must be two numbers: enemy count and initiative.");
                continue;
            }

            try{
                enemyCount = Integer.parseInt(parseInput[0]);
                enemyInit = Integer.parseInt(parseInput[1]);
                if((enemyInit > game.getMaxInit() || enemyInit < 0)){
                    System.out.println("Invalid input, initiative is not in valid range.");
                }else if( (enemyCount <= 0 || enemyCount > 20)){
                    System.out.println("Invalid input, enemy count is not in valid range.");
                
                }else{
                    break;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid input, please enter valid integers.");
            }

        }
        if(enemyCount == 1){
            nameEnemy(scan, list, enemyInit, game, mid);
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
                            if(hitP == -1){
                                System.out.println("Cancelling enemy");
                                return;
                            }
                            initAddEnc(name, enemyInit, hitP, list, game, mid);
                            game.incrementEn();
                        }
                        
                        return;
                    case 'N':
                        hitP = enemyHpManager(scan, list, "all enemies");

                        if(hitP == -1){
                            System.out.println("Cancelling enemy");
                            return;                           
                        }
                        for(int i = 0; i < enemyCount; i++){
                            String name = "Enemy" + enemyAutoName(list);
                            initAddEnc(name, enemyInit, hitP, list, game, mid);  
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
