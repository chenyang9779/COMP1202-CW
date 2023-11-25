package bugs;

public class WillNotBeRemovedBug extends Bug {
  public WillNotBeRemovedBug(String name, int level, int initialSteps) {
    super(name, 15, 10, level, initialSteps, -1);
  }
}
