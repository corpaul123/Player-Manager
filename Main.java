import java.util.*;


public class Main{

  static final int MAX_INIT = 40;

  // main area-----------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    //create hashmap
    List<Entity> ents = new ArrayList<>();
    //initialize scanner
    System.out.println("---------------------------------------");
    Scanner scanner = new Scanner(System.in);
    //get initial player count
    System.out.print("Enter number of players: ");
    int entCount = scanner.nextInt();
    scanner.nextLine();
    ents = enterPlayerInfo(entCount, scanner);

    checkEnemy(scanner, ents);

    sortInit(ents);
    runEncounter(ents, scanner);

    //close the scanner
    scanner.close();

  }

  //add players------------------------------------------------------------------------------------------------------------

  public static void addEntity(String name, int initiative, List<Entity> list){
    list.add(new Entity(name, initiative));
  }

  public static void addEntity(String name, int initiative, int hitP, List<Entity> list){
    list.add(new Entity(name, initiative, hitP));
  }



//check if enemies will be added---------------------------------------------------------------------------------------
  public static void checkEnemy(Scanner scan, List<Entity> list){

      while(true){
        System.out.println("Are there enemies in this encounter? Y/N ");
        char enc = scan.next().toUpperCase().charAt(0);
        scan.nextLine();
        if(enc == 'Y'){
          addEnemy(scan, list);
          break;
        }else if(enc == 'N'){
          break;
        }else if(enc != 'Y' && enc != 'N'){
          System.out.println("Invalid input");
        }
   
      }
  }






//add Enemies-----------------------------------------------------------------------------------------------------

  public static void addEnemy(Scanner scan, List<Entity> list){
    int enemyInit, enemyCount, hitP;

    while(true){
      System.out.println("add enemy amount and initiative (eg. 2 14): ");
      enemyCount = scan.nextInt();
      enemyInit = scan.nextInt();
      scan.nextLine();

      if((enemyInit > MAX_INIT || enemyInit < 0) || (enemyCount <= 0 || enemyCount > 40)){
        System.out.println("Invalid input, please re-enter");

      }
      else{

        break;

      }

    }
    while(true){
      System.out.println("Will every enemy have their own HP? Y/N: ");
      char response = scan.next().toUpperCase().charAt(0);
      scan.nextLine();
        if(response == 'Y'){
          for(int i = 0; i < enemyCount; i++){
            System.out.println("Enter HP for enemy " + (i + 1) + ": ");
            hitP = scan.nextInt();
            scan.nextLine();
            String name = "Enemy" + (i + 1);
            addEntity(name, enemyInit, hitP, list);
          }
          break;
        }else if(response == 'N'){
          System.out.println("Enter Hp for all enemies: ");
          hitP = scan.nextInt();
          scan.nextLine();
          for(int i = 0; i < enemyCount; i++){
            String name = "Enemy" + (i + 1);
            addEntity(name, enemyInit, hitP, list);  
          }
          break;
          }
        else{
          System.out.println("Invalid input");
        }

        sortInit(list);
        
  
    }


  }








  //repeat for number of entities to retrieve names and initiative rolls
  public static List<Entity> enterPlayerInfo(int entCount, Scanner scan){
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
        //System.out.println("name: " + name + " initiative: " + init);
        if(init > 0 && init <= MAX_INIT){
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




  //method sorts the map
  public static List<Entity> sortInit(List<Entity> list){
    //List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>> (map.entrySet());
    list.sort((e1, e2) -> Integer.compare(e2.getInit(), e1.getInit()));

    if(list.isEmpty()){
      return null;
    }

    System.out.println("/-----------------Current Order--------------------/");
    for (Entity entry : list){
      System.out.println(entry.toString());
    }


    return list;

  }

  public static void sortRotate(List<Entity> list){
    
    if( list == null || list.isEmpty()){
      System.out.println("no initiative");
    }
      Entity first = list.remove(0);
      list.add(first);


    System.out.println("/-----------------Current Order--------------------/");
    for (Entity entry : list){
      System.out.println(entry.toString());
    }
  }

  public static void runEncounter(List<Entity> list, Scanner scan){

    int count = 1;
  while(count == 1){
    System.out.println("Actions(end encoutner: 1, remove enemy health: 2, no action: 0)");
    int response = scan.nextInt();
    scan.nextLine();

    if(response == 1 || response == 2 || response == 0 ){
        if(response == 1){
          System.out.println("Ending Encounter");
          count = 0;
        }
        else if (response == 2) {
            System.out.println("Enter enemy and amount of damage");
            String enem = scan.nextLine();
            int damage = scan.nextInt();
            scan.nextLine();
            Optional<Entity> enemOpt = list.stream().filter(e -> e.getName().equalsIgnoreCase(enem)).findFirst();
            if(enemOpt.isPresent()){
              Entity enemy = enemOpt.get();
              enemy.takeDamage(damage);

              if(enemy.getHP() <= 0){
                list.remove(enemy);
                System.out.println(enemy + " has been defeated");
              }
              
            }else{
              System.out.println("Invalid enemy name");
            }

            sortRotate(list);
        }
        else if(response == 0){
          System.out.println("No Action");
          sortRotate(list);
        }
        else{
          System.out.println("Invalid response, must be 1, 2, or 0");
        }
      

    }
  }

  }


}