package bugs;

import building.Building;

public class ConcurrentModificationBug extends Bug {
  public ConcurrentModificationBug(String name, int level, int initialSteps) {
    super(name, 20, 4, level, initialSteps, -1);
  }
}
