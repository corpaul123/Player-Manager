import java.util.*;


public class Main{

  public static void addEnemy(int enemyInit, int enemyCount, HashMap<String, Integer> initiative){

    int ini = enemyInit;
    for(int i = 0; i < enemyCount; i++){
      initiative.put("enemy" + (i + 1), ini);
    }

    sort(initiative);



  }






  //repeat for number of entities to retrieve names and initiative rolls
  public static HashMap<String, Integer> EnterInfo(int entCount){
    HashMap<String, Integer> ents = new HashMap<String, Integer>();
    int cont = entCount;
    Scanner scan = new Scanner(System.in);
      while(cont > 0){

        System.out.println("input name and initiative:" );
        String name = scan.nextLine();
        int init = scan.nextInt();
        scan.nextLine();
        //System.out.println("name: " + name + " initiative: " + init);
        if(init > 0 && init <= 40){
          ents.put(name, init);
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
  public static void sort(HashMap<String, Integer> initiative){
    List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>> (initiative.entrySet());

    //sorts map in ascending order based on number value
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      public int compare(Map.Entry<String, Integer> o1, 
        Map.Entry<String, Integer> o2) {
      return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    //puts sorted elements in duplicate map
    HashMap<String, Integer> dupe = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> aa : list){
      dupe.put(aa.getKey(), aa.getValue());
    }

    //reverses list of keys in reverse order for descending
    List<String> alKeys = new ArrayList<String>(dupe.keySet());
    Collections.reverse(alKeys);

    //prints out reordered map through iteration
    for(String key : alKeys){
      System.out.println(key + " " + initiative.get(key));
    }


  }






  public static void main(String[] args) {
    //create hashmap
    HashMap<String, Integer> initiative = new HashMap<String, Integer>();
    boolean enem = false;

    //initialize scanner
    System.out.println("---------------------------------------");
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter number of entities (all enemies will be a single entity): ");
    int entCount = scanner.nextInt();
    scanner.nextLine();
    initiative = EnterInfo(entCount);
    
    //System.out.println(entCount);
    int check = 0;
    while(check == 0){
      System.out.println("Are there enemies in this encounter? Y/N ");
      char enc = scanner.next().charAt(0);
      scanner.nextLine();
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
        enemyCount = scanner.nextInt();
        scanner.nextLine();
        enemyInit = scanner.nextInt();
        scanner.nextLine();
        if((enemyCount > 0 && enemyInit > 0) && enemyInit <= 40){
          enemCheck = 0;
        }
        else{
          System.out.println("Inivalid enemy information, please re-enter");
        }
      }
      addEnemy(enemyInit,enemyCount, initiative);

    }
    else{
      sort(initiative);
    }

    //close the scanner
    scanner.close();

  }
}