package week6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentManage {

  public static List<Student> loadData(String stFile, String gradeFile, String charset)
      throws IOException {
    List<Student> list = new ArrayList<>();

    int id;
    String name;
    int byear;
    double grade;

    String line;
    StringTokenizer st;
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(stFile), charset));
    while ((line = reader.readLine()) != null) {
      st = new StringTokenizer(line, "\t");
      id = Integer.parseInt(st.nextToken());
      name = st.nextToken();
      byear = Integer.parseInt(st.nextToken());

      list.add(new Student(id, name, byear));
    }

    reader = new BufferedReader(new InputStreamReader(new FileInputStream(gradeFile), charset));

    while ((line = reader.readLine()) != null) {
      st = new StringTokenizer(line, "\t");
      id = Integer.parseInt(st.nextToken());
      int count = 0;
      double values = 0;
      while (st.hasMoreTokens()) {
        count++;
        values += Double.parseDouble(st.nextToken());
      }
      grade = values / count;
      for (Student student : list) {
        if (student.id == id) {
          student.setGrade(grade);
        }
      }
    }

    reader.close();

    return list;
  }

  public static void exportData(List<Student> list, String textFile, String charset)
      throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter pw = new PrintWriter(textFile, charset);
    for (Student student : list) {
      String line = student.id + student.name + student.byear + student.grade;
      pw.println(line);
    }
    pw.close();
  }

  public static void main(String[] args) throws IOException {
    String stFile = "D:\\Trash\\Temp\\Test\\student.txt";
    String charset = "UTF-8";
    String gradeFile = "D:\\Trash\\Temp\\Test\\grade.txt";
    String textFile = "D:\\Trash\\Temp\\Test\\test.txt";

    List<Student> list = loadData(stFile, gradeFile, charset);
//    System.out.println(list);
    exportData(list, textFile, charset);
  }


}
