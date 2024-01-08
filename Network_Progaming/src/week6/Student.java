package week6;

public class Student {

  public int id;
  public String name;
  public int byear;
  public double grade;

  public Student(int id, String name, int byear) {
    this.id = id;
    this.name = name;
    this.byear = byear;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + id +
        ", name='" + name +
        ", byear=" + byear +
        ", grade=" + grade +
        '}'+"\n";
  }
}
