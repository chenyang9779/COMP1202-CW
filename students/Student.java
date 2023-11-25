package students;

import building.*;

public interface Student {

  public int getLevel();

  public int upgradeCost();

  public int defence(Building building);

  public int normalAtk(Building building);

  public int specialAtk(Building building);

  public int getKnowledgePts();

  public int setLevel(int a);

  public int getDelay();

  public int getBaseAtk();
}
