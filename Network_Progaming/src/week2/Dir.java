package week2;

import java.io.File;

public class Dir {

  public static void dirTree(String path) {
    File dir = new File(path);
    if (!dir.exists()) {
      return;
    }
    int level = 0;
    if (dir.isFile()) {
      printFile(dir, level);
    }
    if (dir.isDirectory()) {
      dirTreeHelper(dir, level);
    }
  }

  public static void dirTreeHelper(File dir, int level) {
    printDir(dir, level);
    File[] list = dir.listFiles();
    for (File f : list) {
      if (f.isDirectory()) {
        dirTreeHelper(f, level + 1);
      }
    }
    for (File f : list) {
      if (f.isFile()) {
        printFile(f, level + 1);
      }
    }
  }
  // 100k 30k(dui ga chien) 40k(Bun nem nuong) 50k(Hu tieu go) 32k(Banh mi thit)
  // 17k     15k               20k                 25k             16k

  public static void printDir(File dir, int level) { // In Folder to UpperCase
    StringBuilder sb = getIndex(level);
    sb.append(dir.getName().toUpperCase());
    System.out.println(sb.toString());
  }

  public static void printFile(File dir, int level) { // In File to LowerCase
    StringBuilder sb = getIndex(level);
    sb.append(dir.getName().toLowerCase());
    System.out.println(sb.toString());
  }

  public static StringBuilder getIndex(int level) {
    StringBuilder sb = new StringBuilder();
    if (level == 0) {
      sb.append("+-");
    } else {
      sb.append("  ");
      for (int i = 1; i < level; i++) {
        sb.append("| ");
      }
      sb.append("+-");
    }
    return sb;
  }

  public static void main(String[] args) {
    String path = "D:\\Trash\\Temp";
    dirTree(path);
  }
}
