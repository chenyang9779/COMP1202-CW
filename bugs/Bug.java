package bugs;

import building.Building;

public class Bug {

  private int initialSteps;
  private String name;
  private int baseHp;
  private int baseSteps;
  private int level;
  private int currentHp;
  private int currentSteps;
  private int currentFloor; // all bugs initially at floor -1 which is not in the building

  public Bug(String name, int baseHp, int baseSteps, int level, int currentFloor) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentFloor = currentFloor;
  }

  public Bug(
      String name, int baseHp, int baseSteps, int level, int initialSteps, int currentFloor) {
    this(name, baseHp, baseSteps, level, currentFloor);
    this.currentHp = (int) Math.round(baseHp * (Math.pow(level, 1.5)));
    this.currentSteps = initialSteps;
  }

  public int getBaseSteps() {
    return baseSteps;
  }

  public int getLevel() {
    return level;
  } // getter method for bugs' level

  public int getCurrentHp() {
    return currentHp;
  } // getter method for bugs' current hp

  public void increaseCurrentHp(int amount) {
    currentHp += amount;
  }

  public int getCurrentSteps() {
    return currentSteps;
  } // getter method for bugs' current step

  public int getCurrentFloor() {
    return currentFloor;
  } // getter method for bugs' current floor

  public String getName() {
    return name;
  } // getter method fot bugs' name

  public void specialAbility() {
    Bug[] bug = Building.getAllBugs();
    for (int i = 0; i < bug.length; i++) {
      if (bug[i].getClass() == ConcurrentModificationBug.class) {
        Building.reduceConstructionPoints(1);
      } else if (bug[i].getClass() == NoneTerminationBug.class) {
        bug[i].increaseCurrentHp(100 * bug[i].currentFloor);
      } else if (bug[i].getClass() == NullPointerBug.class
          && bug[i].getCurrentFloor() == Building.getTopFloor()) {
        NullPointerBug nt = new NullPointerBug("duplicate", 1, 1);
        Building.addBug(nt);
      }
    }
  }

  public void move() {
    if (currentSteps > 0) {
      --currentSteps; // current step = current step - 1
    } else {
      currentFloor += 1; // current floor = current floor + 1
      currentSteps = baseSteps - 1; // reset current steps to base steps -1
      specialAbility();
    }
  }

  public void damage(int amount) {
    if (amount > currentHp) {
      currentHp = 0; // if damage is greater than current hp then bugs' hp is 0(dead)
    } else {
      currentHp -= amount; // current hp changed to new current hp with damage taken away
    }
  }

  public void slowDown(int steps) {
    currentSteps += steps;
  } // slow down the bug by adding the input steps to the current number of steps
}
