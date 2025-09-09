import java.util.*;


public class Main{

  static final int MAX_INIT = 40;

  public static void main(String[] args) {
    //create hashmap
    HashMap<String, Integer> initiative = new HashMap<String, Integer>();
    //initialize scanner
    System.out.println("---------------------------------------");
    Scanner scanner = new Scanner(System.in);
    //get initial player count
    System.out.print("Enter number of entities (all enemies will be a single entity): ");
    int entCount = scanner.nextInt();
    scanner.nextLine();
    initiative = enterPlayerInfo(entCount, scanner);

    checkEnemy(scanner, initiative);

    //close the scanner
    scanner.close();

  }





  public static void addEntity(String name, int initiative, HashMap<String, Integer> map){
    map.put(name, initiative);
  }




  public static void checkEnemy(Scanner scan, HashMap<String, Integer> map){
    boolean enem = false;
    int check = 0;
    while(check == 0){
      System.out.println("Are there enemies in this encounter? Y/N ");
      char enc = scan.next().charAt(0);
      scan.nextLine();
      if(enc == 'Y'){
        enem = true;
        check = 1;
      }
      else if(enc == 'N'){
        enem = false;
        check = 1;

      }
      else{
        System.out.println("Unable to use input");
      }
    }

    if(enem == true){
      int enemCheck = 1;
      int enemyCount = 0;
      int enemyInit = 0;
      while(enemCheck == 1){
          
        System.out.println("add enemy amount and initiative: ");
        enemyCount = scan.nextInt();
        scan.nextLine();
        enemyInit = scan.nextInt();
        scan.nextLine();
        if((enemyCount > 0 && enemyInit > 0) && enemyInit <= 40){
          enemCheck = 0;
        }
        else{
          System.out.println("Inivalid enemy information, please re-enter");
        }
      }
      addEnemy(enemyInit,enemyCount, map);

    }
    else{
      sortInit(map);
    }
  }





  public static void addEnemy(int enemyInit, int enemyCount, HashMap<String, Integer> initiative){

    int ini = enemyInit;
    for(int i = 0; i < enemyCount; i++){
      String enemyName = "enemy" + (i + 1);
      addEntity(enemyName, ini, initiative);
    }

    sortInit(initiative);

  }






  //repeat for number of entities to retrieve names and initiative rolls
  public static HashMap<String, Integer> enterPlayerInfo(int entCount, Scanner scan){
    HashMap<String, Integer> ents = new HashMap<String, Integer>();
    int cont = entCount;
      while(cont > 0){
        System.out.println("input name and initiative:" );
        String name = scan.nextLine();
        if(ents.containsKey(name)){
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
  public static List<Map.Entry<String, Integer>> sortInit(HashMap<String, Integer> map){
    List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>> (map.entrySet());
    list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    if(list.isEmpty()){
      return null;
    }

    System.out.println("/-----------------Current Order--------------------/");
    for (Map.Entry<String, Integer> entry : list){
      System.out.println(entry.getKey() + " " + entry.getValue());
    }


    return list;

  }

  public static void sortRotate(List<Map.Entry<String, Integer>> list){
    
    if(list.isEmpty() || list == null ){
      System.out.println("no initiative");
    }

    Map.Entry<String, Integer> first = list.remove(0);
      list.add(first);


    System.out.println("/-----------------Current Order--------------------/");
    for (Map.Entry<String, Integer> entry : list){
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }


}