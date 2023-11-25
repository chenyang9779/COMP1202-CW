package students;

import bugs.Bug;
import building.Building;

public class CyberStudent implements Student {

  private int level;
  private int knowledgePts;
  private int delay = 8;
  private int baseAtk = 7;

  public CyberStudent(int level) {
    this.level = level;
  }

  @Override
  public int getBaseAtk() {
    return baseAtk;
  }

  @Override
  public int getDelay() {
    return delay;
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public int setLevel(int a) {
    return this.level = a;
  }

  @Override
  public int getKnowledgePts() {
    return knowledgePts;
  }

  @Override
  public int upgradeCost() {
    return knowledgePts = (int) Math.round(100 * (Math.pow(2, level)));
  }

  @Override
  public int defence(Building building) {
    if (delay != 1) {
      normalAtk(building);
      delay -= 1;
    } else if (delay == 1) {
      specialAtk(building);
      delay = 8;
    }
    return knowledgePts;
  }

  @Override
  public int normalAtk(Building building) {
    knowledgePts = 0;
    Bug[] bugs = building.getAllBugs();
    if (bugs.length != 0) {
      bugs[0].damage((int) Math.round(baseAtk * Math.pow(level, 1.2)));
      if (bugs[0].getCurrentHp() == 0) {
        knowledgePts = bugs[0].getLevel() * 20;
        building.removeBug(bugs[0]);
      }
    }
    return knowledgePts;
  }

  @Override
  public int specialAtk(Building building) {
    knowledgePts = 0;
    Bug[] bugs = building.getAllBugs();
    Toolbox toolBox = new Toolbox();
    int a = toolBox.getRandomInteger(100);
    if (bugs.length != 0) {
      if (level + 20 <= 50 && a <= level + 20) {
        knowledgePts += bugs[0].getLevel() * 20;
        building.removeBug(bugs[0]);
      } else if (level + 20 > 50 && a <= 50) {
        knowledgePts += bugs[0].getLevel() * 20;
        building.removeBug(bugs[0]);
      } else {
        bugs[0].damage((int) Math.round(baseAtk * Math.pow(level, 1.2)) * 2);
      }
      if (bugs[0].getCurrentHp() == 0) {
        knowledgePts += bugs[0].getLevel() * 20;
        building.removeBug(bugs[0]);
      }
    }
    return knowledgePts;
  }
}
