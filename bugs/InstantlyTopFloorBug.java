package bugs;

import building.Building;

public class InstantlyTopFloorBug extends Bug {
  public InstantlyTopFloorBug(String name, int level, int initialSteps) {
    super(name, 1, 1, level, initialSteps, Building.getTopFloor());
  }
}
