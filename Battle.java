import bugs.*;
import building.*;
import students.*;

public class Battle {

  Team team;
  Building building;
  Toolbox toolbox = new Toolbox();

  public Battle(Team team, Building building) {
    this.team = team;
    this.building = building;
  }

  public static void print(String a) {
    System.out.println(a);
  } // print line method

  public void manageTeam() {
    if (team.getCurrentKnowledgePoints() >= team.getRecruitCost()) {
      try {
        team.recruitNewStudent();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    Student[] studentsArr = team.getStudents();
    for (int i = 0; i < studentsArr.length; i++) {
      if (team.getCurrentKnowledgePoints() >= studentsArr[i].upgradeCost()) {
        try {
          team.upgradeExistingStudent(studentsArr[i]);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } // check if current knowledge point enough to upgrade a specific student if so then upgrade
    // the student
  }

  public void printStatement() {
    print(
        "Current knowledge points: "
            + team.getCurrentKnowledgePoints()
            + "; Next recruiting costs: "
            + team.getRecruitCost());
    print(
        "Current construction points:"
            + building.getConstructionPoints()
            + "; Top floor: "
            + building.getTopFloor());
    Student[] student = team.getStudents();
    print("Currently there are " + team.getTeamSize() + " students in the team:");
    for (int i = 0; i < student.length; i++) {
      print(
          student[i].getClass()
              + "; Level: "
              + student[i].getLevel()
              + "; UpgradeCost: "
              + student[i].upgradeCost()
              + "; Base Attack: "
              + student[i].getBaseAtk()
              + "; Delay: "
              + student[i].getDelay());
    }
    Bug[] bugArray = building.getAllBugs();
    print("Currently there are " + bugArray.length + " bugs in the building:");
    for (int i = 0; i < bugArray.length; i++) {
      print(
          "Name: "
              + bugArray[i].getName()
              + "; Level: "
              + bugArray[i].getLevel()
              + "; Current Hp: "
              + bugArray[i].getCurrentHp()
              + "; Current Floor: "
              + bugArray[i].getCurrentFloor()
              + "; Current Steps: "
              + bugArray[i].getCurrentSteps()
              + "; "
              + bugArray[i].getClass());
    }
  }

  public void battleSteps(int count) {
    manageTeam();
    building.bugsMove();
    print("----------         Before attack " + count + "         ----------");
    printStatement();
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {

    }
    team.studentsAct(building);
    print("");
    print("----------         After attack " + count + "         ----------");
    printStatement();
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {

    }

    if (building.getConstructionPoints() <= 0) {
      print("");
      print("Building is down. Defeated!");
      System.exit(1);
    }
  }
}
