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
        list.add(new Entity(name, initiative, hitP));
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
            if(enc == 'Y'){
                addEnemy(scan, list, game);
                break;
            }else if(enc == 'N'){
                break;
            }else if(enc != 'Y' && enc != 'N'){
                System.out.println("Invalid input, response must be Y or N.");
            }
   
        }
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
        System.out.println("Would you like to name this enemy (Y/N)? ");
        char response = scan.next().toUpperCase().charAt(0);
        scan.nextLine();
        while(true){

            if(response == 'Y'){
                System.out.println("Enter name of enemy: ");
                name = scan.nextLine();
                System.out.println("Enter hit points for this enemy: ");
                hitP = scan.nextInt();
                scan.nextLine();
                addEntity(name, enemyInit, hitP, list);
                game.incrementEn();

                break;
            }
            else if(response == 'N'){
                game.incrementEn();
                name = "Enemy" + game.getEn();
                System.out.println("Enter hit points for this enemy: ");
                hitP = scan.nextInt();
                scan.nextLine();
                addEntity(name, enemyInit, hitP, list); 

                break;
            }
            else{
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
            System.out.println("add enemy amount and initiative (eg. 2, 14): ");
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
                if(response == 'Y'){
                    for(int i = 0; i < enemyCount; i++){
                        System.out.println("Enter hit points for enemy " + (i + 1) + ": ");
                        hitP = scan.nextInt();
                        scan.nextLine();
                        game.incrementEn();
                        String name = "Enemy" + game.getEn();
                        addEntity(name, enemyInit, hitP, list);
                    }
                    break;
                }else if(response == 'N'){
                    System.out.println("Enter hit points for all enemies: ");
                    hitP = scan.nextInt();
                    scan.nextLine();
                    for(int i = 0; i < enemyCount; i++){
                        game.incrementEn();
                        String name = "Enemy" + game.getEn();
                        addEntity(name, enemyInit, hitP, list);  
                    }
                    break;
                }else{
                    System.out.println("Invalid input, response must be Y or N.");
                }

            }
        }
        ManageEncounter.sortInit(list);
    }
}
