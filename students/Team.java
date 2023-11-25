package students;

import building.*;
import java.util.ArrayList;

public class Team {
  private int currentKnowledgePoints;
  private int newStudentCost = 100;
  private int recruitCost;

  public Team(int initialKnowledgePoints) {
    this.currentKnowledgePoints = initialKnowledgePoints;
  }

  public int getCurrentKnowledgePoints() {
    return currentKnowledgePoints;
  }

  public int getRecruitCost() {
    return recruitCost = newStudentCost;
  }

  ArrayList<Student> students = new ArrayList<>();
  Student[] studentsArr = new Student[students.size()];

  public Student[] getStudents() {
    return students.toArray(studentsArr);
  }

  public int studentsAct(Building building) {
    Student[] studentsArr = students.toArray(new Student[students.size()]);
    if (studentsArr.length != 0) {
      for (int i = 0; i < studentsArr.length; i++) {
        studentsArr[i].defence(building);
        currentKnowledgePoints += studentsArr[i].getKnowledgePts();
      }
    } else {
      System.out.println("There is no student in the team!");
    }
    return currentKnowledgePoints;
  }

  public void getNewStudentCost() {
    newStudentCost += 10;
  }

  public int getTeamSize() {
    return students.size();
  }

  public Student[] recruitNewStudent() throws Exception {
    Student[] studentsArr = students.toArray(new Student[students.size()]);
    Toolbox toolBox = new Toolbox();
    int a = toolBox.getRandomInteger(100);
    if (a <= 25 && currentKnowledgePoints >= newStudentCost) {
      students.add(new AiStudent(1));
      currentKnowledgePoints -= newStudentCost;
      getNewStudentCost();
    } else if (a >= 26 && a <= 50 && currentKnowledgePoints >= newStudentCost) {
      students.add(new CsStudent(1));
      currentKnowledgePoints -= newStudentCost;
      getNewStudentCost();
    } else if (a >= 51 && a <= 75 && currentKnowledgePoints >= newStudentCost) {
      students.add(new CyberStudent(1));
      currentKnowledgePoints -= newStudentCost;
      getNewStudentCost();
    } else if (a >= 76 && a <= 100 && currentKnowledgePoints >= newStudentCost) {
      students.add(new SeStudent(1));
      currentKnowledgePoints -= newStudentCost;
      getNewStudentCost();
    } else {
      throw new Exception("Current Knowledge points are not enough!");
    }
    return studentsArr;
  }

  public void upgradeExistingStudent(Student student) throws Exception {
    if (currentKnowledgePoints >= student.upgradeCost()) {
      currentKnowledgePoints -= student.upgradeCost();
      student.setLevel(student.getLevel() + 1);
    } else {
      throw new Exception("Current Knowledge points are not enough!");
    }
  }
}
