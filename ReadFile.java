import bugs.ConcurrentModificationBug;
import bugs.InstantlyTopFloorBug;
import bugs.NoDamageBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import bugs.WillNotBeRemovedBug;
import building.Building;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class ReadFile {
  String path = "";
  File file = new File(path);
  StringBuilder result = new StringBuilder();

  public static BufferedReader readFile(String read) {
    BufferedReader br = null;
    try {
      br =
          new BufferedReader(
              new InputStreamReader(
                  new FileInputStream(read), "UTF-8")); // 构造一个BufferedReader类来读取文件
    } catch (Exception e) {
      System.out.println(e);
    }
    return br;
  }

  public static String getSubString(String text, String left, String right) {
    String result = "";
    int zLen;
    if (left == null || left.isEmpty()) {
      zLen = 0;
    } else {
      zLen = text.indexOf(left);
      if (zLen > -1) {
        zLen += left.length();
      } else {
        zLen = 0;
      }
    }
    int yLen = text.indexOf(right, zLen);
    if (yLen < 0 || right == null || right.isEmpty()) {
      yLen = text.length();
    }
    result = text.substring(zLen, yLen);
    return result;
  } // https://blog.csdn.net/qq_42282196/article/details/95367084

  public static void addBugToBuilding(String[] bugs, Building building) {
    for (int i = 0; i < bugs.length; i++) {
      String bugA = bugs[i];
      String name = getSubString(bugA, "", "(");
      String type = getSubString(bugA, "(", ",");
      String level = getSubString(bugA, ",", ",");
      String iniSteps = getSubString(bugA, ",", ")");
      iniSteps = getSubString(iniSteps, ",", "");
      if (Objects.equals(type, "CMB")) {
        ConcurrentModificationBug cm =
            new ConcurrentModificationBug(
                name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(cm);
      } else if (Objects.equals(type, "NPB")) {
        NullPointerBug np =
            new NullPointerBug(name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(np);
      } else if (Objects.equals(type, "NTB")) {
        NoneTerminationBug nt =
            new NoneTerminationBug(name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(nt);
      } else if (Objects.equals(type, "NDB")) {
        NoDamageBug nd = new NoDamageBug(name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(nd);
      } else if (Objects.equals(type, "WNB")) {
        WillNotBeRemovedBug wn =
            new WillNotBeRemovedBug(name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(wn);
      } else if (Objects.equals(type, "ITB")) {
        InstantlyTopFloorBug it =
            new InstantlyTopFloorBug(name, Integer.parseInt(level), Integer.parseInt(iniSteps));
        building.addBug(it);
      }
    }
  }
}
