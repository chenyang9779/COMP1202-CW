package bugs;

public class NoDamageBug extends Bug {
  public NoDamageBug(String name, int level, int initialSteps) {
    super(name, 300, 10, level, initialSteps, -1);
  }
}
