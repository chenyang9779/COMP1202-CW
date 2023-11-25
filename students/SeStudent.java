package students;

import bugs.Bug;
import building.Building;

public class SeStudent implements Student {

  private int level;
  private int knowledgePts;
  private int delay = 6;
  private int baseAtk = 5;

  public SeStudent(int level) {
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
      delay = 6;
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
    Bug[] bugs = building.getAllBugs();
    if (bugs.length >= 5) {
      for (int i = 0; i < 5; i++) {
        bugs[i].slowDown(2);
      }
    } else {
      for (int i = 0; i < bugs.length; i++) {
        bugs[i].slowDown(2);
      }
    }
    return 0;
  }
}
