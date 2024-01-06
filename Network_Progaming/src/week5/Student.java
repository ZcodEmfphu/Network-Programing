package week5;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Student {

  private int id;
  private String name;
  private int birthYear;
  private double grade;

  public Student() {
  }

  public Student(int id, String name, int birthYear, double grade) {
    this.id = id;
    this.name = name;
    this.birthYear = birthYear;
    this.grade = grade;
  }

  // Ghi vào chuỗi tên với độ dài cho trước vào DataOutput
  public void writeLengthName(DataOutput dos, int length) throws IOException {
    char c;
    for (int i = 0; i < length; i++) { // Duyệt qua từng phần tử
      if (i < name.length()) { // Nếu có thì
        c = name.charAt(i);// Lấy chuỗi name
      } else {
        c = 0; //thì gán là 0
      }
      dos.writeChar(c); // ghi vào DataOutput
    }
  }

  // Đọc từ DataInput 1 chuỗi cho trước
  public String readLengthName(DataInput dis, int length) throws IOException {
    String res = " ";
    char c;
    for (int i = 0; i < length; i++) {
      // Đọc một kí tự từ DataInput
      c = dis.readChar();
      if (c != 0) { // Nếu kí tự khác 0
        res += c;// thì thêm vào
      }
    }
    return res;// Trả về chuỗi kết quả
  }


  // Ghi dữ liệu thông tin học sinh vào DataOutput
  public void writeStudent(DataOutput dos, int length) throws IOException {
    dos.writeInt(id);// Ghi Id vào DataOutput
    writeLengthName(dos, length); // Ghi tên vào DataOutput
    dos.writeInt(birthYear); // Ghi năm sinh vào DataOutput
    dos.writeDouble(grade); // Ghi điểm vào DataOutput
  }

  // Đọc thông tin học sinh từ DataInput
  public void readStudent(DataInput dis, int length) throws IOException {
    id = dis.readInt(); // Đọc id học sinh từ DataInput
    name = readLengthName(dis, length); // Đọc độ dài tên học sinh từ DataInput
    birthYear = dis.readInt();// Đọc năm sinh từ DataInput
    grade = dis.readDouble();// Đọc điểm của học sinh từ DataInput
  }

  public String toString() {
    return "Id:" + id + ", Name:" + name + ", bYear:" + birthYear + ", Grade:" + grade;
  }
}
