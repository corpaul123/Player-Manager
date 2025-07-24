import java.util.Scanner;
import java.util.HashMap;


public class Main{
  public static void main(String[] args) {
    //create hashmap
    HashMap<String, Integer> initiative = new HashMap<String, Integer>();

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

    System.out.println(initiative);
    //close the scanner
    scanner.close();

  }
}