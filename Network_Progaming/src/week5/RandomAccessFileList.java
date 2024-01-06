package week5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileList {

  private final int NAME_LENGTH = 25;
  private RandomAccessFile raf;
  private int count;
  private int recSize;
  private int length;
  private final int headSize = 8;


  public RandomAccessFileList(String file) throws IOException {
    // Khởi tạo một đối tượng RandomAccessFile để thao tác với file ở chế độ đọc và ghi ("rw").
    this.raf = new RandomAccessFile(file, "rw");

    // Kiểm tra xem file có dữ liệu hay không.
    if (raf.length() > 0) {
      // Đọc giá trị count từ file.
      this.count = raf.readInt();

      // Đọc giá trị recSize từ file.
      this.recSize = raf.readInt();

      // Tính toán giá trị length dựa trên recSize.
      this.length = (recSize - 16) / 2;
    } else {
      // Nếu file trống, khởi tạo các giá trị mặc định.
      this.count = 0;
      this.recSize = NAME_LENGTH * 2 + 16;
      this.length = NAME_LENGTH;

      // Ghi giá trị count vào file.
      raf.writeInt(count);

      // Ghi giá trị recSize vào file.
      raf.writeInt(recSize);
    }
    // (Ghi chú thêm nếu cần) Các bước khác có thể thực hiện trong constructor nếu có.
  }


  public void add(Student student) throws IOException {
    raf.seek(raf.length());
    student.writeStudent(raf, length);
    count++;
    raf.seek(0);
    raf.writeInt(count);
  }

  public Student get(int index) throws IOException {
    Student student = new Student();
    if (index >= count) {
      return null;
    }
    long pos = headSize + index * recSize;
    raf.seek(pos);
    student.readStudent(raf, length);
    return student;
  }

  public void update(int index, Student student) throws IOException {
    if (index >= count) {
      return;
    }
    long pos = headSize + index * recSize;
    raf.seek(pos);
    student.writeStudent(raf, length);
  }

  public void close() throws IOException{
    raf.close();
  }

  public static void main(String[] args)  throws IOException{
    String file = "D:\\Trash\\Temp\\Test\\test.txt";
    RandomAccessFileList randomAccessFileList = new RandomAccessFileList(file);

    randomAccessFileList.add(new Student(201, "Do Minh Phu", 2002, 9.5));
    Student student= randomAccessFileList.get(1);

    System.out.println(student);

    randomAccessFileList.update(1, new Student(202,"Do Minh Phu", 2002, 10));

    System.out.println(randomAccessFileList.get(1));
  }


}
