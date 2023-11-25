import building.Building;
import java.io.*;
import students.Team;

public class EcsBuildingDefence {

  public static void print(String a) {
    System.out.println(a);
  } // print line method

  public static void main(String[] args) {
    ReadFile readFile = new ReadFile();

    //     Read configuration code as follows
    int tF = Integer.valueOf(args[0]);
    int cP = Integer.valueOf(args[1]);
    String bug = String.valueOf(args[2]);
    int iKp = Integer.valueOf(args[3]);
    Building building = new Building(tF, cP); // new building with top floor and construction
    // points taking from cmd
    Team team = new Team(iKp); // new team with initial knowledge point taken form cmd
    BufferedReader br = readFile.readFile(bug);

    // Read from input as follows(self testing)
    //    Toolbox toolbox = new Toolbox();
    //    print("Please enter value in the following order: 1. Top floor, 2. Construction points");
    //    Building building = new Building(4, 20);
    //    print("Please type in the file you would like to access:");
    //    BufferedReader br = readFile.readFile("bugs.txt");
    //    print("Please enter the initial knowledge point for your team:");
    //    Team team = new Team(100);

    // Simulation starts running
    String s;
    int count = 1;
    int wave = 1;
    try {
      while ((s = br.readLine())
          != null) { // gets the first line of the file and when there is a next line keeps
        print("Attention！！！！！ ");

        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        print("");
        print("Wave： " + wave + "; Bugs incoming!");
        print("");

        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        String[] bugs = null; // Array for separating the first line
        bugs = s.split(";"); // splits between ";" and put in array
        readFile.addBugToBuilding(bugs, building); // add bugs of current line in to the building
        Battle battle = new Battle(team, building);
        for (int i = 0; i < building.getTopFloor() * 8; i++) {
          battle.battleSteps(count); // use battle method
          print("");
          count += 1; // count each attack and print out attack 1,2,3,4...
        }
        wave += 1;
      }
      print("There is no more wave of bug! You have defeated all the bugs!"); // all lines of bugs
      // finished
    } catch (Exception e) { // exception if no more lines after
      System.out.println(e);
      e.printStackTrace();
    }
  }
}
