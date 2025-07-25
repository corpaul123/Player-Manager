import java.util.*;
import java.lang.*;

public class Main{

  public void EnterEnemy(int enemInit){

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
    int enemInit;

    //initialize scanner
    System.out.println("---------------------------------------");
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter number of entities (all enemies will be a single entity): ");
    int entCount = scanner.nextInt();
    scanner.nextLine();

    //System.out.println(entCount);



    //repeat for number of entities to retrieve names and initiative rolls
    for(int i = 0; i < entCount; i++){

      System.out.println("input name and initiative:" );
      String name = scanner.nextLine();
      int init = scanner.nextInt();
      scanner.nextLine();
      System.out.println("name: " + name + " initiative: " + init);
      initiative.put(name, init);

      
    }
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
      System.out.println("To be added");
    }
    else{
      sort(initiative);
    }

    System.out.println(initiative);
    //close the scanner
    scanner.close();

  }
}